package acme.features.authenticated.epicure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.HttpMethod;
import acme.framework.controllers.Request;
import acme.framework.controllers.Response;
import acme.framework.entities.Principal;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class AuthenticatedEpicureUpdateService implements AbstractUpdateService<Authenticated, Epicure> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedEpicureRepository repository;

	// AbstractUpdateService<Authenticated, Epicure> interface -----------------

	@Override
	public boolean authorise(final Request<Epicure> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
	}

	@Override
	public void bind(final Request<Epicure> request, final Epicure entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "organisation", "assertion", "link");
	}

	@Override
	public void unbind(final Request<Epicure> request, final Epicure entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organisation", "assertion", "link");
	}

	@Override
	public Epicure findOne(final Request<Epicure> request) {
		assert request != null;

		Epicure result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneEpicureByUserAccountId(userAccountId);

		return result;
	}

	@Override
	public void update(final Request<Epicure> request, final Epicure entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Epicure> request, final Response<Epicure> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}