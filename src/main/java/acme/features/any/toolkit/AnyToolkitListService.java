package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.toolkits.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractListService;

@Service
public class AnyToolkitListService implements AbstractListService<Any, Toolkit>{

	@Autowired
	protected AnyToolkitRepository repository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;

		Collection<Toolkit> result;

		result = this.repository.findPublished();

		return result;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		//INCLUYENDO UN ITEM EN PARTICULAR
		
		final String payload;
		final StringBuilder bufferName = new StringBuilder();
		final StringBuilder bufferCode = new StringBuilder();
		final StringBuilder bufferTechnology = new StringBuilder();
		
		final Collection<Item> items = this.repository.findManyItemsByToolkitId(entity.getId());
		int size = items.size();
		
		for(final Item i: items) {
			bufferName.append(i.getName());
			bufferCode.append(i.getCode());
			bufferTechnology.append(i.getTechnology());
			
			if(size > 1) {
				bufferName.append(",");
				bufferCode.append(",");
				bufferTechnology.append(",");
				
				size--;
			}	
		}
		
		payload = String.format(//
			"%s; %s; %s", //
			bufferName.toString(),bufferCode.toString(),bufferTechnology.toString());

		request.unbind(entity, model, "code", "title");
		
		model.setAttribute("payload", payload);

	}
}
