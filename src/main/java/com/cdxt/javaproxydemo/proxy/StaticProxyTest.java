package com.cdxt.javaproxydemo.proxy;

import com.cdxt.javaproxydemo.service.Impl.PersonServiceImpl;
import com.cdxt.javaproxydemo.service.PersonService;

/***
 * Static proxy application of agents (need to know exactly which proxy class)
 */
public class StaticProxyTest implements PersonService {

    private PersonService personService;
    //constructor
    public StaticProxyTest(PersonService personService) {
        this.personService = personService;
    }

    /***
     * Test the static agent
     * @param args
     */
    public static void main(String[] args) {
        //Create a static proxy object
        PersonServiceImpl p = new PersonServiceImpl();
        StaticProxyTest proxy = new StaticProxyTest(p);
        //Calls the method of the static proxy object
        proxy.sayHello("welcome to static proxy java",20);
        System.out.println("**********华丽的分割线********");
        proxy.sayGoodBye(true,200.23);


    }

    @Override
    public void sayHello(String content, int age) {
        System.out.println("static proxy sayHello start");
        //Invoke the proxy method indirectly
        personService.sayHello(content,age);
        System.out.println("static proxy sayHello end");
    }

    @Override
    public void sayGoodBye(boolean seeAgain, double time) {
        System.out.println("static proxy sayHello start");
        //Invoke the proxy method indirectly
        personService.sayGoodBye(seeAgain,time);
        System.out.println("static proxy sayHello end");
    }
}
