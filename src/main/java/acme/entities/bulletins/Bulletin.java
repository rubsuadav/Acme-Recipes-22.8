package acme.entities.bulletins;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bulletin extends AbstractEntity {
	
	protected static final long	serialVersionUID= 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	protected Date instantiationMoment;
	
	@NotBlank
	@Length (min=1, max=100)
	protected String heading;
	
	@NotBlank
	@Length (min=1, max=255)
	protected String pieceOfText;
	
	protected boolean criticalFlag;
	
	@URL
	protected String link;

}
