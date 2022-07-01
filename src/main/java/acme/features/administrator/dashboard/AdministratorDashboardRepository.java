package acme.features.administrator.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository{
	
	@Query("select count(i) from Item i where i.typeEntity = acme.entities.items.Type.COMPONENT")
	Integer numberOfComponents();
	
	@Query("select i.technology, avg(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.COMPONENT group by i.technology")
	Collection<Tuple> averageRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("select i.technology, stddev(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.COMPONENT group by i.technology")
	Collection<Tuple> deviationRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("select i.technology, min(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.COMPONENT group by i.technology")
	Collection<Tuple> minRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("select i.technology, max(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.COMPONENT group by i.technology")
	Collection<Tuple> maxRetailPriceOfComponentsByTechnologyAndCurrency(String currency);
	
	@Query("select count(i) from Item i where i.typeEntity = acme.entities.items.Type.TOOL")
	Integer numberOfTools();
	
	@Query("select avg(i.retailPrice.amount) from Item i where (i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.TOOL)")
	Double averageRetailPriceOfToolsByCurrency(String currency);
	
	@Query("select stddev(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.TOOL")
	Double deviationRetailPriceOfToolsByCurrency(String currency);
	
	@Query("select min(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.TOOL ")
	Double minRetailPriceOfToolsByCurrency(String currency);
	
	@Query("select max(i.retailPrice.amount) from Item i where i.retailPrice.currency = :currency and i.typeEntity = acme.entities.items.Type.TOOL")
	Double maxRetailPriceOfToolsByCurrency(String currency);
	
	@Query("select count(p) from Patronage p where p.status = :status")
	Integer numberOfPatronagesByStatus(Status status);
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = :status")
	Double averageBudgetOfPatronagesByStatus(Status status);
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = :status")
	Double deviationBudgetOfPatronagesByStatus(Status status);
	
	@Query("select min(p.budget.amount) from Patronage p where p.status = :status")
	Double minBudgetOfPatronagesByStatus(Status status);
	
	@Query("select max(p.budget.amount) from Patronage p where p.status = :status")
	Double maxBudgetOfPatronagesByStatus(Status status);
}