package general.springboothomework.controller;

import general.springboothomework.dto.StoreEntityDto;
import general.springboothomework.entity.StoreEntity;
import general.springboothomework.exception.StoreNotFoundException;
import general.springboothomework.repository.StoreEntityRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class StoreControllerAdmin {

    private final StoreEntityRepository storeEntityRepository;
    private StoreEntityDto dto;
    private StoreEntity store;

    public StoreControllerAdmin(StoreEntityRepository storeEntityRepository) {
        this.storeEntityRepository = storeEntityRepository;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<StoreEntity>> getAllStores() {
        List<StoreEntity> entities = storeEntityRepository.findAll();
        if (entities.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StoreEntity> getStoreById(@PathVariable Long id) {
        return ResponseEntity.ok(getOrElseThrow(id));
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StoreEntity> addStore(@Valid @RequestBody StoreEntityDto dto) {
        StoreEntity store = new StoreEntity();
        store.setName(dto.getName());
        store.setEmail(dto.getEmail());
        store.setEmployeeCount(dto.getEmployeeCount());
        store.setDesc(dto.getDesc());
        StoreEntity savedStore = storeEntityRepository.save(store);
        return ResponseEntity.status(201).body(savedStore);
    }

    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StoreEntity> updateStore(@Valid @RequestBody StoreEntityDto dto) {
        StoreEntity updated = UpdateStore(dto, getOrElseThrow(dto.getId()));
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
        storeEntityRepository.delete(getOrElseThrow(id));
        return ResponseEntity.noContent().build();
    }

    private StoreEntity UpdateStore(@NotNull StoreEntityDto dto, @NotNull StoreEntity store) {
        this.dto = dto;
        this.store = store;
        store.setName(dto.getName());
        store.setEmail(dto.getEmail());
        store.setEmployeeCount(dto.getEmployeeCount());
        store.setDesc(dto.getDesc());
        return storeEntityRepository.save(store);
    }

    private StoreEntity getOrElseThrow(Long id) {
        return storeEntityRepository.findById(id)
                .orElseThrow(() -> new StoreNotFoundException("Store not found"));
    }
}
