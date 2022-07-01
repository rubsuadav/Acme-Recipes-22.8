package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit> {

	@Autowired
	protected AnyToolkitRepository repository;

	// AbstractShowService<Any, Item> interface --------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		boolean result;
		int toolkitId;
		Toolkit toolkit;
		
		toolkitId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneById(toolkitId);
		result = toolkit.isPublished();

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
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
		
		String result = "";

		result = entity.isPublished() ? "The toolkit is published": "The toolkit is not published";
        model.setAttribute("published", result);
	}
	
	/**
	 * @param toolkitId
	 * @return the total price of the toolkit with his currency
	 */
	private Money totalPriceOfToolkit(final int toolkitId) {
		final Money result = new Money();
		result.setAmount(0.0);
		result.setCurrency("EUR");
		final AuthenticatedMoneyExchangePerformService moneyExange = new AuthenticatedMoneyExchangePerformService();
		final Collection<Quantity> quantities = this.repository.findManyQuantitiesByToolkitId(toolkitId);
		
		for(final Quantity quantity: quantities) {
			final Money itemMoney = quantity.getItem().getRetailPrice();
			final int number = quantity.getNumber();
			final String systemCurrency = this.repository.findSystemCurrency();
			final MoneyExchange itemMoneyExchanged = moneyExange.computeMoneyExchange(itemMoney, systemCurrency);
			final double newNumber = result.getAmount() + itemMoneyExchanged.getTarget().getAmount()*number;
			result.setAmount(newNumber);
		}
		
		return result;
	}
}