package com.example.demo.controller;


import com.example.demo.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {
    /*@ResponseBody
    @RequestMapping(method = GET, produces = "application/json")
    public String demo() {
        return "{\"hello\":\"world\"}";
    }*/
    private  List<String > dataList = new ArrayList<String>(Arrays.asList("a","b","c"));
    @ResponseBody
    @RequestMapping(method = GET, produces = "application/json" , value = "/demo")
    public Customer demo() {
        Customer c = new Customer();
        c.setId((long) 1);
        c.setFirstName("v");
        c.setLastName("c");

        return c;

    }
    @ResponseBody
    @RequestMapping(value = "/data", method = GET , produces = "application/json")
    public Customer getData(@RequestParam(value="id",defaultValue="0") Integer id , @RequestParam(value="firstName" , defaultValue = "a") String firstName ,@RequestParam(value="lastName" , defaultValue = "b") String lastName)
    {
        return new Customer((long)id , firstName , lastName);
    }
}
