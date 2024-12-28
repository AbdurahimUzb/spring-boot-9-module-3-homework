package general.springboothomework.controller;

import general.springboothomework.entity.AuthUser;
import general.springboothomework.repository.AuthUserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class AuthUserController {

    private final AuthUserRepository authUserRepository;

    public AuthUserController(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @GetMapping("/")
    public CollectionModel<EntityModel<AuthUser>> getAll() {
        List<AuthUser> all = authUserRepository.findAll();
        Link link = linkTo(methodOn(AuthUserController.class).getAll()).withRel("users");

        List<EntityModel<AuthUser>> models = all.stream().map(authUser -> {
            Link uri = linkTo(methodOn(AuthUserController.class).getById(authUser.getId())).withSelfRel();
            return EntityModel.of(authUser, uri, link);
        }).toList();
        return CollectionModel.of(models, link);
    }

    @GetMapping("/{id}")
    public EntityModel<AuthUser> getById(@PathVariable long id) {
        Link link = linkTo(methodOn(AuthUserController.class).getById(id)).withSelfRel();
        AuthUser user = authUserRepository.getAuthUserById(id).orElseThrow(RuntimeException::new);
        return EntityModel.of(user, link);
    }

    @PostMapping("/")
    public EntityModel<AuthUser> save(@RequestBody AuthUser authUser) {
        Link link = linkTo(methodOn(AuthUserController.class).save(authUser)).withSelfRel();
        AuthUser user = authUserRepository.save(authUser);
        return EntityModel.of(user, link);
    }

    @PutMapping("/")
    public EntityModel<AuthUser> update(@RequestBody AuthUser authUser) {
        Link link = linkTo(methodOn(AuthUserController.class).update(authUser)).withSelfRel();
        AuthUser user = authUserRepository.save(authUser);
        return EntityModel.of(user, link);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        authUserRepository.deleteById(id);
    }
}
