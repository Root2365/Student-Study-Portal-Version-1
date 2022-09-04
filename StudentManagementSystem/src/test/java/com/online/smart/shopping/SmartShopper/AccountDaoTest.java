package com.online.smart.shopping.SmartShopper;

import com.online.smart.shopping.SmartShopper.dao.AccountDAO;
import com.online.smart.shopping.SmartShopper.entity.Account;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountDaoTest {

    @Mock
    AccountDAO accountDAO;
    @Test
    public  void getTestClass(){
        Account account=new Account("manager1");
        when( accountDAO.findAccount("manager1")).thenReturn(new Account("manager1"));
        Assert.assertEquals(account.getUserName(),"manager1");
    }


}
