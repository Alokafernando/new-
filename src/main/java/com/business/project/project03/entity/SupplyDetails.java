package com.business.project.project03.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class SupplyDetails {
    private String supplier_id;
    private String part_id;
    private String supply_date;
    private int quantity;
    private double total;
}
