package com.devsuperior.dsdeliver.controller;

import com.devsuperior.dsdeliver.DTO.OrderDTO;
import com.devsuperior.dsdeliver.DTO.ProductDTO;
import com.devsuperior.dsdeliver.services.OrderService;
import com.devsuperior.dsdeliver.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){

        List<OrderDTO> list = this.service.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
