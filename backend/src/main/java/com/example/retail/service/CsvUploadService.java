package com.example.retail.service;

import com.example.retail.model.PricingRecord;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvUploadService {

    public List<PricingRecord> parseCsv(InputStreamReader reader) throws Exception {
        try (CSVReader csvReader = new CSVReader(reader)) {
            List<String[]> lines = csvReader.readAll();
            List<PricingRecord> records = new ArrayList<>();
            // Assuming the first line is a header: Store ID,SKU,Product Name,Price,Date
            for (int i = 1; i < lines.size(); i++) {
                String[] row = lines.get(i);
                PricingRecord record = new PricingRecord();
                record.setStoreId(row[0]);
                record.setSku(row[1]);
                record.setProductName(row[2]);
                record.setPrice(new BigDecimal(row[3]));
                record.setRecordDate(LocalDate.parse(row[4]));
                records.add(record);
            }
            return records;
        } catch (CsvException e) {
            throw new Exception("Error reading CSV", e);
        }
    }
}
