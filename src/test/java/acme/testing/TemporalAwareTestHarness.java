package acme.testing;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.bulletins.Bulletin;
import acme.entities.peeps.Peep;
import acme.framework.helpers.FactoryHelper;
import acme.testing.any.peep.PeepRepository;
import acme.testing.authenticated.bulletin.BulletinRepository;

public class TemporalAwareTestHarness extends TestHarness {

	// Lifecycle management ---------------------------------------------------

	@Autowired
	private PeepRepository peepRepository;

	@Autowired
	private BulletinRepository bulletinRepository;
	
	@Override
	@BeforeAll
	public void beforeAll() {
		super.beforeAll();
		FactoryHelper.autowire(this);
		this.patchPeeps();
		this.patchBulletins();
	}

	// Business methods -------------------------------------------------------


	protected void patchPeeps() {
		Collection<Peep> peeps;
		Date moment;

		peeps = this.peepRepository.findPeepsToPatch();
		for (final Peep peep : peeps) {
			moment = this.adjustMoment(peep.getInstantiationMoment());
			peep.setInstantiationMoment(moment);
			this.peepRepository.save(peep);
			
		}
		
	}

	protected void patchBulletins() {
		Collection<Bulletin> bulletins;
		Date moment;

		bulletins = this.bulletinRepository.findBulletinsToPatch();
		for (final Bulletin bulletin : bulletins) {
			moment = this.adjustMoment(bulletin.getInstantiationMoment());
			bulletin.setInstantiationMoment(moment);
			this.bulletinRepository.save(bulletin);
		}
	}

	protected String computeDeltaMoment(final int deltaDays) {
		assert deltaDays <= 0;

		String result;
		Calendar calendar;
		SimpleDateFormat formatter;

		calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, deltaDays);
		formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");

		result = formatter.format(calendar.getTime());

		return result;
	}

	protected Date adjustMoment(final Date currentMoment) {
		assert currentMoment != null;

		Date result;
		Calendar calendar;
		int day;

		calendar = Calendar.getInstance();

		calendar.setTime(currentMoment);
		day = calendar.get(Calendar.DAY_OF_MONTH);

		calendar.setTime(new Date());
		calendar.set(Calendar.HOUR, 00);
		calendar.set(Calendar.MINUTE, 00);
		calendar.set(Calendar.SECOND, 00);
		calendar.set(Calendar.MILLISECOND, 000);
		calendar.add(Calendar.DAY_OF_MONTH, -day);

		result = calendar.getTime();

		return result;

	}


}