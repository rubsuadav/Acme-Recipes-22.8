package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository{

	@Query("select i from Toolkit i where i.id = :id")
	Toolkit findOneById(int id);
		
	@Query("select i from Toolkit i where i.published = 1")
	Collection<Toolkit> findPublished();
	
	//QUEY PARA MOSTRAR EL PAYLOAD
	
	@Query("select q.item from Quantity q where q.toolkit.id = :toolkitId")
	Collection<Item> findManyItemsByToolkitId(int toolkitId);
	
	//QUERYS NECESARIAS PARA MOSTRAR LOS ITEMS DE SUS TOOLKITS
	
	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findManyQuantitiesByToolkitId(int id);
	
	@Query("select q.item from Quantity q where q.id = :id")
	Collection<Item> findManyItemsByQuantityId(int id);
	
	@Query("select sc.systemCurrency from SystemConfiguration sc")
	String findSystemCurrency();
	
}