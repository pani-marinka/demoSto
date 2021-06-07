package org.DemoSto.controller;

import org.DemoSto.exeption.NotFoundExeption;
import org.DemoSto.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("order")
public class MainController {

    @Autowired
    private OrderService orderService;


    public List<Map<String, String>> orders = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("orderName", "firstOrder");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("orderName", "twoOrder");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("orderName", "threeOrder");
        }});
    }};

    private Map<String, String> getIdOrder(@PathVariable String id) {
        return orders.stream()
                .filter(order -> order.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundExeption::new);
 }


    public ArrayList<String> order2= new ArrayList<>();

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getIdOrder(id);
    }


//    @GetMapping
//    public  String main( Model model) {
//        //Iterable<Order> orderss = repositorySto.findAll();
//        orderService.createOrder("12", 2);
//        orderService.createOrder("122", 3);
//        orderService.createOrder("1222", 4);
//
//
//        ArrayList<Order> orderss = new ArrayList<>();
//        orderss = (ArrayList<Order>) orderService.getAllOrder();
//        model.addAttribute("or", orderss);
//        String test1 = "qwe";
//        model.addAttribute("mnp", test1);
//        return "modal";
//    }

    @RequestMapping
    public String order( String name) {
        return "order";
    }

/*
 @RequestMapping
    public Order order(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Order(counter.incrementAndGet(),
                            String.format(template, name));
    }
     @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", required=false, defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
 */

        /*
        List<Person> list = new ArrayList<Person>();

    list.add(tom);
    list.add(jerry);
    list.add(donald);

    model.addAttribute("people", list);

    return "loop-list-example";
         */

        /*
        ArrayList<String> subjects = new ArrayList<String>();

    for(Subject sub:user.getSubject())
    {
        subjects.add(sub.getSubjectName());
    }
    model.addAttribute("subjects", subjects);

         */

//        Iterable<Order> orderss = orderService.getAllOrder();
//        model.put("orderss", orderss);






}
