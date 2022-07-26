package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDishes.Status;
import acme.entities.items.Type;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{

	@Query("SELECT c.acceptedCurrencies FROM SystemConfiguration c")
	String getAcceptedCurrencies();
	
	//KITCHEN UTENSILS
	
	@Query("SELECT COUNT(i) FROM Item i WHERE i.typeEntity = acme.entities.items.Type.KITCHENUTENSIL")
	Integer numberOfKitchenUtensils();
	
	@Query("SELECT AVG(i.retailPrice.amount) FROM Item i WHERE (i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.KITCHENUTENSIL)")
	Double averageRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	@Query("SELECT STDDEV(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.KITCHENUTENSIL")
	Double deviationRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	@Query("SELECT MIN(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.KITCHENUTENSIL ")
	Double minRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	@Query("SELECT MAX(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.KITCHENUTENSIL")
	Double maxRetailPriceOfKitchenUtensilsByCurrency(String currency);
	
	//INGREDIENTS
	
	@Query("SELECT COUNT(i) FROM Item i WHERE i.typeEntity = acme.entities.items.Type.INGREDIENT")
	Integer numberOfIngredients();
	
	@Query("SELECT AVG(i.retailPrice.amount) FROM Item i WHERE (i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.INGREDIENT)")
	Double averageRetailPriceOfIngredientsByCurrency(String currency);
	
	@Query("SELECT STDDEV(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.INGREDIENT")
	Double deviationRetailPriceOfIngredientsByCurrency(String currency);
	
	@Query("SELECT MIN(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.INGREDIENT ")
	Double minRetailPriceOfIngredientsByCurrency(String currency);
	
	@Query("SELECT MAX(i.retailPrice.amount) FROM Item i WHERE i.retailPrice.currency = :currency AND i.typeEntity = acme.entities.items.Type.INGREDIENT")
	Double maxRetailPriceOfIngredientsByCurrency(String currency);
	
	// FINE DISHES
	
	@Query("SELECT COUNT(p) FROM FineDish p WHERE p.status = :status")
	Integer numberOfPatronagesByStatus(Status status);

	@Query("SELECT AVG(p.budget.amount) FROM FineDish p WHERE p.status = :status")
	Double averageBudgetOfFineDishesByStatus(Status status);
	
	@Query("SELECT STDDEV(p.budget.amount) FROM FineDish p WHERE p.status = :status")
	Double deviationBudgetOfFineDishesByStatus(Status status);
	
	@Query("SELECT MIN(p.budget.amount) FROM FineDish p WHERE p.status = :status")
	Double minBudgetOfFineDishesByStatus(Status status);
	
	@Query("SELECT MAX(p.budget.amount) FROM FineDish p WHERE p.status = :status")
	Double maxBudgetOfFineDishesByStatus(Status status);
	
	//PIMPAMS (CC)
	
	@Query("SELECT (("+ "SELECT COUNT(r) FROM Pimpam r" + ")/COUNT(i))*100 FROM Item i WHERE i.typeEntity = :typeEntity")
	Double ratioOfItemsWithPimpams(Type typeEntity);
	
	@Query("SELECT AVG(r.budget.amount) FROM Pimpam r WHERE r.budget.currency = :currency")
	Double averageBudgetOfPimpamsByCurrency(String currency);
	
	@Query("SELECT STDDEV(r.budget.amount) FROM Pimpam r WHERE r.budget.currency = :currency")
	Double deviationBudgetOfPimpamsByCurrency(String currency);
	
	@Query("SELECT MIN(r.budget.amount) FROM Pimpam r WHERE r.budget.currency = :currency")
	Double minBudgetOfPimpamsByCurrency(String currency);
	
	@Query("SELECT MAX(r.budget.amount) FROM Pimpam r WHERE r.budget.currency = :currency")
	Double maxBudgetOfPimpamsByCurrency(String currency);
	
}