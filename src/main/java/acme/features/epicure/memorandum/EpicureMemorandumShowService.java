package acme.features.epicure.memorandum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.memorandums.Memorandum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureMemorandumShowService implements AbstractShowService<Epicure, Memorandum> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected EpicureMemorandumRepository repository;
	
	// AbstractShowService<Epicure, Memorandum> interface --------------
	
	
	@Override
	public boolean authorise(final Request<Memorandum> request) {
		assert request != null;
		
		boolean result;
		int memorandumId;
		Memorandum memorandum;
		
		memorandumId = request.getModel().getInteger("id");
		memorandum =  this.repository.findOneById(memorandumId);
		result = memorandum.getFineDish().getEpicure().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}
	
	@Override
	public Memorandum findOne(final Request<Memorandum> request) {
		assert request != null;

		Memorandum result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Memorandum> request, final Memorandum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "automaticSequenceNumber", "instantiationMoment", "report", "link");
		model.setAttribute("confirmation", false);
		model.setAttribute("readonly", true);
	}
}
