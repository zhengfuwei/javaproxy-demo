package com.cdxt.javaproxydemo.proxy;

import com.cdxt.javaproxydemo.service.Impl.PersonServiceImpl;
import com.cdxt.javaproxydemo.service.PersonService;

/***
 * java代理之静态代理运用（需要准确知道那个代理类）
 */
public class StaticProxyTest implements PersonService {

    private PersonService personService;
    //构造器
    public StaticProxyTest(PersonService personService) {
        this.personService = personService;
    }

    /***
     * 测试静态代理
     * @param args
     */
    public static void main(String[] args) {
        //创建静态代理对象
        PersonServiceImpl p = new PersonServiceImpl();
        StaticProxyTest proxy = new StaticProxyTest(p);
        //调用静态代理对象的方法
        proxy.sayHello("welcome to static proxy java",20);
        System.out.println("**********华丽的分割线********");
        proxy.sayGoodBye(true,200.23);


    }

    @Override
    public void sayHello(String content, int age) {
        System.out.println("static proxy sayHello start");
        //真正调用的方法 在代理类的方法  间接调用代理方法
        personService.sayHello(content,age);
        System.out.println("static proxy sayHello end");
    }

    @Override
    public void sayGoodBye(boolean seeAgain, double time) {
        System.out.println("static proxy sayHello start");
        //真正调用的方法 在代理类的方法  间接调用代理方法
        personService.sayGoodBye(seeAgain,time);
        System.out.println("static proxy sayHello end");
    }
}
