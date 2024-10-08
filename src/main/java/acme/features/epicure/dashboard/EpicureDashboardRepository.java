package acme.features.epicure.dashboard;

import java.util.Collection;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDishes.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureDashboardRepository extends AbstractRepository{

	@Query("SELECT COUNT(p) FROM FineDish p WHERE p.status = :status")
	Integer numberOfPatronagesByStatus(Status status);

	@Query("SELECT p.budget.currency, AVG(p.budget.amount) FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	Collection<Tuple> averageNumberOfBudgetsByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, STDDEV(p.budget.amount) FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	Collection<Tuple> deviationOfBudgetsByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, MIN(p.budget.amount) FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	Collection<Tuple> minBudgetByCurrencyAndStatus(Status status);
	
	@Query("SELECT p.budget.currency, MAX(p.budget.amount) FROM FineDish p WHERE p.status = :status GROUP BY p.budget.currency")
	Collection<Tuple> maxBudgetByCurrencyAndStatus(Status status);
	
}
