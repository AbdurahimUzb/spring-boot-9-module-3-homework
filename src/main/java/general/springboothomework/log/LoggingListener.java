package general.springboothomework.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;

@Slf4j
@Component
public class LoggingListener {

    @PostPersist
    public void logCreate(Object entity) {
        log.info("Created: {}", entity);
    }

    @PostUpdate
    public void logUpdate(Object entity) {
        log.info("Updated: {}", entity);
    }

    @PostRemove
    public void logDelete(Object entity) {
        log.info("Deleted: {}", entity);
    }
}
