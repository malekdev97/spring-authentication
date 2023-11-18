package com.malek.app.rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @GetMapping(value = "/index")
    public String index() {
        return "Index";
    }
}
