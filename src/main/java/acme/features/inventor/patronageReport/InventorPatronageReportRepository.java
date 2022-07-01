package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronageReports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{

	@Query("select pr from PatronageReport pr where pr.patronage.inventor.id = :inventorId")
	Collection<PatronageReport> findManyPatronagesReportByInventorId(int inventorId);
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOneById(int id);
	
	@Query("select p from Patronage p where p.id = :patronageId")
	Patronage findOnePatronageById(int patronageId);
	
	@Query("select count(pr) from PatronageReport pr where pr.patronage.code = :code")
	int countPatronageReportWithPatronageCode(String code);
	
}
