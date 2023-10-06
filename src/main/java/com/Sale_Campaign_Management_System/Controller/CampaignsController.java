package com.Sale_Campaign_Management_System.Controller;

import com.Sale_Campaign_Management_System.Model.Campaigns;
import com.Sale_Campaign_Management_System.Model.dto.CompaignsDTO;
import com.Sale_Campaign_Management_System.Service.CampaignsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("CreateCampaign")
public class CampaignsController {

    @Autowired
    CampaignsService campaignsService;

    @PostMapping("/campaign")
    public Campaigns campaigns(@RequestBody CompaignsDTO campaigns) throws ParseException {
        return campaignsService.createCampaigns(campaigns);
    }
}
