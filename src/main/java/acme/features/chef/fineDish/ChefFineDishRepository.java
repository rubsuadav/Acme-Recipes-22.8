package acme.features.chef.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.fineDishes.FineDish;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefFineDishRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneById(int id);

	@Query("select f from FineDish f where f.chef.id = :chefId and f.published = 1")
	Collection<FineDish> findManyFineDishesByChefId(int chefId);

}
