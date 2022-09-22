package acme.features.administrator.bulletin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamFilter;
import acme.entities.bulletins.Bulletin;
import acme.entities.configurations.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.utils.UtilRepository;

@Service
public class AdministratorBulletinCreateService implements AbstractCreateService<Administrator, Bulletin>{

	@Autowired
	protected AdministratorBulletinRepository repository;
	
	@Autowired
	protected UtilRepository uRepository;

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
		
		final SystemConfiguration configuration = this.uRepository.findSystemConfiguration();

		if (!errors.hasErrors("heading")) {
			errors.state(request, SpamFilter.spamValidator(entity.getHeading(), configuration.getWeakSpamWords(), configuration.getStrongSpamWords(), 
														   configuration.getWeakSpamThreshold(), configuration.getStrongSpamThreshold()), 
																			"heading", "form.error.spam");
		}
		
		if (!errors.hasErrors("pieceOfText")) {
			errors.state(request, SpamFilter.spamValidator(entity.getPieceOfText(), configuration.getWeakSpamWords(), configuration.getStrongSpamWords(),
															configuration.getWeakSpamThreshold(), configuration.getStrongSpamThreshold()), 
																			"pieceOfText", "form.error.spam");
		}
	}

	@Override
	public void create(final Request<Bulletin> request, final Bulletin entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}