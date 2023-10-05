package com.Sale_Campaign_Management_System.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Double mrp;
    private Double currentPrice;
    private Double discount;
    private Integer inventory;
    private String formattedTime;

    @PrePersist
    private void prePersist() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // Define your desired date/time format
        this.formattedTime = sdf.format(new Date(System.currentTimeMillis()));
    }

}
