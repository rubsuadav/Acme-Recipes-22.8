package acme.features.patron.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageController extends AbstractController<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageShowService			showService;
	
	@Autowired
	protected PatronPatronageCreateService		createService;

	@Autowired
	protected PatronPatronageUpdateService		updateService;

	@Autowired
	protected PatronPatronageDeleteService		deleteService;
	
	@Autowired
	protected PatronPatronageListMineService	patronageListMineService;
	
	///////////////////////////////////////////////////////////////

	@Autowired
	protected PatronPatronagePublishService		publishService;

	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("list-mine", "list", this.patronageListMineService);
		
		super.addCommand("publish", "update", this.publishService);
	}

}
