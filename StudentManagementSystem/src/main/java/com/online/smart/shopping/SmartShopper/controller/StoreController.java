package com.online.smart.shopping.SmartShopper.controller;

import com.online.smart.shopping.SmartShopper.dao.StoreDAO;
import com.online.smart.shopping.SmartShopper.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Transactional
public class StoreController {

    @Autowired
    StoreDAO storeDAO;

    @GetMapping("/store/register")
    public String store(Model model) {
        Store store = new Store();
        model.addAttribute("store", store);
        return "store";
    }

    @PostMapping("/store/submit")
    public String submitForm(@ModelAttribute("store") Store store ) {
        System.out.println("In store Done "+store);
        storeDAO.saveStore(store);
        return "redirect:/";
    }
}
