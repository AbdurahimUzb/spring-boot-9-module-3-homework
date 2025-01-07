package general.springboothomework.asyncron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ReportSenderService {

    @Async
    public void sendReport(String message) {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (Exception ignored) {
        }
        log.info("Sending report to asyncron");
        throw new RuntimeException("Report failed exception " + LocalDate.now());
    }

}
