package com.lin.mapper;

import com.lin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    int addUser(User user);

    int deleteUserByUsername(String username);

    void deleteUserLike(String username);

    void deleteAll();

    int updateUser(User user);

    List<User> getUsers();

    User getUserByUsername(String username);

    List<User> getUserLike(String username);
}