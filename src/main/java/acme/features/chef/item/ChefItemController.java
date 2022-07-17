package acme.features.chef.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.items.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefItemController extends AbstractController<Chef, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefItemShowService			showService;

	@Autowired
	protected ChefItemCreateService		createService;

	@Autowired
	protected ChefItemUpdateService		updateService;

	@Autowired
	protected ChefItemDeleteService		deleteService;

	@Autowired
	protected ChefItemListMineService	itemListMineService;

	@Autowired
	protected ChefItemPublishService		publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);

		super.addCommand("list-mine-items", "list", this.itemListMineService);

		super.addCommand("publish", "update", this.publishService);
	}

}
