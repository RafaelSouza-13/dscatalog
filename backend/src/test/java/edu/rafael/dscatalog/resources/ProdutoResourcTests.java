package edu.rafael.dscatalog.resources;

import edu.rafael.dscatalog.dto.ProductDTO;
import edu.rafael.dscatalog.services.ProductService;
import edu.rafael.dscatalog.tests.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

@WebMvcTest(ProductResource.class)
public class ProdutoResourcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService service;
    private ProductDTO productDTO;
    private PageImpl<ProductDTO> page;

    @BeforeEach
    void setUp(){
        productDTO = Factory.createProductDTO();
        page = new PageImpl<>(List.of(productDTO));
        Mockito.when(service.findAllPaged(ArgumentMatchers.any())).thenReturn(page);
    }

    @Test
    public void findAllShouldReturnPage() throws Exception{
        ResultActions result = mockMvc.perform(get("/products/").
                accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());

    }
}
