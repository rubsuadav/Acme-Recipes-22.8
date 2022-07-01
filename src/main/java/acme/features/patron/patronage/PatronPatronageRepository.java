package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.SystemConfiguration;
import acme.entities.patronageReports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findOneById(int id);

	@Query("select p from Patronage p where p.patron.id = :patronId")
	Collection<Patronage> findManyPatronagesByPatronId(int patronId);

	//QUERYS PARA EL CREATE
	
	@Query("select p from Patron p where p.id = :patronId")
	Patron findOnePatronById(int patronId);

	@Query("select p from Patronage p where p.code = :code")
	Patronage findOnePatronageByCode(String code);

	@Query("select i from Inventor i")
	Collection<Inventor> findAllInventors();
	
	@Query("select i from Inventor i where i.id = :inventorId")
	Inventor finOneInventorById(int inventorId);
	
	@Query("select sc from SystemConfiguration sc")
	SystemConfiguration findSystemConfiguration();

	//QUERY PARA EL DELETE
	
	@Query("Select p from PatronageReport p where p.patronage.id = :id") 
	Collection<PatronageReport> findPatronagesReportByPatronageId(int id); 
	
}
