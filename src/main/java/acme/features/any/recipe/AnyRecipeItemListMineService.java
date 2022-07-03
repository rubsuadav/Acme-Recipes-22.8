package acme.features.any.recipe;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service 
public class AnyRecipeItemListMineService implements AbstractListService<Any, Item>{

	@Autowired
	protected AnyRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null; 

		int id;
		id=request.getModel().getInteger("id");
		final Recipe recipe = this.repository.findOneById(id);
		return	recipe.isPublished();
	}

	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		final Collection<Item> result = new HashSet<>();
		int recipeId;
		
		recipeId = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findManyQuantitiesByRecipeId(recipeId);

		for(final Quantity quantity: quantities) {
			final int id=quantity.getId();
			final Collection<Item> items=this.repository.findManyItemsByQuantityId(id);
			result.addAll(items);
		}

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null; 
		assert entity != null; 
		assert model != null; 

		request.unbind(entity, model, "typeEntity", "name","code", "retailPrice"); 

	}

}