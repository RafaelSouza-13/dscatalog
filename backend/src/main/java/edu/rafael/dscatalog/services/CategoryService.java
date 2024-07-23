package edu.rafael.dscatalog.services;

import edu.rafael.dscatalog.dto.CategoryDTO;
import edu.rafael.dscatalog.entities.Category;
import edu.rafael.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public List<CategoryDTO> findAll(){
         List<Category> categories = categoryRepository.findAll();
         return categories.stream().map(CategoryDTO::new).toList();
    }
}
