package acme.roles;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.roles.UserRole;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patron extends UserRole{

	protected static final long	serialVersionUID = 1L;
		
	@NotBlank
	@Length(min=1, max=100)
	protected String company;
	
	@NotBlank
	@Length(min=1, max=255)
	protected String statement;
	
	@URL
	protected String link;
}
