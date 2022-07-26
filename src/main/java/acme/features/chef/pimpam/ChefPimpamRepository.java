package acme.features.chef.pimpam;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.SystemConfiguration;
import acme.entities.items.Item;
import acme.entities.pimpams.Pimpam;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ChefPimpamRepository extends AbstractRepository{
	
	//QUERYS PARA EL LISTADO
	
	@Query("select p from Pimpam p")
	Collection<Pimpam> findManyPimpams();
	
	@Query("select i.pimpam from Item i where i.id = :id")
	Collection<Pimpam> findManyPimpamsByItemId(int id);
	
	//para RN de solo poder crear pimpans si y solo ese item no esta publicado y no tenga ningun pimpam asociado
	
	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);
	
	//QUERYS PARA EL SHOW
	
	@Query("select y from Pimpam y where y.id = :id")
	Pimpam findOnePimpamById(int id);
	
	//QUERYS PARA EL CREATE (RN)
	
	@Query("select y from Pimpam y where y.code = :code")
	Pimpam findOnePimpamByCode(String code);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();
	
	//QUERYS PARA EL UPDATE

	@Query("select i from Item i where i.pimpam.id = :id")
	Item findOneItemByPimpamId(int id);
	
}