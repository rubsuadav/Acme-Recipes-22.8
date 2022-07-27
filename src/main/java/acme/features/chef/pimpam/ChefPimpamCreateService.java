package acme.features.chef.pimpam;

import java.time.LocalDate;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.Type;
import acme.entities.pimpams.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefPimpamCreateService implements AbstractCreateService<Chef, Pimpam>{
	
	@Autowired
	protected ChefPimpamRepository repository;

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;

		boolean result;
		int masterId;
		Item item;

		masterId = request.getModel().getInteger("masterId");
		item = this.repository.findOneItemById(masterId);
		result = (item != null && !item.isPublished() && request.isPrincipal(item.getChef()) 
			&& item.getTypeEntity().equals(Type.KITCHENUTENSIL) && this.repository.findManyPimpamsByItemId(masterId).isEmpty());

		return result;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code","title","desciption","initial","end","budget","link");
		
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("masterId", request.getModel().getInteger("masterId"));
		request.unbind(entity, model, "code", "instantiationMoment", "title","desciption","initial","end","budget","link");
		
	}

	@Override
	public Pimpam instantiate(final Request<Pimpam> request) {
		assert request != null;

		Pimpam result;

		result = new Pimpam();

		Date creation;
		creation = new Date(System.currentTimeMillis()-1);

		result.setInstantiationMoment(creation);

		return result;
	}

	@Override
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Pimpam existing;

			existing = this.repository.findOnePimpamByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.pimpam.form.error.duplicated");

			//RN DE VALIDACION DEL CODIGO DE YYMMDD

			final LocalDate date = LocalDate.now();
			
			final int dayValue = date.getDayOfMonth();
			final String day = String.format("%02d", dayValue);

			final int monthValue = date.getMonthValue();
			final String month = String.format("%02d", monthValue);

			final int yearValue = date.getYear();
			final String year = String.format("%02d", yearValue).substring(2);

			final String correctlyDate = String.format("%s%s%s", day,month,year);
			errors.state(request,entity.getCode().contains(correctlyDate), "code", "chef.pimpam.form.error.code");

		}

		if (!errors.hasErrors("budget")) {
			final String[] currencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			boolean acceptedCurrencies = false;
			for(int i = 0; i< currencies.length; i++) {
				if(entity.getBudget().getCurrency().equals(currencies[i].trim())) {
					acceptedCurrencies=true;
				}
			}

			errors.state(request, acceptedCurrencies, "budget", "chef.pimpam.form.error.non-accepted-currency");
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "chef.pimpam.form.error.negative-budget");
		}

		if(!errors.hasErrors("initial")) {
			final Date minimumStartDate=DateUtils.addMonths(entity.getInstantiationMoment(), 1);
			errors.state(request,entity.getInitial().after(minimumStartDate), "initial", "chef.pimpam.form.error.too-close-start-date");

		}

		if(!errors.hasErrors("end") && !errors.hasErrors("initial")) {
			final Date minimumFinishDate=DateUtils.addWeeks(entity.getInitial(), 1);
			errors.state(request,entity.getEnd().after(minimumFinishDate), "end", "chef.pimpam.form.error.one-week");

		}
		
	}

	@Override
	public void create(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;

		int masterId;
		Item item;

		masterId = request.getModel().getInteger("masterId");
		item = this.repository.findOneItemById(masterId);

		item.setPimpam(entity);
		
		this.repository.save(item);
		
		this.repository.save(entity);
		
	}

}