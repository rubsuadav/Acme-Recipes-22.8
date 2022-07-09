package acme.features.chef.memorandum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandums.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefMemorandumListMineService implements AbstractListService<Chef, Memorandum> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefMemorandumRepository repository;
	
	// AbstractListService<Chef, Memorandum> interface --------------
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		return true;
	}
	
	@Override
	public Collection<Memorandum> findMany(final Request<Memorandum> request) {
		assert request != null;

		Collection<Memorandum> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findManyMemorandumsByChefId(principal.getActiveRoleId());

		return result;
	}
	
	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("fineDishId", entity.getFineDish().getCode());

		request.unbind(entity, model, "instantiationMoment");
	}
}
