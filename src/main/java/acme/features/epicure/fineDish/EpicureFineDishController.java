package acme.features.epicure.fineDish;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.fineDishes.FineDish;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureFineDishController extends AbstractController<Epicure, FineDish> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureFineDishShowService			showService;
	
	@Autowired
	protected EpicureFineDishCreateService		createService;

	@Autowired
	protected EpicureFineDishUpdateService		updateService;

	@Autowired
	protected EpicureFineDishDeleteService		deleteService;
	
	@Autowired
	protected EpicureFineDishListMineService	fineDishListMineService;
	
	///////////////////////////////////////////////////////////////

	@Autowired
	protected EpicureFineDishPublishService		publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("list-mine", "list", this.fineDishListMineService);
		
		super.addCommand("publish", "update", this.publishService);
		
	}

}