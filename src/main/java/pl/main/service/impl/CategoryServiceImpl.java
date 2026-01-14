package pl.main.service.impl;

import org.springframework.stereotype.Service;
import pl.main.entity.Category;
import pl.main.repository.CategoryRepository;
import pl.main.service.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
