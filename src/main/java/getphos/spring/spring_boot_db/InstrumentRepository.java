package getphos.spring.spring_boot_db;

import getphos.spring.spring_boot_db.entity.Instrument;
import org.springframework.data.repository.CrudRepository;

public interface InstrumentRepository extends CrudRepository<Instrument, Long> {
}
