package acme.features.any.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.MoneyExchange;
import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;
import acme.utils.ConversionExchange;

@Service
public class AnyItemShowService implements AbstractShowService<Any, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemRepository repository;

	@Autowired
	protected ConversionExchange exchange;

	// AbstractShowService<Any, Item> interface --------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int itemId;
		Item item;

		itemId = request.getModel().getInteger("id");
		item = this.repository.findOneById(itemId);
		result = item.isPublished();

		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		//IMPLEMENTACION CACHÉ DE CONVERSIONES

		final MoneyExchange conversion = this.exchange.conversion(entity.getRetailPrice());

		model.setAttribute("conversion", conversion.getTarget());

		request.unbind(entity, model, "typeEntity", "name", "code", "description", "retailPrice", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}

}
