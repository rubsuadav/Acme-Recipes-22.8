package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findOneById(int id);

	@Query("select t from Toolkit t where t.inventor.id = :inventorId")
	Collection<Toolkit> findManyToolkitsByInventorId(int inventorId);
	
	//QUERYS NECESARIAS PARA MOSTRAR LOS ITEMS DE SUS TOOLKITS
	
	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findManyQuantitiesByToolkitId(int id);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select q.item from Quantity q where q.id = :id")
	Collection<Item> findManyItemsByQuantityId(int id);
	
	//QUERYS NECESARIAS PARA EL CREATE
	
	@Query("select i from Inventor i where i.id = :inventorId")
	Inventor findOneInventorById(int inventorId);
	
	@Query("select t from Toolkit t where t.code = :code")
	Toolkit findOneToolkitByCode(String code);

	@Query("select i from Item i where i.id = :itemId")
	Item finOneItemById(int itemId);

	@Query("select i from Item i where i.published = 1 and "
		+ "i not in (select q.item from Quantity q where q.toolkit.id = :id)")
	Collection<Item> findAllIPossibletems(int id);

	//QUERY PARA EL SHOW PARA IMPLEMENTAR LA CACHÉ
	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
	MoneyExchange findMoneyExchangeByCurrencyAndAmount(String currency, Double amount);
	
	//QUERY PARA EL SHOW DE LAS QUANTITIES
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findOneQuantityById(int id);
	
}