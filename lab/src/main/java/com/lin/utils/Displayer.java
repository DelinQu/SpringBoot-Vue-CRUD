package com.lin.utils;

import com.lin.entity.Person;
import com.lin.entity.User;

import java.util.List;

public class Displayer {

    public static void displayUsers(List<User> users){
        //表头
        System.out.println("表user:");
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|   字段名 username              字段名 pass                                ");
        System.out.println("+-------------------------------------------------------------------------+");

        //表格内容
        users.forEach(user -> {
            String username = user.getUsername();
            String pass = user.getPass();
            System.out.printf("|    %-20s"+"\t\t"+"%s"+"\t\t\n",username,pass);
            System.out.println("+-------------------------------------------------------------------------+");
        });
    }

    public static void displayPersons(List<Person> persons){
        //表头
        System.out.println("表person:");
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("|   字段名 username   字段名 name         字段名 age       字段名 teleno  ");
        System.out.println("+-------------------------------------------------------------------------+");

        //表格内容
        persons.forEach(person -> {
            String username = person.getUsername();
            String name = person.getName();
            Integer age = person.getAge();
            String teleno = person.getTeleno();

            System.out.printf("|   %-10s"+"\t\t"+" %-10s \t\t",username,name);
            if(age!=null) System.out.printf("%-10d",age);
            else System.out.printf("%-10s","null");
            System.out.println("\t\t"+teleno);
            System.out.println("+-------------------------------------------------------------------------+");
        });
    }
}
