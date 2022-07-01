package acme.features.any.item;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.items.Item;
import acme.features.any.toolkit.AnyToolkitItemListMineService;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyItemController extends AbstractController<Any, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyItemListService		toolListAllService;

	@Autowired
	protected AnyItemShowService			showService;
	
	@Autowired
	protected AnyToolkitItemListMineService		anyToolkitItemListMineService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("show", this.showService);
		super.addCommand("list", this.toolListAllService);

		//ENDPOINT PARA ACCEDER A LA LISTA DE SUS ITEMS DESDE SU TOOLKIT

		super.addCommand("list-toolkit-items", "list", this.anyToolkitItemListMineService);
	}

}
