package acme.features.inventor.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkits.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorQuantityController extends AbstractController<Inventor, Quantity> {
	
	@Autowired
	protected InventorToolkitItemShowService inventorToolkitItemShowService;
	
	@Autowired
	protected InventorToolkitItemUpdateService inventorToolkitItemUpdateService;
				
	@Autowired
	protected InventorToolkitItemDeleteService inventorToolkitItemDeleteService;
	
	@Autowired
	protected InventorToolkitItemListService inventorToolkitItemListService;
	 
	@Autowired
	protected InventorQuantityCreateService createService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-toolkit-items", "list", this.inventorToolkitItemListService);
		super.addCommand("create", this.createService);
		super.addCommand("show", this.inventorToolkitItemShowService);
		super.addCommand("update", this.inventorToolkitItemUpdateService);
		super.addCommand("delete", this.inventorToolkitItemDeleteService);

	}

}