package acme.features.any.userAccount;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository {

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneById(int id);
	
	@Query("select ua from UserAccount ua join ua.roles r where ua.enabled = 1 and "
		+ "Administrator not in (select type(r2) from UserAccount ua2 join ua2.roles r2 where ua2.id = ua.id)")
	Collection<UserAccount> findUserAccounts();

}