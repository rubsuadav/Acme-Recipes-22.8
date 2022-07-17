package acme.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.datatypes.Money;

@Service
public class ConversionExchange {
	
	@Autowired
	protected UtilRepository repository;

	/**
	 * @param money
	 * @return conversiones de divisas haciendo uso de la cache
	 */
	public MoneyExchange conversion(final Money money) {

		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();

		MoneyExchange conversion = new MoneyExchange();

		final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();

		//Si la divisa es diferente de la divisa predeterminada de la configuración del sistema, entonces pueden ocurrir 2 cosas:

		if(!money.getCurrency().equals(systemConfiguration.getSystemCurrency())) {
			conversion = this.repository.findMoneyExchangeByCurrencyAndAmount(money.getCurrency(), money.getAmount());

			//Se obtiene o calcula  la conversión

			if(conversion == null) {
				conversion = moneyExchange.computeMoneyExchange(money, systemConfiguration.getSystemCurrency());
				this.repository.save(conversion);

			}

			//En caso contrario, no es necesario realizar una conversión 

		} else {
			conversion.setSource(money);
			conversion.setTarget(money);
			conversion.setCurrencyTarget(systemConfiguration.getSystemCurrency());
			conversion.setDate(new Date(System.currentTimeMillis()));

		}

		return conversion;

	}
	
}