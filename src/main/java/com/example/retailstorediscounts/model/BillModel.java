package com.example.retailstorediscounts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillModel {

    private Long userId;
    private List<ItemModel> items;
}
