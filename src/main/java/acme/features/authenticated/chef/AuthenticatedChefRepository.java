package acme.features.authenticated.chef;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Chef;

@Repository
public interface AuthenticatedChefRepository extends AbstractRepository {

	@Query("select i from Chef i where i.userAccount.id = :id")
	Chef findOneChefByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);

}