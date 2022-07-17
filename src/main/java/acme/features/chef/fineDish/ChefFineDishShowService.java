package acme.features.chef.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.fineDishes.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;
import acme.utils.ConversionExchange;

@Service
public class ChefFineDishShowService implements AbstractShowService<Chef, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefFineDishRepository repository;
	
	@Autowired
	protected ConversionExchange exchange;

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

		final MoneyExchange conversion = this.exchange.conversion(entity.getBudget());

		model.setAttribute("conversion", conversion.getTarget());

		request.unbind(entity, model,"status","code","request","budget","initial","creation","end","link","published");

	}
	
}