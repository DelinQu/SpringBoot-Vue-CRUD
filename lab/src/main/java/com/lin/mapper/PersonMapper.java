package com.lin.mapper;

import com.lin.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PersonMapper {
    int addPerson(Person person);

    int deletePersonByUsername(String username);

    void deletePersonLike(String username);

    void deleteAll();

    int updatePerson(Person person);

    List<Person> getPersons();

    Person getPersonByUsername(String username);

    List<Person> getPersonLike(String username);
}
