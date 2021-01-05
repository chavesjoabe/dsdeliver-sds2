package com.devsuperior.dsdeliver.services;

import com.devsuperior.dsdeliver.DTO.OrderDTO;
import com.devsuperior.dsdeliver.DTO.ProductDTO;
import com.devsuperior.dsdeliver.entities.Order;
import com.devsuperior.dsdeliver.entities.OrderStatus;
import com.devsuperior.dsdeliver.entities.Product;
import com.devsuperior.dsdeliver.repositories.OrderRepositorie;

import com.devsuperior.dsdeliver.repositories.ProductRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepositorie repositorie;

    @Autowired
    private ProductRepositorie productRepositorie;

    @Transactional(readOnly = true)
    public List<OrderDTO> findAll(){
        try{
            List<Order> list = this.repositorie.findOrderWithProducts();
            return list.stream().map(order -> new OrderDTO(order)).collect(Collectors.toList());

        } catch (Exception e) {
           throw e;
        }
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO){
        Order order = new Order(null, orderDTO.getAddress(), orderDTO.getLatitude(),
                                orderDTO.getLongitude(), Instant.now(), OrderStatus.PENDING);

        for(ProductDTO p : orderDTO.getProducts() ){
            Product product = this.productRepositorie.getOne(p.getId());
            order.getProducts().add(product);
        }
        order = repositorie.save(order);

        return new OrderDTO(order);
    }
    @Transactional
    public OrderDTO setDelivered(Long id){
        Order order = this.repositorie.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);

        order = repositorie.save(order);

        return new OrderDTO(order);
    }
}
