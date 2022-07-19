package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.entities.fineDishes.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefFineDishUpdateService implements AbstractUpdateService<Chef, FineDish> {
	
	@Autowired
	protected ChefFineDishRepository repository;

	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;
		
		boolean result;
		int id;
		FineDish fineDish;
		Chef chef;
		
		id = request.getModel().getInteger("id");
		fineDish = this.repository.findOneById(id);
		chef = fineDish.getChef();
		result = fineDish.getStatus().equals(Status.PROPOSED) && request.isPrincipal(chef) && fineDish.isPublished();
		
		return result;
	
	}

	@Override
	public void bind(final Request<FineDish> request, final FineDish entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,  "status", "code", "request", "budget", "initial", "creation", "end", "link");
		
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("epicureProfileFullName", entity.getEpicure().getIdentity().getFullName());
		model.setAttribute("epicureProfileEmail", entity.getEpicure().getIdentity().getEmail());
		model.setAttribute("epicureCompany", entity.getEpicure().getOrganisation());
		model.setAttribute("epicureStatement", entity.getEpicure().getAssertion());
		model.setAttribute("epicureLink", entity.getEpicure().getLink());

		request.unbind(entity, model,"status","code","request","budget","initial","creation","end","link");
		
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
	public void update(final Request<FineDish> request, final FineDish entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}