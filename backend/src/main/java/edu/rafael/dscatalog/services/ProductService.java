package edu.rafael.dscatalog.services;

import edu.rafael.dscatalog.dto.ProductDTO;
import edu.rafael.dscatalog.entities.Product;
import edu.rafael.dscatalog.repositories.ProductRepository;
import edu.rafael.dscatalog.services.exceptions.DatabaseException;
import edu.rafael.dscatalog.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(){
         List<Product> products = productRepository.findAll();
         return products.stream().map(ProductDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> findAllPaged(PageRequest page){
        Page<Product> products = productRepository.findAll(page);
        return products.map(ProductDTO::new);
    }

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Categoria não encontrada")
        );
        return new ProductDTO(product, product.getCategories());
    }

    @Transactional
    public ProductDTO insert(ProductDTO dto){
        Product Product = new Product();
        Product = productRepository.save(Product);
        return new ProductDTO(Product);
    }

    @Transactional
    public ProductDTO update(Long id, ProductDTO dto){
        try{
            Product Product = productRepository.getReferenceById(id);
            Product.setName(dto.getName());
            Product = productRepository.save(Product);
            return new ProductDTO(Product);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id: "+id+" não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ResourceNotFoundException("Categoria não encontrado");
        }
        try {
            productRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
