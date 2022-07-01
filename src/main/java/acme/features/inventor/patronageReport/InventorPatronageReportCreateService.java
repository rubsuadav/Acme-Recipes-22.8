package acme.features.inventor.patronageReport;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.Status;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport>{

	@Autowired
	protected InventorPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		boolean result;
		int masterId;
		Patronage patronage;
		
		masterId = request.getModel().getInteger("patronageId");
		patronage = this.repository.findOnePatronageById(masterId);
		result = (patronage != null && patronage.isPublished() && patronage.getStatus().equals(Status.ACCEPTED) && request.isPrincipal(patronage.getInventor()));
		
		return result;
		
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "automaticSequenceNumber", "creation", "memorandum", "link");

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "automaticSequenceNumber", "creation", "memorandum", "link");

		model.setAttribute("patronageId", entity.getPatronage().getId());
		model.setAttribute("status", entity.getPatronage().getStatus());

	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int patronageId;
		Patronage patronage;
		Date creation;
		String automaticSequenceNumber;
		
		patronageId = request.getModel().getInteger("patronageId");
		patronage = this.repository.findOnePatronageById(patronageId);	
		creation = new Date(System.currentTimeMillis()-1);

		final int count = this.repository.countPatronageReportWithPatronageCode(patronage.getCode()) + 1;
		final String countNumber = String.format("%04d", count);
		automaticSequenceNumber = patronage.getCode() + ":" + countNumber;

		result = new PatronageReport();
		result.setCreation(creation);
		result.setPatronage(patronage);
		result.setAutomaticSequenceNumber(automaticSequenceNumber);

		return result;
		
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");

	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		Date creation;
		
		creation = new Date(System.currentTimeMillis()-1);

		entity.setCreation(creation);

		this.repository.save(entity);

	}

}