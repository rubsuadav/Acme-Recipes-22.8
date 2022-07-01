package acme.entities.patronages;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Patronage extends AbstractEntity {
	
	protected static final long	serialVersionUID= 1L;
	
	@NotNull
	protected Status status;
	
	@NotBlank
	@Column(unique = true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String legalStuff;
	
	@Valid
	@NotNull
	protected Money budget;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date initial;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date creation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date end;
	
	@URL
	protected String link;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Inventor inventor;
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Patron patron;
	
	protected boolean published;
}
