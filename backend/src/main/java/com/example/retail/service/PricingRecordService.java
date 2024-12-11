package com.example.retail.service;

import com.example.retail.model.PricingRecord;
import com.example.retail.repository.PricingRecordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PricingRecordService {

    private final PricingRecordRepository repository;

    public PricingRecordService(PricingRecordRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveAll(List<PricingRecord> records) {
        repository.saveAll(records);
    }

    public List<PricingRecord> searchByStoreId(String storeId) {
        return repository.findByStoreId(storeId);
    }

    public List<PricingRecord> searchBySku(String sku) {
        return repository.findBySku(sku);
    }

    public List<PricingRecord> findAll() {
        return repository.findAll();
    }

    public Optional<PricingRecord> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public PricingRecord updateRecord(PricingRecord record) {
        return repository.save(record);
    }
}
