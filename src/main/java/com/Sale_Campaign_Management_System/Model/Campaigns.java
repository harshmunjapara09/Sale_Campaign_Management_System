package com.Sale_Campaign_Management_System.Model;

import com.Sale_Campaign_Management_System.Model.dto.ProductSale;
import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Campaigns {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String startDate;
    private String endDate;
    private String title;
    private Long productId;
    private Double discount;
    private Double oldPrice;
    private String formattedTime;

    @PrePersist
    private void prePersist() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Define your desired date/time format
        this.formattedTime = sdf.format(new Date(System.currentTimeMillis()));
    }
}
