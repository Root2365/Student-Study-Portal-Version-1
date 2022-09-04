package com.online.smart.shopping.SmartShopper.controller;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import com.online.smart.shopping.SmartShopper.dao.AccountDAO;
import com.online.smart.shopping.SmartShopper.dao.OrderDAO;
import com.online.smart.shopping.SmartShopper.dao.ProductDAO;
import com.online.smart.shopping.SmartShopper.entity.Account;
import com.online.smart.shopping.SmartShopper.entity.Product;
import com.online.smart.shopping.SmartShopper.form.ProductForm;
import com.online.smart.shopping.SmartShopper.model.OrderDetailInfo;
import com.online.smart.shopping.SmartShopper.model.OrderInfo;
import com.online.smart.shopping.SmartShopper.model.ProductInfo;
import com.online.smart.shopping.SmartShopper.pagination.PaginationResult;
import com.online.smart.shopping.SmartShopper.service.AccountService;
import com.online.smart.shopping.SmartShopper.utils.Utils;
import com.online.smart.shopping.SmartShopper.validator.ProductFormValidator;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Transactional
public class AdminController {

   public static final String  GROCERY="GROCERY";
   public static final String  GARMENTS="GARMENTS";
   public static final String  ELECTRONICS="ELECTRONICS";
   public static final String  MOBILE="MOBILE";
   public static final String  BOOKS="BOOKS";

   @Autowired
   private JavaMailSender mailSender;

   @Autowired
   private OrderDAO orderDAO;

   @Autowired
   private ProductDAO productDAO;

   @Autowired
   private ProductFormValidator productFormValidator;


   @Autowired
   AccountDAO accountDAO;
   
   @Autowired
   AccountService accountService;


   @InitBinder
   public void myInitBinder(WebDataBinder dataBinder) {
      Object target = dataBinder.getTarget();
      if (target == null) {
         return;
      }
      System.out.println("Target=" + target);

      if (target.getClass() == ProductForm.class) {
         dataBinder.setValidator(productFormValidator);
      }
   }

   // GET: Show Login Page
   @RequestMapping(value = { "/admin/login" }, method = RequestMethod.GET)
   public String login(Model model) {

      return "login";
   }

   @RequestMapping(value = { "/admin/accountInfo" }, method = RequestMethod.GET)
   public String accountInfo(Model model) {

      UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      System.out.println(userDetails.getPassword());
      System.out.println(userDetails.getUsername());
      System.out.println(userDetails.isEnabled());

      model.addAttribute("userDetails", userDetails);
      return "accountInfo";
   }

   @RequestMapping(value = { "/admin/orderList" }, method = RequestMethod.GET)
   public String orderList(Model model, //
                           @RequestParam(value = "page", defaultValue = "1") String pageStr) {
      int page = 1;
      try {
         page = Integer.parseInt(pageStr);
      } catch (Exception e) {
      }
      final int MAX_RESULT = 5;
      final int MAX_NAVIGATION_PAGE = 10;

      PaginationResult<OrderInfo> paginationResult //
              = orderDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);

      model.addAttribute("paginationResult", paginationResult);
      return "orderList";
   }

   // GET: Show product.
   @RequestMapping(value = { "/admin/product" }, method = RequestMethod.GET)
   public String product(Model model, @RequestParam(value = "code", defaultValue = "") String code) {
      ProductForm productForm = null;

      if (code != null && code.length() > 0) {
         Product product = productDAO.findProduct(code);
         if (product != null) {
            productForm = new ProductForm(product);
         }
      }
      if (productForm == null) {
         productForm = new ProductForm();
         productForm.setNewProduct(true);
      }
      List<String> listProfession = Arrays.asList(MOBILE, ELECTRONICS, GROCERY,GARMENTS,BOOKS);
      model.addAttribute("listProfession", listProfession);

      model.addAttribute("productForm", productForm);
      return "product";
   }

   @RequestMapping(value = { "/admin/product/delete" }, method = RequestMethod.GET)
   public String deleteProduct(Model model, @RequestParam(value = "code", defaultValue = "") String code) {

      if (code != null && code.length() > 0) {
         productDAO.deleteProduct(code);

      }     final int maxResult = 5;
      final int maxNavigationPage = 10;
      PaginationResult<ProductInfo> result = productDAO.queryProducts(1,
              maxResult, maxNavigationPage, "","EMPTY","EMPTY");

      List<String> listProfession = Arrays.asList("STORE", "CATEGORY","NAME","SALE_ITEM");
      model.addAttribute("listProfession", listProfession);
      model.addAttribute("filterValue", "EMPTY");
      model.addAttribute("category", "EMPTY");

      model.addAttribute("paginationProducts", result);
      return "productList";
   }

   // POST: Save product
   @RequestMapping(value = { "/admin/product" }, method = RequestMethod.POST)
   public String productSave(Model model, //
                             @ModelAttribute("productForm") @Validated ProductForm productForm, //
                             BindingResult result, //
                             final RedirectAttributes redirectAttributes) {

      if (result.hasErrors()) {
         return "product";
      }
      try {
         productDAO.save(productForm);
      } catch (Exception e) {
         Throwable rootCause = ExceptionUtils.getRootCause(e);
         String message = rootCause.getMessage();
         model.addAttribute("errorMessage", message);
         // Show product form.
         return "product";
      }

      return "redirect:/productList";
   }

   @RequestMapping(value = { "/admin/order" }, method = RequestMethod.GET)
   public String orderView(Model model, @RequestParam("orderId") String orderId) {
      OrderInfo orderInfo = null;
      if (orderId != null) {
         orderInfo = this.orderDAO.getOrderInfo(orderId);
      }
      if (orderInfo == null) {
         return "redirect:/admin/orderList";
      }
      List<OrderDetailInfo> details = this.orderDAO.listOrderDetailInfos(orderId);
      orderInfo.setDetails(details);

      model.addAttribute("orderInfo", orderInfo);

      return "order";
   }

   @RequestMapping(value="/admin/logout", method=RequestMethod.GET)
   public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
      System.out.println("In the /admin/logout");
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null){
         new SecurityContextLogoutHandler().logout(request, response, auth);
      }
      return "redirect:/";
   }

   @RequestMapping("/new/{role}/register")
   public String register(Model model,@PathVariable String role) {
      System.out.println("In new register "+role);

      Account account = new Account();
      model.addAttribute("account", account);
      model.addAttribute("manager", false);
      model.addAttribute("role", role);
      List<String> listProfession = Arrays.asList(MOBILE, ELECTRONICS, GROCERY,GARMENTS,BOOKS);
      model.addAttribute("listProfession", listProfession);

      return "register";
   }

   @PostMapping("/add/{role}/account")
   public String submitForm(@ModelAttribute("account") Account account , @PathVariable String role) {
      System.out.println("In Register Done "+role);
      System.out.println(account.toString());
      account.setUserRole(role);
      account.setActive(true);
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      String encodedPassword = passwordEncoder.encode(account.getEncrytedPassword());
      account.setEncrytedPassword(encodedPassword);
      accountDAO.saveAccount(account);
      return "redirect:/";
   }

   @RequestMapping(value = { "/studentList" }, method = RequestMethod.GET)
   public String studentList(Model model, //
                           @RequestParam(value = "page", defaultValue = "1") String pageStr) {
      int page = 1;
      try {
         page = Integer.parseInt(pageStr);
      } catch (Exception e) {
      }
      final int MAX_RESULT = 5;
      final int MAX_NAVIGATION_PAGE = 10;

      PaginationResult<Account> paginationResult //
              = accountDAO.listOrderInfo(page, MAX_RESULT, MAX_NAVIGATION_PAGE);
      System.out.println("Account founds"+paginationResult.getList());
      model.addAttribute("paginationResult", paginationResult);
      return "studentList";
   }

   @GetMapping("/forgot_password")
   public String showForgotPasswordForm() {
      return "forgot_password_form";
   }

   @PostMapping("/forgot_password")
   public String processForgotPassword(HttpServletRequest request, Model model) throws javax.mail.MessagingException {
      String email = request.getParameter("email");
      String token = RandomString.make(30);
      try {
         accountService.updateResetPasswordToken(token, email);
         String resetPasswordLink = Utils.getSiteURL(request) + "/reset_password?token=" + token;
         sendEmail(email, resetPasswordLink);
         model.addAttribute("message", "We have sent a reset password link to your email. Please check.");

      } /*catch (CustomerNotFoundException ex) {
         model.addAttribute("error", ex.getMessage());
      }*/ catch (UnsupportedEncodingException | MessagingException e) {
         model.addAttribute("error", "Error while sending email");
      }
      return "forgot_password_form";
   }
   public void sendEmail(String recipientEmail, String link)
           throws MessagingException, UnsupportedEncodingException, javax.mail.MessagingException {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(message);

      helper.setFrom("contact@shopme.com", "Shopme Support");
      helper.setTo(recipientEmail);

      String subject = "Here's the link to reset your password";

      String content = "<p>Hello,</p>"
              + "<p>You have requested to reset your password.</p>"
              + "<p>Click the link below to change your password:</p>"
              + "<p><a href=\"" + link + "\">Change my password</a></p>"
              + "<br>"
              + "<p>Ignore this email if you do remember your password, "
              + "or you have not made the request.</p>";

      helper.setSubject(subject);

      helper.setText(content, true);

      mailSender.send(message);
   }
   @GetMapping("/reset_password")
   public String showResetPasswordForm(@Param(value = "token") String token, Model model) {
      Account customer = accountService.getByResetPasswordToken(token);
      model.addAttribute("token", token);

      if (customer == null) {
         model.addAttribute("message", "Invalid Token");
         return "message";
      }

      return "reset_password_form";
   }


   @PostMapping("/reset_password")
   public String processResetPassword(HttpServletRequest request, Model model) {
      String token = request.getParameter("token");
      String password = request.getParameter("password");

      Account customer = accountService.getByResetPasswordToken(token);
      model.addAttribute("title", "Reset your password");

      if (customer == null) {
         model.addAttribute("message", "Invalid Token");
         return "message";
      } else {
         accountService.updatePassword(customer, password);

         model.addAttribute("message", "You have successfully changed your password.");
      }

      return "message";
   }
}
