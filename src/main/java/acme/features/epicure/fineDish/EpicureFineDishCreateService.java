package acme.features.epicure.fineDish;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.entities.fineDishes.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishCreateService implements AbstractCreateService<Epicure, FineDish> {

	@Autowired
	protected EpicureFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		return true;

	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(this.repository.findAllChefs().isEmpty()) {
			request.bind(entity, errors,"code","request","budget","initial","end","link");
		} else {
			entity.setChef(this.repository.finOneChefById(Integer.valueOf( request.getModel().getAttribute("chefId").toString())));
			request.bind(entity, errors,"code","request","budget","initial","end","link", "chefId");
		}


	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model,"code","request","budget","initial","creation","end","link","published");
		model.setAttribute("chefs", this.repository.findAllChefs());

	}

	@Override
	public FineDish instantiate(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		Epicure epicure;

		epicure = this.repository.findOneEpicureById(request.getPrincipal().getActiveRoleId());
		result = new FineDish();
		result.setEpicure(epicure);

		Date creation;
		creation = new Date(System.currentTimeMillis()-1);

		result.setCreation(creation);

		result.setStatus(Status.PROPOSED);

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
			errors.state(request, existing == null, "code", "epicure.fine-dish.form.error.duplicated");
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

		if(entity.getChef()==null) {
			errors.state(request, entity.getChef() != null, "chefId", "epicure.fine-dish.form.error.no-chef");
		}

	}

	@Override
	public void create(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreation(moment);

		this.repository.save(entity);

	}

}