package acme.features.chef.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.recipes.Quantity;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface ChefItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findOneById(int id);

	@Query("select i from Item i where i.chef.id = :chefId")
	Collection<Item> findManyItemsByChefId(int chefId);
	
	//QUERYS NECESARIAS PARA EL CREATE

	@Query("select i from Chef i where i.id = :chefId")
	Chef findOneChefById(int chefId);

	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	//QUERY PARA EL DELETE
	
	@Query("select q from Quantity q where q.item.id = :id")
	Collection<Quantity> findManyQuantitiesByItemId(int id);

}