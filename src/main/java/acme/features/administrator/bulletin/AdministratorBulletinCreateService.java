package acme.features.administrator.bulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin>{

	@Autowired
	protected AdministratorBulletinRepository repository;
	
	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		
		return true;
	}

	@Override
	public void bind(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "instantiationMoment", "heading", "pieceOfText", "criticalFlag","link");
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "heading", "pieceOfText", "criticalFlag","link");
		model.setAttribute("instantiationMoment", entity.getInstantiationMoment());
		
	}

	@Override
	public Bulletin instantiate(final Request<Bulletin> request) {
		assert request != null;
		
		Bulletin bulletin;
		
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		
		bulletin = new Bulletin();
		
		bulletin.setInstantiationMoment(moment);
		
		return bulletin;
	}

	@Override
	public void validate(final Request<Bulletin> request, final Bulletin entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		boolean confirmation;
		
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
	}

	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;
	
		this.repository.save(entity);
		
	}

}