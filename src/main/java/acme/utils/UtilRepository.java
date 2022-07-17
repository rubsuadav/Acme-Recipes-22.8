package acme.utils;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface UtilRepository extends AbstractRepository{
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	@Query("select me from MoneyExchange me where me.source.currency = :currency and me.source.amount = :amount")
	MoneyExchange findMoneyExchangeByCurrencyAndAmount(String currency, Double amount);

}
