package acme.features.inventor.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.Type;
import acme.entities.toolkits.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.features.inventor.toolkit.InventorToolkitRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitItemUpdateService implements AbstractUpdateService<Inventor, Quantity>{

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
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "number"); 
		
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		String result = "";

		result = entity.getItem().isPublished() ? "The item is published": "The item is not published";
		model.setAttribute("published", result);
		
		request.unbind(entity, model, "number", "item.typeEntity", "item.name", "item.code", "item.technology", "item.description", 
			"item.retailPrice", "item.link");
		
		model.setAttribute("toolkitPublished", entity.getToolkit().isPublished());
		
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

	@Override 
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) { 
		assert request != null; 
		assert entity != null; 
		assert errors != null;
		
		final Item selectedItem = entity.getItem();
		
		if(selectedItem.getTypeEntity().equals(Type.TOOL)) {
			errors.state(request,entity.getNumber() == 1, "number", "inventor.quantity.form.error.toolkit-has-tool");
		}
		
	} 

	@Override
	public void update(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}