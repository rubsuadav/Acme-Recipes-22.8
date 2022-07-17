package acme.features.any.peep;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.peeps.Peep;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;

@Service
public class AnyPeepCreateService implements AbstractCreateService<Any, Peep>{

	@Autowired
	protected AnyPeepRepository repository;
	
	@Override
	public boolean authorise(final Request<Peep> request) {
		assert request!=null;
		
		return true;
	}

	@Override
	public void bind(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
		request.bind(entity, errors, "heading", "writer", "pieceOfText", "email");
		
	}

	@Override
	public void unbind(final Request<Peep> request, final Peep entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		
		request.unbind(entity, model, "heading", "writer", "pieceOfText", "email");
		
		model.setAttribute("creationMoment", entity.getInstantiationMoment());
		
	}

	@Override
	public Peep instantiate(final Request<Peep> request) {
		assert request!=null;
		
		Peep result;
		Date moment;
		
		moment=new Date(System.currentTimeMillis()-1);
		result= new Peep();
		
		result.setInstantiationMoment(moment);
		
		return result;
	}

	@Override
	public void validate(final Request<Peep> request, final Peep entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
		boolean confirmation;
		confirmation=request.getModel().getBoolean("confirmation");
		
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
	}

	@Override
	public void create(final Request<Peep> request, final Peep entity) {
		assert request!=null;
		assert entity!=null;
		
		this.repository.save(entity);
	}

}