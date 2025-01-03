package general.springboothomework.controller;

import general.springboothomework.entity.StoreEntity;
import general.springboothomework.exception.StoreNotFoundException;
import general.springboothomework.repository.StoreEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class StoreControllerUser {

    private final StoreEntityRepository storeEntityRepository;

    public StoreControllerUser(StoreEntityRepository storeEntityRepository) {
        this.storeEntityRepository = storeEntityRepository;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<StoreEntity>> getAllStores() {
        List<StoreEntity> entities = storeEntityRepository.findAll();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<StoreEntity> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(getOrElseThrow(id));
    }

    private StoreEntity getOrElseThrow(Long id) {
        return storeEntityRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found"));
    }
}
