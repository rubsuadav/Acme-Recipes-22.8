package acme.features.inventor.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.Status;
import acme.features.patron.patronage.PatronPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageUpdateService implements AbstractUpdateService<Inventor, Patronage>{

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result;
		int id;
		Patronage patronage;
		Inventor inventor;
		
		id = request.getModel().getInteger("id");
		patronage = this.repository.findOneById(id);
		inventor = patronage.getInventor();
		result = patronage.getStatus().equals(Status.PROPOSED) && request.isPrincipal(inventor) && patronage.isPublished();
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors,  "status", "code", "legalStuff", "budget", "initial", "creation", "end", "link");
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("patronProfileFullName", entity.getPatron().getIdentity().getFullName());
		model.setAttribute("patronProfileEmail", entity.getPatron().getIdentity().getEmail());
		model.setAttribute("patronCompany", entity.getPatron().getCompany());
		model.setAttribute("patronStatement", entity.getPatron().getStatement());
		model.setAttribute("patronLink", entity.getPatron().getLink());
		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "initial", "creation", "end", "link");
	}

	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}

}
