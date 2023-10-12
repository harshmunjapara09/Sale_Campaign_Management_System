package com.Sale_Campaign_Management_System.Reposotry;

import com.Sale_Campaign_Management_System.Model.PriceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceHistoryRepo extends JpaRepository<PriceHistory,Long> {
}
