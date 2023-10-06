//package com.Sale_Campaign_Management_System.Service;
//
package com.Sale_Campaign_Management_System.Service;

import com.Sale_Campaign_Management_System.Model.Campaigns;
import com.Sale_Campaign_Management_System.Model.Product;
import com.Sale_Campaign_Management_System.Model.dto.CompaignsDTO;
import com.Sale_Campaign_Management_System.Model.dto.ProductSale;
import com.Sale_Campaign_Management_System.Reposotry.CampaignsRepo;
import com.Sale_Campaign_Management_System.Reposotry.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CampaignsService {
    @Autowired
    CampaignsRepo campaignsRepo;
    @Autowired
    ProductRepo productRepo;

    public Campaigns createCampaigns(CompaignsDTO campaigns) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Update the date format pattern
        Date currentDate = new Date();
        String formattedDate = sdf.format(currentDate);

        Date current = sdf.parse(formattedDate);
        Date startDate = sdf.parse(campaigns.getStartdate());
        Date endDate = sdf.parse(campaigns.getEnddate());

        Campaigns campaigns1 = null;
        List<ProductSale> productList = campaigns.getCampaigndiscount();

        for (ProductSale productSale : productList) {
            campaigns1 = new Campaigns();
            campaigns1.setTitle(campaigns.getTitle());
            campaigns1.setStartDate(campaigns.getStartdate());
            campaigns1.setEndDate(campaigns.getEnddate());
            Long productId = productSale.getProductid();
            Optional<Product> productOptional = productRepo.findById(productId);

            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                campaigns1.setDiscount(productSale.getDiscount());
                campaigns1.setProductId(productId);
                campaigns1.setOldPrice(product.getCurrentPrice());
                campaignsRepo.save(campaigns1);

                if (current.after(startDate) && current.before(endDate)) {
                    Optional<Product> product1 = productRepo.findById(campaigns1.getProductId());
                    if (product1.isPresent()) {
                        Product UpdateProduct = product1.get();
                        Double currentprice = UpdateProduct.getCurrentPrice() - campaigns1.getDiscount();
                        UpdateProduct.setCurrentPrice(currentprice);
                        productRepo.save(UpdateProduct);
                    }
                }
                else if (current.after(endDate)) {
                    if (campaigns1 != null && campaigns1.getProductId() != null) {
                        Optional<Product> product1 = productRepo.findById(campaigns1.getProductId());

                        if (product1.isPresent()) {
                            Product UpdateProduct = product1.get();
                            Double currentprice = campaigns1.getOldPrice() + campaigns1.getDiscount();
                            UpdateProduct.setCurrentPrice(currentprice);
                            productRepo.save(UpdateProduct);
                        }
                    } else {
                        System.out.println("Campaigns or Product ID not available.");
                    }
                }
//                else if (current.after(endDate)) {
//                    Optional<Product> product1 = productRepo.findById(campaigns1.getProductId());
//
//                    if (product1.isPresent()) {
//                        Product UpdateProduct = product1.get();
//                        Double currentprice = campaigns1.getOldPrice();
//                        UpdateProduct.setCurrentPrice(currentprice);
//                        productRepo.save(UpdateProduct);
//                    }
//                }
            } else {
                System.out.println("Id not Found");
            }
        }
        return campaigns1;
    }
}