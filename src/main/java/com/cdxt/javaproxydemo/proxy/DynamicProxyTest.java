package com.cdxt.javaproxydemo.proxy;

import com.cdxt.javaproxydemo.service.Impl.PersonServiceImpl;
import com.cdxt.javaproxydemo.service.PersonService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 * Dynamic proxy implementation
 */
public class DynamicProxyTest implements InvocationHandler {

    private Object object;

    public DynamicProxyTest(Object object) {
        this.object = object;
    }

    /***
     * Use reflection to obtain dynamic proxy objects and methods
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("DynamicProxy invoke start ");
        System.out.println("proxy:"+proxy.getClass().getName());
        System.out.println("method:"+method.getName());
        for (Object o:args){
            System.out.println("args:"+o.toString());
        }
        method.invoke(object,args);
        System.out.println("DynamicProxy invoke end ");
        return null;
    }

    /**
     * Dynamic agent test
     * @param args
     */
    public static void main(String[] args) {
        //Create the classes that need to be represented
        PersonServiceImpl p = new PersonServiceImpl();
        //Gets the class loader of the loaded proxy object
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        //Gets the interface implemented by the proxy class
        Class<?>[] interfaces = p.getClass().getInterfaces();
        //Create a proxy class that wants to be directly delegated by the proxy to call invoke
        DynamicProxyTest test = new DynamicProxyTest(p);
        //Generate proxy class
        PersonService proxy = (PersonService) Proxy.newProxyInstance(loader, interfaces, test);
        //The method of the proxy class is called through the proxy class
        proxy.sayHello("test",20);
        proxy.sayGoodBye(true,20.5);
        System.out.println("end");
    }
}
