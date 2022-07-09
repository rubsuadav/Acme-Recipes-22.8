package acme.features.chef.memorandum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDishes.FineDish;
import acme.entities.memorandums.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefMemorandumRepository extends AbstractRepository{

	@Query("select m from Memorandum m where m.fineDish.chef.id = :chefId")
	Collection<Memorandum> findManyMemorandumsByChefId(int chefId);
	
	@Query("select m from Memorandum m where m.id = :id")
	Memorandum findOneById(int id);
	
	@Query("select f from FineDish f where f.id = :fineDishId")
	FineDish findOneFineDishById(int fineDishId);
	
	@Query("select count(m) from Memorandum m where m.fineDish.code = :code")
	int countMemorandumsWithFineDishCode(String code);
	
}
