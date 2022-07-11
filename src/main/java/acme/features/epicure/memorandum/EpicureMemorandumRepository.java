package acme.features.epicure.memorandum;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.memorandums.Memorandum;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EpicureMemorandumRepository extends AbstractRepository{

	@Query("select m from Memorandum m where m.fineDish.epicure.id = :epicureId")
	Collection<Memorandum> findManyMemorandumsByEpicureId(int epicureId);
	
	@Query("select m from Memorandum m where m.id = :id")
	Memorandum findOneById(int id);
	
}
