package acme.features.chef.quantity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.Type;
import acme.entities.recipes.Quantity;
import acme.entities.recipes.Recipe;
import acme.features.chef.recipe.ChefRecipeRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefQuantityCreateService implements AbstractCreateService<Chef, Quantity> {
	
	@Autowired
	protected ChefRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;

		boolean result;
		int masterId;
		Recipe recipe;

		masterId = request.getModel().getInteger("masterId");
		recipe = this.repository.findOneById(masterId);
		result = (recipe != null && !recipe.isPublished() && request.isPrincipal(recipe.getChef()));

		return result;
		
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(this.repository.findAllIPossibletems(entity.getRecipe().getId()).isEmpty()) {
			request.bind(entity, errors, "number");
		} else {
			entity.setItem(this.repository.finOneItemById(Integer.valueOf( request.getModel().getAttribute("itemId").toString())));
			request.bind(entity, errors, "number", "itemId");
		}

	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		model.setAttribute("masterId", request.getModel().getAttribute("masterId"));

		model.setAttribute("items", this.repository.findAllIPossibletems(entity.getRecipe().getId()));
		request.unbind(entity, model, "number");
		
	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;

		Quantity quantity;
		int recipeId;
		Recipe recipe;

		quantity = new Quantity();

		recipeId = request.getModel().getInteger("masterId");
		recipe = this.repository.findOneById(recipeId);

		quantity.setRecipe(recipe);

		return quantity;
	
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert errors != null;

		if(entity.getItem() == null) {
			errors.state(request, entity.getItem() != null, "itemId", "chef.quantity.form.error.noItem");
		} else {
			final Item selectedItem = this.repository.finOneItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString()));
			if(selectedItem.getTypeEntity().equals(Type.INGREDIENT)) {
				errors.state(request,entity.getNumber() == 1, "number", "chef.quantity.form.error.recipe-has-ingredient");
			}

		}
		
	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}