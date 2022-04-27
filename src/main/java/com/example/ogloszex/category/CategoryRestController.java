package com.example.ogloszex.category;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/categories")
@RestController
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/names")
    public List<String> findAllNames() {
        List<CategoryDto> dtos = categoryService.findAll();
        return categoryService.categoryNames(dtos);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.deleteById(id);
    }

    @PostMapping("")
    public CategoryDto insert(@RequestBody CategoryDto dto) {
        return categoryService.insert(dto);
    }

    @GetMapping("")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

}
