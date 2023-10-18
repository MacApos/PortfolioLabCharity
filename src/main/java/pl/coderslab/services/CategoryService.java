package pl.coderslab.services;

import org.springframework.stereotype.Service;
import pl.coderslab.domain.Category;
import pl.coderslab.repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

}
