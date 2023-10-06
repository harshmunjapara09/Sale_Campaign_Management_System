package com.Sale_Campaign_Management_System.Model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompaignsDTO {
    String startdate;
    String enddate;
    String title;
    List<ProductSale> campaigndiscount;

}
