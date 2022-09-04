package com.online.smart.shopping.SmartShopper.utils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.online.smart.shopping.SmartShopper.model.CartInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import java.io.UnsupportedEncodingException;

public class Utils {

   // Products in the cart, stored in Session.
   public static CartInfo getCartInSession(HttpServletRequest request) {

      CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");

   
      if (cartInfo == null) {
         cartInfo = new CartInfo();
         
         request.getSession().setAttribute("myCart", cartInfo);
      }

      return cartInfo;
   }

   public static void removeCartInSession(HttpServletRequest request) {
      request.getSession().removeAttribute("myCart");
   }

   public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
      request.getSession().setAttribute("lastOrderedCart", cartInfo);
   }

   public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
      return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
   }

   public static String getSiteURL(HttpServletRequest request) {
      String siteURL = request.getRequestURL().toString();
      return siteURL.replace(request.getServletPath(), "");
   }



}
