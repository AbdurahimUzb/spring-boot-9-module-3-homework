package general.springboothomework.properties.database;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Database {

    private String url;
    private String username;
    private String password;
    private String secret;
}
