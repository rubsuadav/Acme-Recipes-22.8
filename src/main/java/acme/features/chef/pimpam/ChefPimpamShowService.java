package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.pimpams.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;
import acme.utils.ConversionExchange;

@Service
public class ChefPimpamShowService implements AbstractShowService<Chef, Pimpam> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPimpamRepository repository;
	
	@Autowired
	protected ConversionExchange exchange;

	// AbstractShowService<Administrator, Chimpum> interface --------------

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;

		return true;
	}

	@Override
	public Pimpam findOne(final Request<Pimpam> request) {
		assert request != null;

		Pimpam result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePimpamById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//IMPLEMENTACION CACHÉ DE CONVERSIONES

		final MoneyExchange conversion = this.exchange.conversion(entity.getBudget());

		model.setAttribute("conversion", conversion.getTarget());
		
		request.unbind(entity, model, "code", "instantiationMoment", "title", "desciption", "initial", "end", "budget", "link");
		
		if (this.repository.findOneItemByPimpamId(entity.getId()).isPublished()) {
			model.setAttribute("canUpdateDelete", false);
		} else {
			model.setAttribute("canUpdateDelete", true);
		}

	}
}