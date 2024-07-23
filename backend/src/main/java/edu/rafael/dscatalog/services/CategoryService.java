package edu.rafael.dscatalog.services;

import edu.rafael.dscatalog.dto.CategoryDTO;
import edu.rafael.dscatalog.entities.Category;
import edu.rafael.dscatalog.repositories.CategoryRepository;
import edu.rafael.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll(){
         List<Category> categories = categoryRepository.findAll();
         return categories.stream().map(CategoryDTO::new).toList();
    }

    public CategoryDTO findById(Long id){
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada")
        );
        return new CategoryDTO(category);
    }
}
