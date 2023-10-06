package com.Sale_Campaign_Management_System.Controller;

import com.Sale_Campaign_Management_System.Model.Campaigns;
import com.Sale_Campaign_Management_System.Model.Product;
import com.Sale_Campaign_Management_System.Model.dto.ProductDTO;
import com.Sale_Campaign_Management_System.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("harsh-store")
public class ProductContoller {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public List<Product> add(@RequestBody List<Product> p){
        return productService.save(p);
    }

    @GetMapping("/get")
    public ProductDTO get(@RequestParam Integer page, Integer pageSize){
        return productService.get(page,pageSize);
    }


}
