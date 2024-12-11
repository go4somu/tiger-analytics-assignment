package com.example.retail.repository;

import com.example.retail.model.PricingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PricingRecordRepository extends JpaRepository<PricingRecord, Long> {
    List<PricingRecord> findByStoreId(String storeId);
    List<PricingRecord> findBySku(String sku);
}
