package org.DemoSto;

import org.DemoSto.dao.RepositorySto;
import org.DemoSto.data.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class GreetingController {
    @Autowired
    private RepositorySto repositorySto;


    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
//    @GetMapping
//    public String main(Map<String, Object> model) {
//        Iterable<Order> orderss = repositorySto.findAll();
//
//        String mnp = "1";
//        for (Order element:orderss){
//            mnp = mnp + element.getProductid();
//        }
//      //  model.put("orderss", mnp);
//        return "main";
//
//
//    }


}


