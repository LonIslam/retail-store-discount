package com.example.retailstorediscounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemModel implements Serializable {
    private String id;
    private int quantity;
    private double price;
    private String type;
}
