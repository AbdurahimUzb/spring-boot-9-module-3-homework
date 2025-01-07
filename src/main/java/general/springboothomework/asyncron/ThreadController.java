package general.springboothomework.asyncron;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    private final ReportSenderService reportSenderService;

    public ThreadController(ReportSenderService reportSenderService) {
        this.reportSenderService = reportSenderService;
    }

    @GetMapping("/sendReport")
    public String sendReport() {
        reportSenderService.sendReport("Javohir");
        return "Report send Successfully";
    }
}
