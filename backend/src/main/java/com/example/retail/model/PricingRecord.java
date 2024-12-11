package com.example.retail.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pricing_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PricingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="store_id", nullable=false)
    private String storeId;

    @Column(name="sku", nullable=false)
    private String sku;

    @Column(name="product_name")
    private String productName;

    @Column(name="price", precision=10, scale=2)
    private BigDecimal price;

    @Column(name="record_date")
    private LocalDate recordDate;
}
