package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyRecipeShowService implements AbstractShowService<Any, Recipe> {

	@Autowired
	protected AnyRecipeRepository repository;

	// AbstractShowService<Any, Item> interface --------------

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		boolean result;
		int recipeId;
		Recipe recipe;
		
		recipeId = request.getModel().getInteger("id");
		recipe = this.repository.findOneById(recipeId);
		result = recipe.isPublished();

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
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
		
		String result = "";

		result = entity.isPublished() ? "The Recipe is published": "The Recipe is not published";
        model.setAttribute("published", result);
	}
	
	/**
	 * @param RecipeId
	 * @return the total price of the Recipe with his currency
	 */
	private Money totalPriceOfRecipe(final int RecipeId) {
		final Money result = new Money();
		result.setAmount(0.0);
		result.setCurrency(this.repository.findSystemCurrency());
		final AuthenticatedMoneyExchangePerformService moneyExange = new AuthenticatedMoneyExchangePerformService();
		final Collection<Quantity> quantities = this.repository.findManyQuantitiesByRecipeId(RecipeId);
		
		for(final Quantity quantity: quantities) {
			final Money itemMoney = quantity.getItem().getRetailPrice();
			final int number = quantity.getNumber();
			final String systemCurrency = this.repository.findSystemCurrency();
			final MoneyExchange itemMoneyExchanged = moneyExange.computeMoneyExchange(itemMoney, systemCurrency);
			final double newNumber = result.getAmount() + itemMoneyExchanged.getTarget().getAmount()*number;
			result.setAmount(newNumber);
		}
		
		return result;
	}
}