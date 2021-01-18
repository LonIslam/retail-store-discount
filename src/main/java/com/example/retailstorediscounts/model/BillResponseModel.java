package com.example.retailstorediscounts.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BillResponseModel {
    private double totalAmount = 0.0;
    private double totalAmountWoGroceries = 0.0;
    private double totalPayableAmount = 0.0;
    private double percentageDiscount = 0.0;
    private double priceDiscount = 0.0;
    private List<String> appliedDiscounts = new ArrayList<>();
    private List<ItemModel> itemList = new ArrayList<>();
}
