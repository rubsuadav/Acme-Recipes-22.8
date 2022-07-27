package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.pimpams.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefPimpamListService implements AbstractListService<Chef, Pimpam>{
	
	@Autowired
	protected ChefPimpamRepository repository;

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Pimpam> findMany(final Request<Pimpam> request) {
		assert request != null;

		Collection<Pimpam> result;	
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findManyPimpamsByChefId(principal.getActiveRoleId());

		return result;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "instantiationMoment", "title", "budget");
		
		final Item item = this.repository.findOneItemByPimpamId(entity.getId());
		
		model.setAttribute("itemId", item.getCode());
		
	}

}