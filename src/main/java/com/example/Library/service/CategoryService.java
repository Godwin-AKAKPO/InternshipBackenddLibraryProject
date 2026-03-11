package com.example.Library.service;


import com.example.Library.model.Category;
import com.example.Library.model.Loan;
import com.example.Library.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){

        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            return null;
        }else{
            return optionalCategory.get();
        }

    }
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}
