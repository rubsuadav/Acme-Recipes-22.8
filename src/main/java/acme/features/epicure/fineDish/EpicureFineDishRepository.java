package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.SystemConfiguration;
import acme.entities.fineDishes.FineDish;
import acme.entities.memorandums.Memorandum;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;
import acme.roles.Epicure;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneById(int id);

	@Query("select f from FineDish f where f.epicure.id = :epicureId")
	Collection<FineDish> findManyFineDishesByEpicureId(int epicureId);

	//QUERYS PARA EL CREATE

	@Query("select e from Epicure e where e.id = :epicureId")
	Epicure findOneEpicureById(int epicureId);

	@Query("select f from FineDish f where f.code = :code")
	FineDish findOneFineDishByCode(String code);

	@Query("select c from Chef c")
	Collection<Chef> findAllChefs();

	@Query("select c from Chef c where c.id = :chefId")
	Chef finOneChefById(int chefId);

	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

	//QUERY PARA EL DELETE

	@Query("Select m from Memorandum m where m.fineDish.id = :id") 
	Collection<Memorandum> findMemorandumByFineDishId(int id);

}
