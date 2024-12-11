package com.example.retail.controller;

import com.example.retail.model.PricingRecord;
import com.example.retail.service.CsvUploadService;
import com.example.retail.service.PricingRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pricing")
@CrossOrigin
public class PricingRecordController {

    private final PricingRecordService pricingRecordService;
    private final CsvUploadService csvUploadService;

    public PricingRecordController(PricingRecordService pricingRecordService, CsvUploadService csvUploadService) {
        this.pricingRecordService = pricingRecordService;
        this.csvUploadService = csvUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadPricingCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            List<PricingRecord> records = csvUploadService.parseCsv(new InputStreamReader(file.getInputStream()));
            pricingRecordService.saveAll(records);
            return ResponseEntity.ok("Upload successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error parsing CSV: " + e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<PricingRecord>> search(
            @RequestParam(required = false) String storeId,
            @RequestParam(required = false) String sku) {

        if (storeId != null) {
            return ResponseEntity.ok(pricingRecordService.searchByStoreId(storeId));
        } else if (sku != null) {
            return ResponseEntity.ok(pricingRecordService.searchBySku(sku));
        } else {
            return ResponseEntity.ok(pricingRecordService.findAll());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PricingRecord> updateRecord(@PathVariable Long id, @RequestBody PricingRecord updatedRecord) {
        Optional<PricingRecord> existing = pricingRecordService.findById(id);
        if (existing.isPresent()) {
            PricingRecord record = existing.get();
            record.setStoreId(updatedRecord.getStoreId());
            record.setSku(updatedRecord.getSku());
            record.setProductName(updatedRecord.getProductName());
            record.setPrice(updatedRecord.getPrice());
            record.setRecordDate(updatedRecord.getRecordDate());
            PricingRecord saved = pricingRecordService.updateRecord(record);
            return ResponseEntity.ok(saved);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
