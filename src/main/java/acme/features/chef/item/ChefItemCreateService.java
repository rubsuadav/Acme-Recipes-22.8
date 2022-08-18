package acme.features.chef.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefItemCreateService implements AbstractCreateService<Chef, Item>{

	@Autowired
	protected ChefItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "typeEntity", "name", "code", "description", "retailPrice", "link");

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "typeEntity", "name", "code", "description", "retailPrice", "link", "published");

	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;

		Item result;
		Chef chef;

		chef = this.repository.findOneChefById(request.getPrincipal().getActiveRoleId());
		result = new Item();

		result.setChef(chef);

		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findOneItemByCode(entity.getCode());
			errors.state(request, existing == null, "code", "chef.item.form.error.duplicated");
		}

		if (!errors.hasErrors("retailPrice")) {
			final String[] currencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			boolean acceptedCurrencies = false;
			for(int i = 0; i< currencies.length; i++) {
				if(entity.getRetailPrice().getCurrency().equals(currencies[i].trim())) {
					acceptedCurrencies=true;
				}
			}

			errors.state(request, acceptedCurrencies, "retailPrice", "chef.item.form.error.non-accepted-currency");
			errors.state(request, entity.getRetailPrice().getAmount() > 0, "retailPrice", "chef.item.form.error.negative-retail-price");
		}
		
		final SystemConfiguration configuration = this.repository.findSystemConfiguration();

		if (!errors.hasErrors("name")) {
			errors.state(request, SpamFilter.spamValidator(entity.getName(), configuration.getWeakSpamWords(), configuration.getStrongSpamWords(), 
														   configuration.getWeakSpamThreshold(), configuration.getStrongSpamThreshold()), 
																			"name", "form.error.spam");
		}
		
		if (!errors.hasErrors("description")) {
			errors.state(request, SpamFilter.spamValidator(entity.getDescription(), configuration.getWeakSpamWords(), configuration.getStrongSpamWords(),
															configuration.getWeakSpamThreshold(), configuration.getStrongSpamThreshold()), 
																			"description", "form.error.spam");
		}

	}

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}