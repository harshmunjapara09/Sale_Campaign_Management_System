package com.Sale_Campaign_Management_System.Reposotry;

import com.Sale_Campaign_Management_System.Model.Campaigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignsRepo extends JpaRepository<Campaigns,Long> {

}
