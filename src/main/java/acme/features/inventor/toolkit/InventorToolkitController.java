package acme.features.inventor.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolkitController extends AbstractController<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitShowService			showService;
	
	@Autowired
	protected InventorToolkitCreateService		createService;

	@Autowired
	protected InventorToolkitUpdateService		updateService;

	@Autowired
	protected InventorToolkitDeleteService		deleteService;
	
	@Autowired
	protected InventorToolkitListMineService	toolkitListMineService;
	
	@Autowired
	protected InventorToolkitPublishService		publishService;
	
	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("list-mine-toolkits", "list", this.toolkitListMineService);
		super.addCommand("publish", "update", this.publishService);
	}

}
