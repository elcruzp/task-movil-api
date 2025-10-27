package com.trabajomovil.task_movil_api.controller;

import com.trabajomovil.task_movil_api.category.Category;
import com.trabajomovil.task_movil_api.category.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:8100"})
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // GET /api/categories
    @GetMapping
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    // GET /api/categories/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable Long id) {
        return categoryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST /api/categories
    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryRepository.save(category);
}

    // PUT /api/categories/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @Valid @RequestBody Category category) {
        return categoryRepository.findById(id)
                .map(existing -> {
                    category.setId(id);
                    Category updated = categoryRepository.save(category);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE /api/categories/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!categoryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
