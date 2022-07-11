package acme.features.epicure.memorandum;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandums.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumListMineService implements AbstractListService<Epicure, Memorandum> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureMemorandumRepository repository;
	
	// AbstractListService<Epicure, Memorandum> interface --------------
	
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
		result = this.repository.findManyMemorandumsByEpicureId(principal.getActiveRoleId());

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
