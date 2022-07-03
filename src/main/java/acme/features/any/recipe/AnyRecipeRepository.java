package acme.features.any.recipe;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyRecipeRepository extends AbstractRepository{

	@Query("select r from Recipe r where r.id = :id")
	Recipe findOneById(int id);
		
	@Query("select r from Recipe r where r.published = 1")
	Collection<Recipe> findPublished();
	
	//QUEY PARA MOSTRAR EL PAYLOAD
	
	@Query("select q.item from Quantity q where q.recipe.id = :recipeId")
	Collection<Item> findManyItemsByRecipeId(int recipeId);
	
	//QUERYS NECESARIAS PARA MOSTRAR LOS ITEMS DE SUS RECIPES
	
	@Query("select q from Quantity q where q.recipe.id = :id")
	Collection<Quantity> findManyQuantitiesByRecipeId(int id);
	
	@Query("select q.item from Quantity q where q.id = :id")
	Collection<Item> findManyItemsByQuantityId(int id);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
	
}