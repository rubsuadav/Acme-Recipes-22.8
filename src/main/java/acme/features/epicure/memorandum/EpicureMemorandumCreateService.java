package acme.features.epicure.memorandum;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.FineDish;
import acme.entities.fineDishes.Status;
import acme.entities.memorandums.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumCreateService implements AbstractCreateService<Epicure, Memorandum> {

	@Autowired
	protected EpicureMemorandumRepository repository;
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;

		boolean result;
		int masterId;
		FineDish fineDish;
		
		masterId = request.getModel().getInteger("fineDishId");
		fineDish = this.repository.findOneFineDishById(masterId);
		result = (fineDish != null && fineDish.isPublished() && 
				 (fineDish.getStatus().equals(Status.ACCEPTED) || fineDish.getStatus().equals(Status.DENIED)) && 
			     request.isPrincipal(fineDish.getEpicure()));
		
		return result;
		
	}

	@Override
	public void bind(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "automaticSequenceNumber", "instantiationMoment", "report", "link");
		
	}

	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "automaticSequenceNumber", "instantiationMoment", "report", "link");

		model.setAttribute("fineDishId", entity.getFineDish().getId());
		model.setAttribute("status", entity.getFineDish().getStatus());
		
	}

	@Override
	public Memorandum instantiate(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		int fineDishId;
		FineDish fineDish;
		Date creation;
		String automaticSequenceNumber;
		
		fineDishId = request.getModel().getInteger("fineDishId");
		fineDish = this.repository.findOneFineDishById(fineDishId);	
		creation = new Date(System.currentTimeMillis()-1);

		final int count = this.repository.countMemorandumsWithFineDishCode(fineDish.getCode()) + 1;
		final String countNumber = String.format("%04d", count);
		automaticSequenceNumber = fineDish.getCode() + ":" + countNumber;

		result = new Memorandum();
		result.setInstantiationMoment(creation);
		result.setFineDish(fineDish);
		result.setAutomaticSequenceNumber(automaticSequenceNumber);

		return result;
		
	}

	@Override
	public void validate(final Request<Memorandum> request, final Memorandum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean confirmation;
		confirmation = request.getModel().getBoolean("confirmation");
		errors.state(request, confirmation, "confirmation", "javax.validation.constraints.AssertTrue.message");
		
	}

	@Override
	public void create(final Request<Memorandum> request, final Memorandum entity) {
		assert request != null;
		assert entity != null;

		Date creation;
		
		creation = new Date(System.currentTimeMillis()-1);

		entity.setInstantiationMoment(creation);

		this.repository.save(entity);
		
	}

}