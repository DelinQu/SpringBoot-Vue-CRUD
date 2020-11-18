package com.lin.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.entity.User;
import com.lin.mapper.PersonMapper;
import com.lin.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.session.NonUniqueSessionRepositoryException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PersonMapper personMapper;

    public int addUser(User user){
        return userMapper.addUser(user);
    }

    public int deleteUserByUsername(String username){
        if(personMapper.getPersonByUsername(username)!=null) personMapper.deletePersonByUsername(username);
        return userMapper.deleteUserByUsername(username);
    }

    public void deleteUserLike(String username){
        userMapper.deleteUserLike(username);
    }

    public void deleteAll(){
        userMapper.deleteAll();
    }

    public int updateUser(User user){
        return userMapper.updateUser(user);
    }

    public List<User> getUsers(){
        return userMapper.getUsers();
    }

    public User getUserByUsername(String username){
        return userMapper.getUserByUsername(username);
    }

    //分页查询
    public PageInfo<User> getPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.getUsers();
        PageInfo<User> PageInfo = new PageInfo<User>(users);
        return PageInfo;
    }

    public List<User> getUserLike(String username){
        return userMapper.getUserLike("%"+username+"%");
    }
}
