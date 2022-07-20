package acme.features.epicure.memorandum;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.memorandums.Memorandum;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicureMemorandumController extends AbstractController<Epicure, Memorandum> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicureMemorandumListMineService listMineService;
	
	@Autowired
	protected EpicureMemorandumShowService showService;
	
	@Autowired
	protected EpicureMemorandumCreateService createService;
	
	// Constructors -----------------------------------------------------------

	
	@PostConstruct
	protected void intialise() {
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("list-mine","list", this.listMineService);
	}
}
