package com.devsuperior.dsdeliver.services;

import com.devsuperior.dsdeliver.DTO.ProductDTO;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.ProductRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepositorie repositorie;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(){
        try{
            List<Product> list = this.repositorie.findAllByOrderByNameAsc();
            return list.stream().map(product -> new ProductDTO(product)).collect(Collectors.toList());

        } catch (Exception e) {
           throw e;
        }
    }
}
