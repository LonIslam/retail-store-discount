package com.example.retailstorediscounts.controller;

import com.example.retailstorediscounts.model.BillModel;
import com.example.retailstorediscounts.model.BillResponseModel;
import com.example.retailstorediscounts.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/discount")
public class DiscountController {

    private final DiscountService discountService;

    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }


    @PostMapping("/")
    public ResponseEntity<BillResponseModel> generatePayableBill(@RequestBody BillModel billModel) {
        log.info("REST request to discountBill [{}]", billModel);
        BillResponseModel billResponseModel = discountService.getPayableBill(billModel);
        return new ResponseEntity<>(billResponseModel, HttpStatus.OK);
    }
}
