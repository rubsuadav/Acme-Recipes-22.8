package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractShowService<Administrator, Toolkit> interface --------------

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int toolkitId;
		Toolkit toolkit;

		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneById(toolkitId);
		result = toolkit.getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int toolkitId;

		toolkitId = request.getModel().getInteger("id");
		result = this.repository.findOneById(toolkitId);

		result.setRetailPrice(this.totalPriceOfToolkit(toolkitId));

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		final String retailPrice = entity.getRetailPrice().toString().replace("<<", "").replace(">>", "");

		model.setAttribute("price", retailPrice);

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");

		String result = "";

		result = entity.isPublished() ? "The toolkit is published": "The toolkit is not published";
		model.setAttribute("published", result);
	}
	
	//IMPLEMENTACION CACHÉ DE CONVERSIONES

	/**
	 * @param money
	 * @return conversiones de divisas haciendo uso de la cache
	 */
	protected MoneyExchange conversion(final Money money) {

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

	/**
	 * @param toolkitId
	 * @return the total price of the toolkit with his currency
	 */
	private Money totalPriceOfToolkit(final int toolkitId) {
		final Money result = new Money();
		result.setAmount(0.0);
		result.setCurrency(this.repository.findSystemConfiguration().getSystemCurrency());

		final Collection<Quantity> quantities = this.repository.findManyQuantitiesByToolkitId(toolkitId);

		for(final Quantity quantity: quantities) {
			final double conversionAmount;
			final Money itemMoney = quantity.getItem().getRetailPrice();
			final int number = quantity.getNumber();

			conversionAmount = this.conversion(itemMoney).getTarget().getAmount();

			final Double newAmount = (double) Math.round((result.getAmount() + conversionAmount*number)*100)/100;
			result.setAmount(newAmount);
		}
		
		return result;
	}

}