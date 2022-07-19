package acme.features.chef.memorandum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.memorandums.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefMemorandumController extends AbstractController<Chef, Memorandum> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefMemorandumListMineService listMineService;
	
	@Autowired
	protected ChefMemorandumShowService showService;
	
	@Autowired
	protected ChefMemorandumCreateService createService;
	
	// Constructors -----------------------------------------------------------

	
	@PostConstruct
	protected void intialise() {
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("list-mine","list", this.listMineService);
	}
}
