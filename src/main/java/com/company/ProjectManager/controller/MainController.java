package com.company.ProjectManager.controller;

import com.company.ProjectManager.model.ProjectInfo;
import com.company.ProjectManager.repos.ProjectInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("project")
public class MainController {
    @Autowired
    ProjectInfoRepo projects;

    public List<Map<String,String>> messages = new ArrayList<>(){{
        add(new HashMap<>(){{put("id","1");put("1st","123");}});
        add(new HashMap<>(){{put("id","2");put("2nd","234");}});
        add(new HashMap<>(){{put("id","3");put("3rd","345");}});
    }};


    @GetMapping()
    public List<Map<String,String>> list() {
        var l = projects.findAll();
        List<Map<String,String>> list = new ArrayList<>();

        for (var a : l) {
            list.add(new HashMap<>(){{put("id", String.valueOf(a.getId()));
                                      put("name", a.getName());}});
        }
        return list;
    }

    @PostMapping
    public ProjectInfo create(@RequestBody ProjectInfo projectInfo) {
        return projects.save(projectInfo);
    }

//    @PutMapping("{id}")
//    public ProjectInfo update(@PathVariable String id) {
//        return projects.save(projectInfo);
//    }


}
