package general.springboothomework.controller;

import general.springboothomework.token.JwtTokenGenerator;
import general.springboothomework.token.TokenRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JwtController {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenGenerator jwtTokenGenerator;

    public JwtController(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtTokenGenerator jwtTokenGenerator) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }

    @PostMapping("/token")
    public String hello(@RequestBody TokenRequest token) {
        String string = token.email();
        String password = token.password();

        UserDetails user = userDetailsService.loadUserByUsername(string);

        if (!passwordEncoder.matches(password, user.getPassword()))
            throw new BadCredentialsException("Wrong password");
        return jwtTokenGenerator.generateToken(user.getUsername());
    }
}
