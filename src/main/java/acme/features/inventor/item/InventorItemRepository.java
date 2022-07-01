package acme.features.inventor.item;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.toolkits.Quantity;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findOneById(int id);

	@Query("select i from Item i where i.inventor.id = :inventorId")
	Collection<Item> findManyItemsByInventorId(int inventorId);
	
	//QUERYS NECESARIAS PARA EL CREATE

	@Query("select i from Inventor i where i.id = :inventorId")
	Inventor findOneInventorById(int inventorId);

	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	//QUERY PARA EL DELETE
	
	@Query("select q from Quantity q where q.item.id = :id")
	Collection<Quantity> findManyQuantitiesByItemId(int id);

	//QUERY PARA EL SHOW PARA IMPLEMENTAR LA CACHÉ
	
	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
	MoneyExchange findMoneyExchangeByCurrencyAndAmount(String currency, Double amount);

}