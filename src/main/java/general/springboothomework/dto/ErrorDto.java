package general.springboothomework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    private String errorPath;
    private String errorMessage;
    private int errorCode;
    private LocalDateTime errorTime = LocalDateTime.now(Clock.system(ZoneId.of("Asia/Tashkent")));
}
