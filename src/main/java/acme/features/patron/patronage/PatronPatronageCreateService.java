package acme.features.patron.patronage;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage>{

	@Autowired
	protected PatronPatronageRepository repository;

	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if(this.repository.findAllInventors().isEmpty()) {
			request.bind(entity, errors,"code","legalStuff","budget","initial","end","link");
		} else {
			entity.setInventor(this.repository.finOneInventorById(Integer.valueOf( request.getModel().getAttribute("inventorId").toString())));
			request.bind(entity, errors,"code","legalStuff","budget","initial","end","link", "inventorId");
		}

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code","legalStuff","budget","initial","creation","end","link","published");
		model.setAttribute("inventors", this.repository.findAllInventors());
	}

	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		Patron patron;

		patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());
		result = new Patronage();
		result.setPatron(patron);

		Date creation;
		creation = new Date(System.currentTimeMillis()-1);

		result.setCreation(creation);
		
		result.setStatus(Status.PROPOSED);
		
		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Patronage existing;

			existing = this.repository.findOnePatronageByCode(entity.getCode());
			errors.state(request, existing == null, "code", "patron.patronage.form.error.duplicated");
		}

		if (!errors.hasErrors("budget")) {
			final String[] currencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			boolean acceptedCurrencies = false;
			for(int i = 0; i< currencies.length; i++) {
				if(entity.getBudget().getCurrency().equals(currencies[i].trim())) {
					acceptedCurrencies=true;
				}
			}

			errors.state(request, acceptedCurrencies, "budget", "patron.patronage.form.error.non-accepted-currency");
			
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget");
		}
		
		if(!errors.hasErrors("initial")) {
			final Date minimumStartDate=DateUtils.addMonths(entity.getCreation(), 1);
			errors.state(request,entity.getInitial().after(minimumStartDate), "initial", "patron.patronage.form.error.too-close-start-date");
			
		}
		
		if(!errors.hasErrors("end") && !errors.hasErrors("initial")) {
			final Date minimumFinishDate=DateUtils.addMonths(entity.getInitial(), 1);
			errors.state(request,entity.getEnd().after(minimumFinishDate), "end", "patron.patronage.form.error.one-month");
			
		}
		
		if(entity.getInventor()==null) {
            errors.state(request, entity.getInventor() != null, "inventorId", "patron.patronage.form.error.noInventor");
        }
	}
  
	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(moment);

		this.repository.save(entity);

	}

}