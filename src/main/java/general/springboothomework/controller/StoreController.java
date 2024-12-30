package general.springboothomework.controller;

import general.springboothomework.entity.StoreEntity;
import general.springboothomework.repository.StoreEntityRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreEntityRepository storeRepository;

    public StoreController(StoreEntityRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @GetMapping("/show")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<StoreEntity>> show() {
        return ResponseEntity.ok(storeRepository.findAll());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StoreEntity> get(@PathVariable Long id) {
        StoreEntity store = storeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Store not found"));
        return ResponseEntity.ok(store);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StoreEntity> save(@Valid @NotNull @RequestBody StoreEntity store) {
        storeRepository.save(store);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StoreEntity> update(@Valid @NotNull @RequestBody StoreEntity store) {
        storeRepository.save(store);
        return ResponseEntity.ok(store);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        storeRepository.deleteById(id);
        return new ResponseEntity<>("Store Deleted", HttpStatus.NO_CONTENT);
    }

}
