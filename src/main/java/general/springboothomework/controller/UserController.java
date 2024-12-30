package general.springboothomework.controller;

import general.springboothomework.entity.UserEntity;
import general.springboothomework.repository.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserEntityRepository userRepository;

    public UserController(UserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public ResponseEntity<List<UserEntity>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity<>("User deleted success", HttpStatus.NO_CONTENT);
    }

}
