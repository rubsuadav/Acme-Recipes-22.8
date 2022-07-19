package acme.features.chef.quantity;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.recipes.Quantity;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefQuantityController extends AbstractController<Chef, Quantity> {
	
	@Autowired
	protected ChefQuantityShowService chefQuantityShowService;
	
	@Autowired
	protected ChefQuantityUpdateService chefQuantityUpdateService;
				
	@Autowired
	protected ChefQuantityDeleteService chefQuantityDeleteService;
	
	@Autowired
	protected ChefQuantityListService chefQuantityListService;
	 
	@Autowired
	protected ChefQuantityCreateService chefQuantityCreateService;

	@PostConstruct
	protected void initialise() {
		super.addCommand("list-recipe-items", "list", this.chefQuantityListService);
		super.addCommand("create", this.chefQuantityCreateService);
		super.addCommand("show", this.chefQuantityShowService);
		super.addCommand("update", this.chefQuantityUpdateService);
		super.addCommand("delete", this.chefQuantityDeleteService);

	}

}