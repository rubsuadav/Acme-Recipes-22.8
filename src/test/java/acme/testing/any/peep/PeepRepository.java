package acme.testing.any.peep;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.peeps.Peep;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PeepRepository extends AbstractRepository {
	
	@Query("select a from Peep a where a.instantiationMoment between '1900/01/01' and '1900/01/31'")
	Collection<Peep> findPeepsToPatch();

}
