package acme.features.inventor.toolkit;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
		Inventor inventor;

		masterId = request.getModel().getInteger("id");
		toolkit = this.repository.findOneById(masterId);
		inventor = toolkit.getInventor();
		result = !toolkit.isPublished() && request.isPrincipal(inventor);

		return result;

	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "title", "description", "assemblyNotes", "link");

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link", "published");

	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int toolkitId;

		toolkitId = request.getModel().getInteger("id");
		result = this.repository.findOneById(toolkitId);

		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findOneToolkitByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.item.form.error.duplicated");
		}

		int toolkitid;
		toolkitid = request.getModel().getInteger("id");
		final Collection<Quantity> quantities = this.repository.findManyQuantitiesByToolkitId(toolkitid);
		final Collection<Item> items = new HashSet<Item>();
		boolean publishItem = true;
		
		for(final Quantity quantity: quantities) {
			final int id=quantity.getId();
			final Collection<Item> item=this.repository.findManyItemsByQuantityId(id);
			items.addAll(item);
		}
		
		errors.state(request, !items.isEmpty(), "*", "inventor.toolkit.form.error.no-items");
		
		for (final Item item : items) {
			publishItem= publishItem && item.isPublished();
		}
		errors.state(request, publishItem, "*", "inventor.toolkit.form.error.no-items-published");
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		this.repository.save(entity);

	}

}