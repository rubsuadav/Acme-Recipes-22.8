package acme.features.chef.pimpam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.pimpams.Pimpam;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Chef;

@Service
public class ChefPimpamDeleteService implements AbstractDeleteService<Chef, Pimpam>{
	
	@Autowired
	protected ChefPimpamRepository repository;

	@Override
	public boolean authorise(final Request<Pimpam> request) {
		assert request != null;

		boolean result;
		int id;
		Item item;
		Chef chef;

		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemByPimpamId(id);
		chef = item.getChef();
		result = !item.isPublished() && request.isPrincipal(chef);

		return result;
	}

	@Override
	public void bind(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code","title","desciption","initial","end","budget","link");
		
	}

	@Override
	public void unbind(final Request<Pimpam> request, final Pimpam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "instantiationMoment", "title","desciption","initial","end","budget","link");
		
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
	public void validate(final Request<Pimpam> request, final Pimpam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void delete(final Request<Pimpam> request, final Pimpam entity) {
		assert request != null;
		assert entity != null;
		
		Item item;
		
		item = this.repository.findOneItemByPimpamId(entity.getId());
		
		item.setPimpam(null);
		this.repository.delete(entity);
		
	}

}