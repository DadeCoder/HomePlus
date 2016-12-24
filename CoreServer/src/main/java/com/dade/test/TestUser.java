package com.dade.test;

import com.dade.baisc.BasicModelObject;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Dade on 2016/12/24.
 */
@Document(collection = "user")
public class TestUser extends BasicModelObject {

    private String id;
    private String name;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "TestUser{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
