package acme.features.authenticated.bulletin;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.bulletins.Bulletin;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedBulletinShowService implements AbstractShowService<Authenticated, Bulletin> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedBulletinRepository repository;

	// AbstractShowService<Authenticated, Bulletin> interface --------------


	@Override
	public boolean authorise(final Request<Bulletin> request) {
		assert request != null;
		
		boolean result;
		int bulletinId;
		Bulletin bulletin;
		Calendar calendar;
		Date deadline;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		
		bulletinId = request.getModel().getInteger("id");
		bulletin = this.repository.findOneById(bulletinId);
		result = bulletin.getInstantiationMoment().after(deadline);

		return result;
	}

	@Override
	public Bulletin findOne(final Request<Bulletin> request) {
		assert request != null;

		Bulletin result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Bulletin> request, final Bulletin entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		String criticalFlag = "";

		criticalFlag = entity.isCriticalFlag() ? "The flag is critical": "The flag is not critical";
		
		request.unbind(entity, model, "instantiationMoment", "heading", "pieceOfText","link");
		
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
		
		model.setAttribute("criticalFlag", criticalFlag);
	}

}