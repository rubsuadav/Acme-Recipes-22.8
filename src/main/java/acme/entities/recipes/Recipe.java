package acme.entities.recipes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Chef;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recipe extends AbstractEntity{

	protected static final long serialVersionUID = 1L;
	
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@Column(unique = true)
	@NotBlank
	protected String code;
	
	@NotBlank
	@Length(min=1, max= 100)
	protected String heading;
	
	@NotBlank
	@Length(min=1, max= 255)
	protected String description;
	
	@NotBlank
	@Length(min=1, max= 255)
	protected String preparationNotes;
	
	@URL
	protected String link;
	
	protected boolean published;
	
	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Chef chef;
	
	//derived atribute
	
	@Transient
	protected Money retailPrice;

}
