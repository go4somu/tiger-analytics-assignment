package com.example.retail;

import com.example.retail.model.PricingRecord;
import com.example.retail.repository.PricingRecordRepository;
import com.example.retail.service.PricingRecordService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PricingRecordServiceTest {

    @Test
    public void testSaveAll() {
        PricingRecordRepository mockRepo = Mockito.mock(PricingRecordRepository.class);
        PricingRecordService service = new PricingRecordService(mockRepo);

        List<PricingRecord> records = new ArrayList<>();
        PricingRecord record = new PricingRecord();
        record.setStoreId("S1");
        record.setSku("SKU1");
        record.setProductName("Test Product");
        record.setPrice(new BigDecimal("9.99"));
        record.setRecordDate(LocalDate.now());
        records.add(record);

        service.saveAll(records);
        Mockito.verify(mockRepo, Mockito.times(1)).saveAll(records);
    }

    @Test
    public void testSearchByStoreId() {
        PricingRecordRepository mockRepo = Mockito.mock(PricingRecordRepository.class);
        PricingRecordService service = new PricingRecordService(mockRepo);
        List<PricingRecord> mockResult = new ArrayList<>();
        Mockito.when(mockRepo.findByStoreId("S1")).thenReturn(mockResult);

        List<PricingRecord> result = service.searchByStoreId("S1");
        assertEquals(mockResult, result);
    }
}
