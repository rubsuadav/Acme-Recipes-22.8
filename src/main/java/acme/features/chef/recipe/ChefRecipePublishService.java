package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefRecipePublishService implements AbstractUpdateService<Chef, Recipe>{

	@Autowired
	protected ChefRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;

		boolean result;
		int masterId;
		Recipe recipe;
		Chef chef;

		masterId = request.getModel().getInteger("id");
		recipe = this.repository.findOneById(masterId);
		chef = recipe.getChef();
		
		result = !recipe.isPublished() && request.isPrincipal(chef);

		return result;
	}

	@Override
	public void bind(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "heading", "description","preparationNotes", "link");

	}

	@Override
	public void unbind(final Request<Recipe> request, final Recipe entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "heading", "description", "preparationNotes", "link","published");

	}

	@Override
	public Recipe findOne(final Request<Recipe> request) {
		assert request != null;

		Recipe result;
		int recipeId;

		recipeId = request.getModel().getInteger("id");
		result = this.repository.findOneById(recipeId);

		return result;

	}

	@Override
	public void validate(final Request<Recipe> request, final Recipe entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Recipe existing;

			existing = this.repository.findOneRecipeByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "chef.item.form.error.duplicated");
		}
		
	}

	@Override
	public void update(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		
		this.repository.save(entity);

	}

}