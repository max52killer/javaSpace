package com.dragonsoft.EasyTest.mongodb.service.impl;

import com.dragonsoft.EasyTest.mongodb.dao.PersonDao;
import com.dragonsoft.EasyTest.mongodb.po.Person;
import com.dragonsoft.EasyTest.mongodb.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;

    @Override
    public Person save(Person person) {
        person.set_id(getUUID());
        return personDao.save(person);
    }
    public String getUUID(){
        return String.valueOf(UUID.randomUUID()).replace("-","");
    }
}
