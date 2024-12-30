package general.springboothomework.controller;

import general.springboothomework.entity.ItemEntity;
import general.springboothomework.repository.ItemEntityRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemEntityRepository itemRepository;

    public ItemController(ItemEntityRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/show")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ItemEntity>> show() {
        return ResponseEntity.ok(itemRepository.findAll());
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemEntity> get(@PathVariable Long id) {
        ItemEntity store = itemRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found"));
        return ResponseEntity.ok(store);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemEntity> save(@Valid @NotNull @RequestBody ItemEntity store) {
        System.out.println("store = " + store);
        itemRepository.save(store);
        return new ResponseEntity<>(store, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ItemEntity> update(@Valid @NotNull @RequestBody ItemEntity store) {
        itemRepository.save(store);
        return ResponseEntity.ok(store);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return new ResponseEntity<>("Item Deleted", HttpStatus.NO_CONTENT);
    }
}
