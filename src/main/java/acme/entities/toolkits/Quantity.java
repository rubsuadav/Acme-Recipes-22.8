package acme.entities.toolkits;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import acme.entities.items.Item;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Quantity extends AbstractEntity{

	protected static final long serialVersionUID = 1L;

	@Min(1)
	protected int number;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Toolkit toolkit;

	@ManyToOne(optional = false)
	@Valid
	@NotNull
	protected Item item;

}
