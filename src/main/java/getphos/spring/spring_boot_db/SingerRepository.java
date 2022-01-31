package getphos.spring.spring_boot_db;

import getphos.spring.spring_boot_db.entity.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {
}
