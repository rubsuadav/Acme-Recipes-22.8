package acme.features.inventor.quantity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemShowService implements AbstractShowService<Inventor, Quantity>{

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int toolkitId;
		int quantityId;
		Quantity quantity;
		Toolkit toolkit;

		quantityId = request.getModel().getInteger("id");
		quantity = this.repository.findOneQuantityById(quantityId);
		toolkitId= quantity.getToolkit().getId();
		toolkit = this.repository.findOneById(toolkitId);
		result = toolkit.getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
		
	}

	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;

		Quantity result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneQuantityById(id);

		return result;
	}

	/**
	 * @param money
	 * @return realiza conversiones de divisas. Si la divisa del objeto money que se pasa como parámetro
	 * es diferente de la divisa predeterminada de la configuración del sistema, entonces se obtiene o calcula 
	 * la conversión. En caso contrario, no es necesario realizar una conversión, por lo que los Money fuente y destino +
	 * son iguales. 
	 */
	protected MoneyExchange conversion(final Money money) {

		final AuthenticatedMoneyExchangePerformService moneyExchange = new AuthenticatedMoneyExchangePerformService();

		MoneyExchange conversion = new MoneyExchange();

		final SystemConfiguration systemConfiguration = this.repository.findSystemConfiguration();

		if(!money.getCurrency().equals(systemConfiguration.getSystemCurrency())) {
			conversion = this.repository.findMoneyExchangeByCurrencyAndAmount(money.getCurrency(), money.getAmount());

			if(conversion == null) {
				conversion = moneyExchange.computeMoneyExchange(money, systemConfiguration.getSystemCurrency());
				this.repository.save(conversion);
			}

		} else {
			conversion.setSource(money);
			conversion.setTarget(money);
			conversion.setCurrencyTarget(systemConfiguration.getSystemCurrency());
			conversion.setDate(new Date(System.currentTimeMillis()));

		}

		return conversion;

	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//IMPLEMENTACION CACHÉ DE CONVERSIONES

		final MoneyExchange conversion = this.conversion(entity.getItem().getRetailPrice());

		model.setAttribute("toolkitPublished", entity.getToolkit().isPublished());
		model.setAttribute("conversion", conversion.getTarget());
		
		String result = "";

		result = entity.getItem().isPublished() ? "The item is published": "The item is not published";
		
		model.setAttribute("published", result);

		request.unbind(entity, model, "number", "item.typeEntity", "item.name", "item.code", "item.technology", "item.description", 
			"item.retailPrice", "item.link");

	}
	
}