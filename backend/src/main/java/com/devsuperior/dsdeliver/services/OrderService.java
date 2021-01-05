package com.devsuperior.dsdeliver.services;

import com.devsuperior.dsdeliver.DTO.OrderDTO;
import com.devsuperior.dsdeliver.DTO.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepositorie;
import com.devsuperior.dsdeliver.repositories.ProductRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepositorie repositorie;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        try{
            List<Order> list = this.repositorie.findOrderWithProducts();
            return list.stream().map(order -> new OrderDTO(order)).collect(Collectors.toList());

        } catch (Exception e) {
           throw e;
        }
    }
}
