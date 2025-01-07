package general.springboothomework.properties.database.controller;

import general.springboothomework.properties.database.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Value("${database.url}")
    private String url;

    @Value("${database.username}")
    private String username;

    @Value("${database.password}")
    private String password;

    @Value("${database.secret}")
    private String secret;

    @GetMapping("/")
    public Database getDatabase() {
        return new Database(url, username, password, secret);
    }

}
