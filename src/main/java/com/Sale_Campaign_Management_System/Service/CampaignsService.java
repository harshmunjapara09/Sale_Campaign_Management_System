//package com.Sale_Campaign_Management_System.Service;
//
package com.Sale_Campaign_Management_System.Service;

import com.Sale_Campaign_Management_System.Model.Campaigns;
import com.Sale_Campaign_Management_System.Model.PriceHistory;
import com.Sale_Campaign_Management_System.Model.Product;
import com.Sale_Campaign_Management_System.Model.dto.CompaignsDTO;
import com.Sale_Campaign_Management_System.Model.dto.ProductSale;
import com.Sale_Campaign_Management_System.Reposotry.CampaignsRepo;
import com.Sale_Campaign_Management_System.Reposotry.PriceHistoryRepo;
import com.Sale_Campaign_Management_System.Reposotry.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@EnableScheduling
@Service
public class CampaignsService {
    @Autowired
    CampaignsRepo campaignsRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    PriceHistoryRepo priceHistoryRepo;

    public Campaigns createCampaigns(CompaignsDTO campaigns) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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

                PriceHistory priceHistory= new PriceHistory();
                priceHistory.setPrice(product.getCurrentPrice());
                priceHistory.setProductId(product.getId());
                priceHistoryRepo.save(priceHistory);


                if (current.after(startDate) && current.before(endDate)) {
                    Optional<Product> product1 = productRepo.findById(campaigns1.getProductId());
                    if (product1.isPresent()) {
                        Product UpdateProduct = product1.get();
                        Double currentprice = UpdateProduct.getCurrentPrice() - campaigns1.getDiscount();
                        UpdateProduct.setCurrentPrice(currentprice);
                        productRepo.save(UpdateProduct);
                    }
                }
            } else {
                System.out.println("Id not Found");
            }
        }
        return campaigns1;
    }

        @Scheduled(fixedRate = 86400000) // This will run the method every minute, adjust the rate as needed
//    @Scheduled(cron = "0 0 23 * * ?")
    public void updateProductPrices() {
        Date currentDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //
        String formattedDate = sdf.format(currentDate);
        List<Campaigns> activeCampaigns = campaignsRepo.findActiveCampaigns("2023-10-19");

        for (Campaigns campaign : activeCampaigns) {
            // Calculate new prices based on the campaign's old price and discount
            Double currentPrice = campaign.getOldPrice();

            // Update the product's price in the repository
            Optional<Product> productOptional = productRepo.findById(campaign.getProductId());
            if (productOptional.isPresent()) {
                Product product = productOptional.get();
                product.setCurrentPrice(currentPrice);
                productRepo.save(product);
            }
        }
    }
}