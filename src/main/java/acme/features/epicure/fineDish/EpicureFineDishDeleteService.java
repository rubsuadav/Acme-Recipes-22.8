package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.entities.memorandums.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Epicure;

@Service
public class EpicureFineDishDeleteService implements AbstractDeleteService<Epicure, FineDish> {
	
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
		
	}

	@Override
	public void delete(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		for (final Memorandum m : this.repository.findMemorandumByFineDishId(entity.getId())) {
			this.repository.delete(m);
		}
		
		this.repository.delete(entity);	
	}

}