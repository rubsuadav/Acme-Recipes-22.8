package acme.forms;

import java.io.Serializable;
import java.util.Map;

import acme.entities.fineDishes.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	protected static final long	serialVersionUID= 1L;

	// Attributes -------------------------------------------------------------
	
	//KITCHEN UTENSILS

	int	numberOfKitchenUtensils;
	Map<String, Double> averageRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double> deviationRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double> minRetailPriceOfKitchenUtensilsByCurrency;
	Map<String, Double> maxRetailPriceOfKitchenUtensilsByCurrency;
	
	//INGREDIENTS
	
	int	numberOfIngredients;
	Map<String, Double>	averageRetailPriceOfIngredientsByCurrency;
	Map<String, Double> deviationRetailPriceOfIngredientsByCurrency;
	Map<String, Double>	minRetailPriceOfIngredientsByCurrency;
	Map<String, Double>	maxRetailPriceOfIngredientsByCurrency;
	
	//FINE DISHES
	
	Map<Status, Integer> numberOfFineDishesByStatus;
	Map<Status, Double>	averageBudgetOfFineDishesByStatus;
	Map<Status, Double> deviationBudgetOfFineDishesByStatus;
	Map<Status, Double> minBudgetOfFineDishesByStatus;
	Map<Status, Double> maxBudgetOfFineDishesByStatus;
}
