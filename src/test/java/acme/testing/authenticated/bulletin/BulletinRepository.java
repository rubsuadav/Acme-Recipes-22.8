package acme.testing.authenticated.bulletin;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.bulletins.Bulletin;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface BulletinRepository extends AbstractRepository {
	
	@Query("select a from Bulletin a where a.instantiationMoment between '1900/01/01' and '1900/01/31'")
	Collection<Bulletin> findBulletinsToPatch();

}
