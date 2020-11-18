package com.lin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.entity.Person;
import com.lin.entity.User;
import com.lin.mapper.PersonMapper;
import com.lin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonMapper personMapper;
    @Autowired
    UserMapper userMapper;

    public int addPerson(Person person){
        if(userMapper.getUserByUsername(person.getUsername())==null) {      //信息不存在于user
            userMapper.addUser(new User(person.getUsername(),"888888"));
        }
        return personMapper.addPerson(person);
    }

    public int deletePersonByUsername(String username){
        return personMapper.deletePersonByUsername(username);
    }

    public void deletePersonLike(String username){
        personMapper.deletePersonLike(username);
    }

    public void deleteAll(){
        personMapper.deleteAll();
    }

    public int updatePerson(Person person){
        return personMapper.updatePerson(person);
    }

    public List<Person> getPersons(){
        return personMapper.getPersons();
    }

    public Person getPersonByUsername(String username){
        return personMapper.getPersonByUsername(username);
    }

    public PersonMapper getPersonMapper() {
        return personMapper;
    }

    //分页查询
    public PageInfo<Person> getPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Person> persons = personMapper.getPersons();
        PageInfo<Person> PageInfo = new PageInfo<Person>(persons);
        return PageInfo;
    }

    public List<Person> getPersonLike(String username){
        return personMapper.getPersonLike("%"+username+"%");
    }
}
