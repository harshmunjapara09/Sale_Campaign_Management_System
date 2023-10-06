package com.Sale_Campaign_Management_System.Service;

import com.Sale_Campaign_Management_System.Model.Campaigns;
import com.Sale_Campaign_Management_System.Model.Product;
import com.Sale_Campaign_Management_System.Model.dto.ProductDTO;
import com.Sale_Campaign_Management_System.Reposotry.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepo productRepo;

    public List<Product> save(List<Product> p) {
        return productRepo.saveAll(p);
    }

    public ProductDTO get(Integer page, Integer pageSize) {

        List<Product> getAll = productRepo.findAll();
        int pageNo = 1;
        List<Product> list = new ArrayList<>();
        for (Product p : getAll) {
            if (list.size() == pageSize) {
                if (pageNo == page) {
                    break;
                }
                pageNo++;
                list.clear();
            }
            list.add(p);
        }

        double result = (double) getAll.size() / pageSize;
        int totalpage = (int) Math.ceil(result);

        ProductDTO ans = new ProductDTO();
        ans.setProduct(list);
        ans.setPage(page);
        ans.setTotalPage(totalpage);
        ans.setPageSize(pageSize);
        return ans;

//        Page<Product> productPage = productRepo.findAll(PageRequest.of(page - 1, pageSize));
//
//        List<Product> productList = productPage.getContent();
//        int totalPage = ((Page<?>) productPage).getTotalPages();
//
//        ProductDTO ans = new ProductDTO();
//        ans.setProduct(productList);
//        ans.setPage(page);
//        ans.setTotalPage(totalPage);
//        ans.setPageSize(pageSize);
//
//        return ans;
    }

//    public Campaigns sale(Campaigns c) {
//
//    }
}
