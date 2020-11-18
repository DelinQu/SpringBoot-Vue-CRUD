package com.lin.controller;

import com.github.pagehelper.PageInfo;
import com.lin.entity.Person;
import com.lin.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/getPersons")
    public List<Person> getPersons(){
        return personService.getPersons();
    }

    @GetMapping("/getPersonByUsername")
    public Person getPersonByUsername(@RequestParam("username") String username){
        return personService.getPersonByUsername(username);
    }

    @PutMapping("/updatePerson")
    public boolean updatePerson(@RequestBody Person person){
        int res=personService.updatePerson(person);
        if(res==0)
            return false;
        else
            return true;
    }

    @DeleteMapping("/deletePersonByUsername")
    public boolean deletePersonByUsername(@RequestParam("username") String username){
        int res=personService.deletePersonByUsername(username);
        if(res==0)
            return false;
        else
            return true;
    }

    @PostMapping("/addPerson")
    public boolean addPerson(@RequestBody Person person){
        int res=personService.addPerson(person);
        if(res==0)
            return false;
        else
            return true;
    }

    @GetMapping("/getPageInfo")
    public PageInfo<Person> getPageInfo(int pageNum, int pageSize) {
        return personService.getPageInfo(pageNum,pageSize);
    }

    @GetMapping("/getPersonLike")
    public List<Person> getPersonLike(@RequestParam("username") String username) {
        return personService.getPersonLike(username);
    }

}
