package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;
import acme.utils.ConversionExchange;

@Service
public class ChefQuantityShowService implements AbstractShowService<Chef, Quantity>{

	@Autowired
	protected ChefRecipeRepository repository;
	
	@Autowired
	protected ConversionExchange exchange;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int recipeId;
		int quantityId;
		Quantity quantity;
		Recipe recipe;

		quantityId = request.getModel().getInteger("id");
		quantity = this.repository.findOneQuantityById(quantityId);
		recipeId = quantity.getRecipe().getId();
		recipe = this.repository.findOneById(recipeId);
		result = recipe.getChef().getId() == request.getPrincipal().getActiveRoleId();

		return result;
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//IMPLEMENTACION CACHÉ DE CONVERSIONES

		final MoneyExchange conversion = this.exchange.conversion(entity.getItem().getRetailPrice());

		model.setAttribute("recipePublished", entity.getRecipe().isPublished());
		model.setAttribute("conversion", conversion.getTarget());
		
		String result = "";

		result = entity.getItem().isPublished() ? "The item is published": "The item is not published";
		
		model.setAttribute("published", result);

		request.unbind(entity, model, "number", "item.typeEntity", "item.name", "item.code", "item.description", 
			"item.retailPrice", "item.link");

	}
	
}