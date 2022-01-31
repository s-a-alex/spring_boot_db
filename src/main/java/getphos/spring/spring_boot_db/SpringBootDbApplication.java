package getphos.spring.spring_boot_db;

import getphos.spring.spring_boot_db.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@SpringBootApplication(scanBasePackages="getphos.spring.spring_boot_db")
@EntityScan("getphos.spring.spring_boot_db.entity")
public class SpringBootDbApplication implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(SpringBootDbApplication.class);

    @Autowired
    SingerRepository singerRepository;
    @Autowired
    InstrumentRepository instrumentRepository;

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext ctx=SpringApplication.run(SpringBootDbApplication.class, args);
        System.in.read();
        ctx.close();
    }

    @Transactional(readOnly = true)
    @Override public void run(String ... args) throws Exception {
        listSingersWithAlbum(StreamSupport.stream(singerRepository.findAll().spliterator(), false).collect(Collectors.toList()));
    }

    private void listSingers(List<Singer> singers) {
        logger.info(" ---- Listing singers:");
        for (Singer singer : singers) {
            logger.info(singer.toString());
        }
    }

    private void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");
        singers.forEach(s -> {
            logger.info(s.toString());
            if (s.getAlbums() != null) {
                s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
            }
            if (s.getInstruments() != null) {
                s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
            }
        });
    }
}
