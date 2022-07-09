package acme.features.epicure.fineDish;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.fineDishes.FineDish;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureFineDishRepository extends AbstractRepository {

	@Query("select f from FineDish f where f.id = :id")
	FineDish findOneById(int id);

	@Query("select f from FineDish f where f.epicure.id = :epicureId")
	Collection<FineDish> findManyFineDishesByEpicureId(int epicureId);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
	MoneyExchange findMoneyExchangeByCurrencyAndAmount(String currency, Double amount);

	//QUERYS PARA EL CREATE

//	@Query("select e from Epicure e where e.id = :epicureId")
//	Epicure findOneEpicureById(int epicureId);
//
//	@Query("select f from FineDish f where f.code = :code")
//	FineDish findOneFineDishByCode(String code);
//
//	@Query("select c from Chef c")
//	Collection<Chef> findAllChefs();
//
//	@Query("select c from Chef c where c.id = :chefId")
//	Chef finOneChefById(int chefId);
//
//	@Query("select sc from SystemConfiguration sc")
//	SystemConfiguration findSystemConfiguration();

	//QUERY PARA EL DELETE

//	@Query("Select m from Memorandum m where m.fineDish.id = :id") 
//	Collection<Memorandum> findMemorandumByFineDishId(int id);

}
