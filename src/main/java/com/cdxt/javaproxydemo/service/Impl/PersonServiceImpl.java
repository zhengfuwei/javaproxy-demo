package com.cdxt.javaproxydemo.service.Impl;

import com.cdxt.javaproxydemo.service.PersonService;

public class PersonServiceImpl implements PersonService {
    @Override
    public void sayHello(String content, int age) {
        System.out.println("student say hello " + content + " "+ age);
    }

    @Override
    public void sayGoodBye(boolean seeAgain, double time) {
        System.out.println("student sayGoodBye " + time + " "+ seeAgain);
    }
}
