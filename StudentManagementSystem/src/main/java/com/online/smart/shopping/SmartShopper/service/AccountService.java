package com.online.smart.shopping.SmartShopper.service;

import com.online.smart.shopping.SmartShopper.dao.AccountDAO;
import com.online.smart.shopping.SmartShopper.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountDAO accountDAO;

    public Account getByResetPasswordToken(String token) {
        return accountDAO.findByResetPasswordToken(token);
    }

    public void updatePassword(Account account, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        account.setEncrytedPassword(encodedPassword);
        account.setResetPasswordToken(null);
        accountDAO.saveAccount(account);
    }
    public void updateResetPasswordToken(String token, String email) /*throws CustomerNotFoundException*/ {
        Account customer = accountDAO.findByEmail(email);
        if (customer != null) {
            customer.setResetPasswordToken(token);
            accountDAO.saveAccount(customer);
        } else {
            // throw new AccountNotFoundException("Could not find any customer with the email " + email);
        }
    }
}
