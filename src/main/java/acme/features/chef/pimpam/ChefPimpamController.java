package acme.features.chef.pimpam;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.pimpams.Pimpam;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefPimpamController extends AbstractController<Chef, Pimpam> {
	
	// Internal state ---------------------------------------------------------
	
	@Autowired
	protected ChefPimpamShowService showService;
	
//	@Autowired
//	protected ChefPimpamCreateService createService;
//	
//	@Autowired
//	protected ChefPimpamUpdateService updateService;
//	
//	@Autowired
//	protected ChefPimpamDeleteService deleteService;
	
	@Autowired
	protected ChefPimpamItemListService listPimapmItemService;
	
	@Autowired
	protected ChefPimpamListService listPimpamService;
	
	// Constructors -----------------------------------------------------------

	
	@PostConstruct
	protected void intialise() {
		super.addCommand("show", this.showService);
//		super.addCommand("create", this.createService);
//		super.addCommand("update", this.updateService);
//		super.addCommand("delete", this.deleteService);
		super.addCommand("list", this.listPimapmItemService);
		super.addCommand("list-pimpam","list", this.listPimpamService);
		
	}
}
