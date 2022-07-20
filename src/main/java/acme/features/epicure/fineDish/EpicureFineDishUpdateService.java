package acme.features.epicure.fineDish;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishUpdateService implements AbstractUpdateService<Epicure, FineDish> {
	
	@Autowired
	protected EpicureFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		int masterId;
		FineDish fineDish;
		Epicure epicure;

		masterId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneById(masterId);
		epicure = fineDish.getEpicure();
		result = !fineDish.isPublished() && request.isPrincipal(epicure);

		return result;
		
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,"status","code","request","budget","initial","end","link");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"status","code","request","budget","initial","creation","end","link","published");
		
		model.setAttribute("chefCompany", entity.getChef().getOrganisation());
		model.setAttribute("chefStatement", entity.getChef().getAssertion());
		model.setAttribute("chefLink", entity.getChef().getLink());
		
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
		
	}

	@Override
	public void validate(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			FineDish existing;

			existing = this.repository.findOneFineDishByCode(entity.getCode());
			
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "epicure.fine-dish.form.error.duplicated");
		}
		
		if (!errors.hasErrors("budget")) {
			final String[] currencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			boolean acceptedCurrencies = false;
			for(int i = 0; i< currencies.length; i++) {
				if(entity.getBudget().getCurrency().equals(currencies[i].trim())) {
					acceptedCurrencies=true;
				}
			}

			errors.state(request, acceptedCurrencies, "budget", "epicure.fine-dish.form.error.non-accepted-currency");
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "epicure.fine-dish.form.error.negative-budget");
		}
		
		if(!errors.hasErrors("initial")) {
			final Date minimumStartDate=DateUtils.addMonths(entity.getCreation(), 1);
			errors.state(request,entity.getInitial().after(minimumStartDate), "initial", "epicure.fine-dish.form.error.too-close-start-date");
			
		}
		
		if(!errors.hasErrors("end") && !errors.hasErrors("initial")) {
			final Date minimumFinishDate=DateUtils.addMonths(entity.getInitial(), 1);
			errors.state(request,entity.getEnd().after(minimumFinishDate), "end", "epicure.fine-dish.form.error.one-month");
			
		}
		
	}

	@Override
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}