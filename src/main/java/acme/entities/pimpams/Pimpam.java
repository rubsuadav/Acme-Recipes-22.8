package acme.entities.pimpams;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pimpam extends AbstractEntity {

	protected static final long	serialVersionUID= 1L;

	@Column(unique = true)
	@Pattern(regexp = "^\\d{2}-\\d{6}$")
	@NotBlank
	protected String code;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date instantiationMoment;

	@NotBlank
	@Length (min=1, max=100)
	protected String title;

	@NotBlank
	@Length (min=1, max=255)
	protected String desciption;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date initial;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date end;

	@Valid
	@NotNull
	protected Money budget;

	@URL
	protected String link;
}