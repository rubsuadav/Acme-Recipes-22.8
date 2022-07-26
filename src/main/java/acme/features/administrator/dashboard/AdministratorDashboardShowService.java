package acme.features.administrator.dashboard;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.Status;
import acme.entities.items.Type;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;
import acme.utils.DashboardLibrary;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Autowired
	protected DashboardLibrary library;

	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Administrator.class);
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result = new AdministratorDashboard();
		
		final String[] acceptedCurrencies = this.repository.getAcceptedCurrencies().split(",");
		final List<String> currencies = new ArrayList<>();
		
		for(int i=0; i<acceptedCurrencies.length; i++) {
			currencies.add(acceptedCurrencies[i].trim());
		}
		
		//KITCHEN UTENSILS
		
		final int numberOfKitchenUtensils;
		final Map<String, Double> averageRetailPriceOfKitchenUtensilsByCurrency;
		final Map<String, Double> deviationRetailPriceOfKitchenUtensilsByCurrency;
		final Map<String, Double> minRetailPriceOfKitchenUtensilsByCurrency;
		final Map<String, Double> maxRetailPriceOfKitchenUtensilsByCurrency;
		
		numberOfKitchenUtensils = this.repository.numberOfKitchenUtensils();
		
		averageRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			averageRetailPriceOfKitchenUtensilsByCurrency.put(currencies.get(i), this.repository.averageRetailPriceOfKitchenUtensilsByCurrency(currencies.get(i)));
		}
		
		deviationRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			deviationRetailPriceOfKitchenUtensilsByCurrency.put(currencies.get(i), this.repository.deviationRetailPriceOfKitchenUtensilsByCurrency(currencies.get(i)));
		}
		
		minRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			minRetailPriceOfKitchenUtensilsByCurrency.put(currencies.get(i), this.repository.minRetailPriceOfKitchenUtensilsByCurrency(currencies.get(i)));
		}
		
		maxRetailPriceOfKitchenUtensilsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			maxRetailPriceOfKitchenUtensilsByCurrency.put(currencies.get(i), this.repository.maxRetailPriceOfKitchenUtensilsByCurrency(currencies.get(i)));
		}
		
		//INGREDIENTS
		
		final int numberOfIngredients;
		final Map<String, Double> averageRetailPriceOfIngredientsByCurrency;
		final Map<String, Double> deviationRetailPriceOfIngredientsByCurrency;
		final Map<String, Double> minRetailPriceOfIngredientsByCurrency;
		final Map<String, Double> maxRetailPriceOfIngredientsByCurrency;
		
		numberOfIngredients = this.repository.numberOfIngredients();
		
		averageRetailPriceOfIngredientsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			averageRetailPriceOfIngredientsByCurrency.put(currencies.get(i), this.repository.averageRetailPriceOfIngredientsByCurrency(currencies.get(i)));
		}
		
		deviationRetailPriceOfIngredientsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			deviationRetailPriceOfIngredientsByCurrency.put(currencies.get(i), this.repository.deviationRetailPriceOfIngredientsByCurrency(currencies.get(i)));
		}
		
		minRetailPriceOfIngredientsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			minRetailPriceOfIngredientsByCurrency.put(currencies.get(i), this.repository.minRetailPriceOfIngredientsByCurrency(currencies.get(i)));
		}
		
		maxRetailPriceOfIngredientsByCurrency = new HashMap<>();
		for(int i = 0; i<currencies.size(); i++) {
			maxRetailPriceOfIngredientsByCurrency.put(currencies.get(i), this.repository.maxRetailPriceOfIngredientsByCurrency(currencies.get(i)));
		}
		
		//FINE DISHES
		
		final Map<Status, Integer> numberOfFineDishesByStatus;
		final Map<Status, Double>	averageBudgetOfFineDishesByStatus;
		final Map<Status, Double> deviationBudgetOfFineDishesByStatus;
		final Map<Status, Double> minBudgetOfFineDishesByStatus;
		final Map<Status, Double> maxBudgetOfFineDishesByStatus;

		numberOfFineDishesByStatus = new EnumMap<>(Status.class);
		for(int i=0; i<Status.values().length; i++) {
			numberOfFineDishesByStatus.put(Status.values()[i], this.repository.numberOfPatronagesByStatus(Status.values()[i]));
		}
		
		averageBudgetOfFineDishesByStatus = new EnumMap<>(Status.class);
		for(int i=0; i<Status.values().length; i++) {
			averageBudgetOfFineDishesByStatus.put(Status.values()[i], this.repository.averageBudgetOfFineDishesByStatus(Status.values()[i]));
		}
		
		deviationBudgetOfFineDishesByStatus = new EnumMap<>(Status.class);
		for(int i=0; i<Status.values().length; i++) {
			deviationBudgetOfFineDishesByStatus.put(Status.values()[i], this.repository.deviationBudgetOfFineDishesByStatus(Status.values()[i]));
		}
		
		minBudgetOfFineDishesByStatus = new EnumMap<>(Status.class);
		for(int i=0; i<Status.values().length; i++) {
			minBudgetOfFineDishesByStatus.put(Status.values()[i], this.repository.minBudgetOfFineDishesByStatus(Status.values()[i]));
		}
		
		maxBudgetOfFineDishesByStatus = new EnumMap<>(Status.class);
		for(int i=0; i<Status.values().length; i++) {
			maxBudgetOfFineDishesByStatus.put(Status.values()[i], this.repository.maxBudgetOfFineDishesByStatus(Status.values()[i]));
		}
		
		//PIMPAMS (CC)
		
		double ratioOfItemsWithPimpams;
		Map<String, Double>	averageBudgetOfPimpamsByCurrency;
		Map<String, Double> deviationBudgetOfPimpamsByCurrency;
		Map<String, Double>	minBudgetOfPimpamsByCurrency;
		Map<String, Double>	maxBudgetOfPimpamsByCurrency;
		
		ratioOfItemsWithPimpams = this.repository.ratioOfItemsWithPimpams(Type.INGREDIENT);
		
		averageBudgetOfPimpamsByCurrency = new HashMap<>();
		for(int i=0; i<currencies.size(); i++) {
			averageBudgetOfPimpamsByCurrency.put(currencies.get(i), this.repository.averageBudgetOfPimpamsByCurrency(currencies.get(i)));
		}
		
		deviationBudgetOfPimpamsByCurrency = new HashMap<>();
		for(int i=0; i<currencies.size(); i++) {
			deviationBudgetOfPimpamsByCurrency.put(currencies.get(i), this.repository.deviationBudgetOfPimpamsByCurrency(currencies.get(i)));
		}
		
		minBudgetOfPimpamsByCurrency = new HashMap<>();
		for(int i=0; i<currencies.size(); i++) {
			minBudgetOfPimpamsByCurrency.put(currencies.get(i), this.repository.minBudgetOfPimpamsByCurrency(currencies.get(i)));
		}
		
		maxBudgetOfPimpamsByCurrency = new HashMap<>();
		for(int i=0; i<currencies.size(); i++) {
			maxBudgetOfPimpamsByCurrency.put(currencies.get(i), this.repository.maxBudgetOfPimpamsByCurrency(currencies.get(i)));
		}
		
		// KITCHEN UTENSILS
		
		result.setNumberOfKitchenUtensils(numberOfKitchenUtensils);
		result.setAverageRetailPriceOfKitchenUtensilsByCurrency(averageRetailPriceOfKitchenUtensilsByCurrency);
		result.setDeviationRetailPriceOfKitchenUtensilsByCurrency(deviationRetailPriceOfKitchenUtensilsByCurrency);
		result.setMinRetailPriceOfKitchenUtensilsByCurrency(minRetailPriceOfKitchenUtensilsByCurrency);
		result.setMaxRetailPriceOfKitchenUtensilsByCurrency(maxRetailPriceOfKitchenUtensilsByCurrency);
		
		//INGREDIENTS
		
		result.setNumberOfIngredients(numberOfIngredients);
		result.setAverageRetailPriceOfIngredientsByCurrency(averageRetailPriceOfIngredientsByCurrency);
		result.setDeviationRetailPriceOfIngredientsByCurrency(deviationRetailPriceOfIngredientsByCurrency);
		result.setMinRetailPriceOfIngredientsByCurrency(minRetailPriceOfIngredientsByCurrency);
		result.setMaxRetailPriceOfIngredientsByCurrency(maxRetailPriceOfIngredientsByCurrency);
		
		//FINE DISHES
		
		result.setNumberOfFineDishesByStatus(numberOfFineDishesByStatus);
		result.setAverageBudgetOfFineDishesByStatus(averageBudgetOfFineDishesByStatus);
		result.setDeviationBudgetOfFineDishesByStatus(deviationBudgetOfFineDishesByStatus);
		result.setMinBudgetOfFineDishesByStatus(minBudgetOfFineDishesByStatus);
		result.setMaxBudgetOfFineDishesByStatus(maxBudgetOfFineDishesByStatus);
		
		//PIMPAMS (CC)
		
		result.setRatioOfItemsWithPimpams(ratioOfItemsWithPimpams);
		result.setAverageBudgetOfPimpamsByCurrency(averageBudgetOfPimpamsByCurrency);
		result.setDeviationBudgetOfPimpamsByCurrency(deviationBudgetOfPimpamsByCurrency);
		result.setMinBudgetOfPimpamsByCurrency(minBudgetOfPimpamsByCurrency);
		result.setMaxBudgetOfPimpamsByCurrency(maxBudgetOfPimpamsByCurrency);
		
		return result;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfKitchenUtensils", "averageRetailPriceOfKitchenUtensilsByCurrency", "deviationRetailPriceOfKitchenUtensilsByCurrency", //
			"minRetailPriceOfKitchenUtensilsByCurrency","maxRetailPriceOfKitchenUtensilsByCurrency", //
			"numberOfIngredients", "averageRetailPriceOfIngredientsByCurrency", "deviationRetailPriceOfIngredientsByCurrency", //
			"minRetailPriceOfIngredientsByCurrency","maxRetailPriceOfIngredientsByCurrency", //
			"numberOfFineDishesByStatus","averageBudgetOfFineDishesByStatus","deviationBudgetOfFineDishesByStatus", //
			"minBudgetOfFineDishesByStatus", "maxBudgetOfFineDishesByStatus"
			,"ratioOfItemsWithPimpams", "averageBudgetOfPimpamsByCurrency", "deviationBudgetOfPimpamsByCurrency", "minBudgetOfPimpamsByCurrency", "maxBudgetOfPimpamsByCurrency");
		
		this.library.createDashboard(entity, model);
		
	}

}