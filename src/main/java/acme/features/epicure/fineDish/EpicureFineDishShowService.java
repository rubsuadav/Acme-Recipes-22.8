package acme.features.epicure.fineDish;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.fineDishes.FineDish;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;
import acme.utils.ConversionExchange;

@Service
public class EpicureFineDishShowService implements AbstractShowService<Epicure, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishRepository repository;
	
	@Autowired
	protected ConversionExchange exchange;

	// AbstractShowService<Epicure, FineDish> interface --------------


	@Override
	public boolean authorise(final Request<FineDish> request) {
		assert request != null;

		boolean result;
		int fineDishId;
		FineDish fineDish;

		fineDishId = request.getModel().getInteger("id");
		fineDish = this.repository.findOneById(fineDishId);
		result = fineDish.getEpicure().getId() == request.getPrincipal().getActiveRoleId();

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

		//THE PROFILE OF THE CHEF

		model.setAttribute("chefCompany", entity.getChef().getOrganisation());
		model.setAttribute("chefStatement", entity.getChef().getAssertion());
		model.setAttribute("chefLink", entity.getChef().getLink());

		request.unbind(entity, model,"status","code","request","budget","initial","creation","end","link","published");

		//IMPLEMENTACION CACHÉ DE CONVERSIONES

		final MoneyExchange conversion = this.exchange.conversion(entity.getBudget());

		model.setAttribute("conversion", conversion.getTarget());
		
	}
	
}