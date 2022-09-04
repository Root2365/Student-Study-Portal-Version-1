package com.online.smart.shopping.SmartShopper;

import com.online.smart.shopping.SmartShopper.dao.AccountDAO;
import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.utils.Utils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UtilTest {

    @Mock
    Utils utils;
    @Test
    public  void getTestClass(){
              Assert.assertEquals(1,"manager1");

    }


}
