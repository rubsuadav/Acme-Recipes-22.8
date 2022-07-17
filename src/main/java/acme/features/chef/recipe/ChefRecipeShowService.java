package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;
import acme.utils.ConversionExchange;

@Service
public class ChefRecipeShowService implements AbstractShowService<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository repository;
	
	@Autowired
	protected ConversionExchange exchange;

	// AbstractShowService<Administrator, Recipe> interface --------------

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		boolean result;
		int recipeId;
		Recipe recipe;

		recipeId = request.getModel().getInteger("id");
		recipe = this.repository.findOneById(recipeId);
		result = recipe.getChef().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		Recipe result;
		int recipeId;

		recipeId = request.getModel().getInteger("id");
		result = this.repository.findOneById(recipeId);

		result.setRetailPrice(this.totalPriceOfRecipe(recipeId));

		return result;
		
	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String retailPrice = entity.getRetailPrice().toString().replace("<<", "").replace(">>", "");

		model.setAttribute("price", retailPrice);

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link");

		String result = "";

		result = entity.isPublished() ? "The recipe is published": "The recipe is not published";
		model.setAttribute("published", result);
	}
	
	/**
	 * @param recipeId
	 * @return the total price of the Recipe with his currency
	 */
	private Money totalPriceOfRecipe(final int RecipeId) {
		final Money result = new Money();
		result.setAmount(0.0);
		result.setCurrency(this.repository.findSystemConfiguration().getSystemCurrency());

		final Collection<Quantity> quantities = this.repository.findManyQuantitiesByRecipeId(RecipeId);

		for(final Quantity quantity: quantities) {
			final double conversionAmount;
			final Money itemMoney = quantity.getItem().getRetailPrice();
			final int number = quantity.getNumber();

			conversionAmount = this.exchange.conversion(itemMoney).getTarget().getAmount();

			final Double newAmount = (double) Math.round((result.getAmount() + conversionAmount*number)*100)/100;
			result.setAmount(newAmount);
		}
		
		return result;
	}

}