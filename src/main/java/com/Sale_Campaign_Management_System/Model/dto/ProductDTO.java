package com.Sale_Campaign_Management_System.Model.dto;

import com.Sale_Campaign_Management_System.Model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductDTO {
    private List<Product> product;
    private Integer page;
    private Integer pageSize;
    private Integer totalPage;
}
