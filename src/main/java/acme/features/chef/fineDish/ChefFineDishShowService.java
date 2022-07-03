package acme.features.chef.fineDish;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.fineDishes.FineDish;
import acme.features.authenticated.moneyExchange.AuthenticatedMoneyExchangePerformService;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefFineDishShowService implements AbstractShowService<Chef, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefFineDishRepository repository;

	// AbstractShowService<Administrator, FineDish> interface --------------


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		int fineDishId;
		FineDish fineDish;

		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneById(fineDishId);
		result = fineDish.getChef().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public FineDish findOne(final Request<FineDish> request) {
		assert request != null;

		FineDish result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void unbind(final Request<FineDish> request, final FineDish entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//THE PROFILE OF THE EPICURE

		model.setAttribute("epicureProfileFullName", entity.getEpicure().getIdentity().getFullName());
		model.setAttribute("epicureProfileEmail", entity.getEpicure().getIdentity().getEmail());
		model.setAttribute("epicureCompany", entity.getEpicure().getOrganisation());
		model.setAttribute("epicureStatement", entity.getEpicure().getAssertion());
		model.setAttribute("epicureLink", entity.getEpicure().getLink());

		//IMPLEMENTACION CACHÉ DE CONVERSIONES

		final MoneyExchange conversion = this.conversion(entity.getBudget());

		model.setAttribute("conversion", conversion.getTarget());

		request.unbind(entity, model,"status","code","request","budget","initial","creation","end","link","published");

	}

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

}