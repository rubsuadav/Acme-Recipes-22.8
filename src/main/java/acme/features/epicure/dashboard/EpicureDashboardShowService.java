package acme.features.epicure.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.fineDishes.Status;
import acme.forms.EpicureDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;
import acme.utils.DashboardLibrary;

@Service
public class EpicureDashboardShowService implements AbstractShowService<Epicure, EpicureDashboard> {

	@Autowired
	protected EpicureDashboardRepository repository;
	
	@Autowired
	protected DashboardLibrary library;

	@Override
	public boolean authorise(final Request<EpicureDashboard> request) {
		assert request != null;

		return request.getPrincipal().hasRole(Epicure.class);
	}

	@Override
	public EpicureDashboard findOne(final Request<EpicureDashboard> request) {
		assert request != null;

		final EpicureDashboard result = new EpicureDashboard();
		
		final Map<Status, Integer> numberOfFineDishesByStatus;
		final Map<Pair<Status, String>, Double> averageNumberOfBudgetsByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> deviationOfBudgetsByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> minBudgetByCurrencyAndStatus;
		final Map<Pair<Status, String>, Double> maxBudgetByCurrencyAndStatus;

		numberOfFineDishesByStatus = new EnumMap<>(Status.class);
		for(int i=0; i<Status.values().length; i++) {
			numberOfFineDishesByStatus.put(Status.values()[i], this.repository.numberOfPatronagesByStatus(Status.values()[i]));
		}
		
		averageNumberOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.averageNumberOfBudgetsByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				averageNumberOfBudgetsByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		deviationOfBudgetsByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.deviationOfBudgetsByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				deviationOfBudgetsByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		minBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.minBudgetByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				minBudgetByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}
		
		maxBudgetByCurrencyAndStatus = new HashMap<Pair<Status, String>, Double>();	
		for(int i=0; i<Status.values().length; i++) {
			final int j = i;
			this.repository.maxBudgetByCurrencyAndStatus(Status.values()[i]).stream()
			.forEach(x->
				maxBudgetByCurrencyAndStatus.put(
					Pair.of(Status.values()[j], x.get(0).toString()),
					Double.parseDouble(x.get(1).toString()))
				);
		}

		result.setNumberOfFineDishesByStatus(numberOfFineDishesByStatus);
		result.setAverageNumberOfBudgetsByCurrencyAndStatus(averageNumberOfBudgetsByCurrencyAndStatus);
		result.setDeviationOfBudgetsByCurrencyAndStatus(deviationOfBudgetsByCurrencyAndStatus);
		result.setMinBudgetByCurrencyAndStatus(minBudgetByCurrencyAndStatus);
		result.setMaxBudgetByCurrencyAndStatus(maxBudgetByCurrencyAndStatus);
		
		return result;
	}

	@Override
	public void unbind(final Request<EpicureDashboard> request, final EpicureDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, //
			"numberOfFineDishesByStatus", "averageNumberOfBudgetsByCurrencyAndStatus", // 
			"deviationOfBudgetsByCurrencyAndStatus", "minBudgetByCurrencyAndStatus", //
			"maxBudgetByCurrencyAndStatus");
		
		this.library.createDashboard(entity, model);
		
	}

}
