package acme.features.chef.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.configurations.SystemConfiguration;
import acme.entities.recipes.Recipe;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Chef;

@Service
public class ChefRecipeCreateService implements AbstractCreateService<Chef, Recipe>{
	
	@Autowired
	protected ChefRecipeRepository repository;

	@Override
	public boolean authorise(final Request<Recipe> request) {
		assert request != null;
		
		return true;
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
	public Recipe instantiate(final Request<Recipe> request) {
		assert request != null;
		
		Recipe result;
		Chef chef;

		chef = this.repository.findOneChefById(request.getPrincipal().getActiveRoleId());
		result= new Recipe();

		result.setChef(chef);
		
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
			errors.state(request, existing == null, "code", "chef.item.form.error.duplicated");
		}
		
		final SystemConfiguration configuration = this.repository.findSystemConfiguration();

		if (!errors.hasErrors("heading")) {
			errors.state(request, SpamFilter.spamValidator(entity.getHeading(), configuration.getWeakSpamWords(), configuration.getStrongSpamWords(), 
														   configuration.getWeakSpamThreshold(), configuration.getStrongSpamThreshold()), 
																			"heading", "form.error.spam");
		}
		
		if (!errors.hasErrors("description")) {
			errors.state(request, SpamFilter.spamValidator(entity.getDescription(), configuration.getWeakSpamWords(), configuration.getStrongSpamWords(),
															configuration.getWeakSpamThreshold(), configuration.getStrongSpamThreshold()), 
																			"description", "form.error.spam");
		}
		
		if (!errors.hasErrors("preparationNotes")) {
			errors.state(request, SpamFilter.spamValidator(entity.getPreparationNotes(), configuration.getWeakSpamWords(), configuration.getStrongSpamWords(),
															configuration.getWeakSpamThreshold(), configuration.getStrongSpamThreshold()), 
																			"preparationNotes", "form.error.spam");
		}
	}

	@Override
	public void create(final Request<Recipe> request, final Recipe entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}