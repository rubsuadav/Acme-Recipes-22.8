package acme.features.chef.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefRecipeRepository extends AbstractRepository {

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneById(int id);

	@Query("select r from Recipe r where r.chef.id = :chefId")
	Collection<Recipe> findManyRecipesByChefId(int chefId);
	
	//QUERYS NECESARIAS PARA MOSTRAR LOS ITEMS DE SUS RECETAS
	
	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findManyQuantitiesByRecipeId(int id);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select q.item from Quantity q where q.id = :id")
	Collection<Item> findManyItemsByQuantityId(int id);
	
	//QUERYS NECESARIAS PARA EL CREATE
	
//	@Query("select i from Inventor i where i.id = :inventorId")
//	Inventor findOneInventorById(int inventorId);
//	
//	@Query("select t from Recipe t where t.code = :code")
//	Recipe findOneRecipeByCode(String code);
//
//	@Query("select i from Item i where i.id = :itemId")
//	Item finOneItemById(int itemId);
//
//	@Query("select i from Item i where i.published = 1 and "
//		+ "i not in (select q.item from Quantity q where q.Recipe.id = :id)")
//	Collection<Item> findAllIPossibletems(int id);

	//QUERY PARA EL SHOW PARA IMPLEMENTAR LA CACHÉ
	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
	MoneyExchange findMoneyExchangeByCurrencyAndAmount(String currency, Double amount);
	
	//QUERY PARA EL SHOW DE LAS QUANTITIES
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(int id);
	
}