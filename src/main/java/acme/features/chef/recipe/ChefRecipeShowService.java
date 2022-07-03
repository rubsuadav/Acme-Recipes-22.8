package acme.features.chef.recipe;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefRecipeShowService implements AbstractShowService<Chef, Recipe> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefRecipeRepository repository;

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

		result = entity.isPublished() ? "The Recipe is published": "The Recipe is not published";
		model.setAttribute("published", result);
	}
	
	//IMPLEMENTACION CACHÉ DE CONVERSIONES

	/**
	 * @param money
	 * @return conversiones de divisas haciendo uso de la cache
	 */
	protected MoneyExchange conversion(final Money money) {

		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();

		MoneyExchange conversion = new MoneyExchange();

		final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();
		
		//Si la divisa es diferente de la divisa predeterminada de la configuración del sistema, entonces pueden ocurrir 2 cosas:

		if(!money.getCurrency().equals(systemConfiguration.getSystemCurrency())) {
			conversion = this.repository.findMoneyExchangeByCurrencyAndAmount(money.getCurrency(), money.getAmount());
			
			//Se obtiene o calcula  la conversión

			if(conversion == null) {
				conversion = moneyExchange.computeMoneyExchange(money, systemConfiguration.getSystemCurrency());
				this.repository.save(conversion);

			}
			
			//En caso contrario, no es necesario realizar una conversión

		} else {
			conversion.setSource(money);
			conversion.setTarget(money);
			conversion.setCurrencyTarget(systemConfiguration.getSystemCurrency());
			conversion.setDate(new Date(System.currentTimeMillis()));

		}

		return conversion;

	}

	/**
	 * @param RecipeId
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

			conversionAmount = this.conversion(itemMoney).getTarget().getAmount();

			final Double newAmount = (double) Math.round((result.getAmount() + conversionAmount*number)*100)/100;
			result.setAmount(newAmount);
		}
		
		return result;
	}

}