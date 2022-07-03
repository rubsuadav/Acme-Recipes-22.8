package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyRecipeListService implements AbstractListService<Any, Recipe>{

	@Autowired
	protected AnyRecipeRepository repository;
	
	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Recipe> findMany(final Request<Recipe> request) {
		assert request != null;

		Collection<Recipe> result;

		result = this.repository.findPublished();

		return result;
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		//INCLUYENDO UN ITEM EN PARTICULAR
		
		final String payload;
		final StringBuilder bufferName = new StringBuilder();
		final StringBuilder bufferCode = new StringBuilder();
		
		final Collection<Item> items = this.repository.findManyItemsByRecipeId(entity.getId());
		int size = items.size();
		
		for(final Item i: items) {
			bufferName.append(i.getName());
			bufferCode.append(i.getCode());
			
			if(size > 1) {
				bufferName.append(",");
				bufferCode.append(",");
				
				size--;
			}	
		}
		
		payload = String.format(//
			"%s; %s", //
			bufferName.toString(),bufferCode.toString());

		request.unbind(entity, model, "code", "heading");
		
		model.setAttribute("payload", payload);

	}
}
