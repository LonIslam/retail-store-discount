package com.example.retailstorediscounts;

import com.example.retailstorediscounts.dao.BillDao;
import com.example.retailstorediscounts.dao.UsersDao;
import com.example.retailstorediscounts.entity.User;
import com.example.retailstorediscounts.entity.UserType;
import com.example.retailstorediscounts.model.BillModel;
import com.example.retailstorediscounts.model.BillResponseModel;
import com.example.retailstorediscounts.model.ItemModel;
import com.example.retailstorediscounts.service.DiscountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RetailStoreDiscountsApplicationTests {

    @Mock
    UsersDao usersDao;
    @Mock
    BillDao billDao;

    @InjectMocks
    DiscountService discountService;

    @Before
    public void init() {
        when(usersDao.findById(anyLong())).
                thenAnswer(invocationOnMock -> Optional.of(new User(1l, UserType.EMPLOYEE, "test")));
    }

    @Test
    public void testPayableBill() {
        BillModel billModel = new BillModel();
        billModel.setUserId(1l);
        List<ItemModel> items = new ArrayList<>();
        ItemModel meatItem = new ItemModel("", 5, 210.5, "meat");
        ItemModel groceryModel = new ItemModel("", 5, 50.25, "groceries");
        ItemModel breadModel = new ItemModel("", 4, 400.0, "bread");
        items.add(meatItem);
        items.add(groceryModel);
        items.add(breadModel);

        billModel.setItems(items);
        BillResponseModel billResponseModel = discountService.getPayableBill(billModel);
        assertThat(billResponseModel.getTotalAmount(), is(2903.75));
        assertThat(billResponseModel.getTotalPayableAmount(), is(1963.0));
        assertThat(billResponseModel.getPercentageDiscount(), is(795.75));
        assertThat(billResponseModel.getPriceDiscount(), is(145.0));
    }

}
