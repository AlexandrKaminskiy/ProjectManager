package com.company.ProjectManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MainController {
    public List<Map<String,String>> messages = new ArrayList<>(){{
        add(new HashMap<>(){{put("id","1");put("1st","123");}});
        add(new HashMap<>(){{put("id","2");put("2nd","234");}});
        add(new HashMap<>(){{put("id","3");put("3rd","345");}});
    }};

    @GetMapping
    public List<Map<String,String>> list() {
        return messages;
    }
}
