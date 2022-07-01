package acme.entities.memorandums;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.fineDishes.FineDish;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Memorandum extends AbstractEntity{

	protected static final long	serialVersionUID= 1L;

	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}:[0-9]{4,6}$")
	@NotBlank
	@Column(unique = true)
	protected String automaticSequenceNumber;
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	protected Date instantiationMoment;

	@NotBlank
	@Length (min=1, max=255)
	protected String report;

	@URL
	protected String link;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected FineDish fineDish;
}
