package com.Sale_Campaign_Management_System.Reposotry;

import com.Sale_Campaign_Management_System.Model.Campaigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CampaignsRepo extends JpaRepository<Campaigns,Long> {

    @Query(value = "select * from campaigns where end_date<=:currentDate ",nativeQuery = true)
    List<Campaigns> findActiveCampaigns(String currentDate);
}
