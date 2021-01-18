package com.example.retailstorediscounts.service;

import com.example.retailstorediscounts.dao.BillDao;
import com.example.retailstorediscounts.dao.UsersDao;
import com.example.retailstorediscounts.entity.Bill;
import com.example.retailstorediscounts.entity.User;
import com.example.retailstorediscounts.entity.UserType;
import com.example.retailstorediscounts.model.BillModel;
import com.example.retailstorediscounts.model.BillResponseModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DiscountService {

    private final BillDao billDao;
    private final UsersDao usersDao;

    public DiscountService(UsersDao usersDao, BillDao billDao) {
        this.billDao = billDao;
        this.usersDao = usersDao;
    }


    public void calculateDiscountPercentage(Long userId, BillResponseModel billResponseModel) {
        Optional<User> userOptional = usersDao.findById(userId);
        if (userOptional.isPresent() && userOptional.get().getUserType().name().equals(UserType.EMPLOYEE.name())) {
            billResponseModel.getAppliedDiscounts().add("30% Discount");
            billResponseModel.setPercentageDiscount(billResponseModel.getTotalAmountWoGroceries() * 0.3);
        } else if (userOptional.isPresent() && userOptional.get().getUserType().name().equals(UserType.AFFILIATE.name())) {
            billResponseModel.getAppliedDiscounts().add("10% Discount");
            billResponseModel.setPercentageDiscount(billResponseModel.getTotalAmountWoGroceries() * 0.1);
        } else if (userOptional.isPresent() && userOptional.get().getUserType().name().equals(UserType.CUSTOMER.name())
                && userOptional.get().getCreationTimestamp().isBefore(LocalDateTime.now().minusYears(2L))) {
            billResponseModel.getAppliedDiscounts().add("5% Discount");
            billResponseModel.setPercentageDiscount(billResponseModel.getTotalAmountWoGroceries() * 0.05);
        }
    }

    public void calculatePriceDiscount(BillResponseModel billResponseModel) {
        int hundredsNum = (int) billResponseModel.getTotalAmount() / 100;
        int deservedAmount = hundredsNum * 5;
        billResponseModel.setPriceDiscount(deservedAmount);
        billResponseModel.getAppliedDiscounts().add("5$ per $100");
    }

    public BillResponseModel getPayableBill(BillModel billModel) {
        BillResponseModel billResponseModel = new BillResponseModel();
        billModel.getItems().forEach(itemModel -> {
            double itemTotalPrice = itemModel.getQuantity() * itemModel.getPrice();
            billResponseModel.setTotalAmount(billResponseModel.getTotalAmount() + itemTotalPrice);
            billResponseModel.getItemList().add(itemModel);
        });

        billModel.getItems().stream().filter(itemModel -> !itemModel.getType().equals("groceries")).forEach(itemModel -> {
            double itemTotalPrice = itemModel.getQuantity() * itemModel.getPrice();
            billResponseModel.setTotalAmountWoGroceries(billResponseModel.getTotalAmountWoGroceries() + itemTotalPrice);
        });

        calculateDiscountPercentage(billModel.getUserId(), billResponseModel);
        calculatePriceDiscount(billResponseModel);
        billResponseModel.setTotalPayableAmount(billResponseModel.getTotalAmount() - billResponseModel.getPriceDiscount() - billResponseModel.getPercentageDiscount());
        Bill bill = mapBillModelToEntity(billResponseModel, billModel.getUserId());
        billDao.save(bill);
        return billResponseModel;
    }

    private Bill mapBillModelToEntity(BillResponseModel billResponseModel, Long userId) {
        Optional<User> userOptional = usersDao.findById(userId);
        Bill bill = new Bill();
        bill.setPriceDiscount(billResponseModel.getPriceDiscount());
        bill.setPercentageDiscount(billResponseModel.getPercentageDiscount());
        bill.setTotalPayableAmount(billResponseModel.getTotalPayableAmount());
        bill.setTotalAmount(billResponseModel.getTotalAmount());
        bill.setTotalAmountWoGroceries(billResponseModel.getTotalAmountWoGroceries());
        if (userOptional.isPresent()) {
            bill.setUser(userOptional.get());
        }
        bill.setItemList(billResponseModel.getItemList());
        return bill;
    }
}
