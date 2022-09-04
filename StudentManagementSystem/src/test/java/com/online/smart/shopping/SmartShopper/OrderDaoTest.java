package com.online.smart.shopping.SmartShopper;

import com.online.smart.shopping.SmartShopper.dao.AccountDAO;
import com.online.smart.shopping.SmartShopper.dao.OrderDAO;
import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.entity.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDaoTest {

    @Mock
    OrderDAO orderDAO;

    @Test
    public  void getTestClass(){
        Order order=new Order("order1");
        when( orderDAO.findOrder("order1")).thenReturn(new Order("order1"));
        Assert.assertEquals(order.getId(),"order1");
    }


}
