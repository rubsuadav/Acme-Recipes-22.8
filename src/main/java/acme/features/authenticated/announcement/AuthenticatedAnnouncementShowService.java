package acme.features.authenticated.announcement;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedAnnouncementShowService implements AbstractShowService<Authenticated, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedAnnouncementRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		
		boolean result;
		int announcementId;
		Announcement announcement;
		Calendar calendar;
		Date deadline;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		deadline = calendar.getTime();
		
		announcementId = request.getModel().getInteger("id");
		announcement = this.repository.findOneById(announcementId);
		result = announcement.getCreationMoment().after(deadline);

		return result;
	}

	@Override
	public Announcement findOne(final Request<Announcement> request) {
		assert request != null;

		Announcement result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		String criticalFlag = "";

		criticalFlag = entity.isCriticalFlag() ? "The flag is critical": "The flag is not critical";
		
		request.unbind(entity, model, "creationMoment", "title", "body","link");
		
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
		
		model.setAttribute("criticalFlag", criticalFlag);
	}

}