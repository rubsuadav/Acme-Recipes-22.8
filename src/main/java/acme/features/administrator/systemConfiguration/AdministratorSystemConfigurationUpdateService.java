package acme.features.administrator.systemConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration> {

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "acceptedCurrencies", "systemCurrency", "strongSpamWords", "strongSpamThreshold", "weakSpamWords", "weakSpamThreshold");

	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "acceptedCurrencies", "systemCurrency", "strongSpamWords", "strongSpamThreshold", "weakSpamWords", "weakSpamThreshold");

	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;

		SystemConfiguration result;

		result = this.repository.findSystemConfiguration();

		return result;
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("systemCurrency")) {
			final String[] currencies = this.repository.findSystemConfiguration().getAcceptedCurrencies().split(",");
			boolean res = false;
			for(int i = 0; i< currencies.length; i++) {
				if(entity.getSystemCurrency().equals(currencies[i].trim())) {
					res = true;
				}
			}
			errors.state(request, res, "systemCurrency", "administrator.configuration.form.error.incorrect-system-currency");
		}
	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}