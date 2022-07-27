package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.pimpams.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.helpers.CollectionHelper;
import acme.framework.services.AbstractListService;
import acme.roles.Chef;

@Service
public class ChefPimpamItemListService implements AbstractListService<Chef, Pimpam>{
	
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
		int masterId;
		
		masterId = request.getModel().getInteger("masterId");
        result = this.repository.findManyPimpamsByItemId(masterId);
		return result;
	}
		
		@Override
		public void unbind(final Request<Pimpam> request, final Collection<Pimpam> entities, final Model model) {
			assert request != null;
			assert !CollectionHelper.someNull(entities);
			assert model != null;
			
			int masterId;
			Item item;
			final boolean showCreate;

			masterId = request.getModel().getInteger("masterId");
			item = this.repository.findOneItemById(masterId);
			showCreate = (!item.isPublished() && this.repository.findManyPimpamsByItemId(masterId).isEmpty());

			model.setAttribute("masterId", masterId);
			model.setAttribute("showCreate", showCreate);
			
		}
		
	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "instantiationMoment", "title", "budget");
				
	}
	
}