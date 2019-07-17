package com.cdxt.javaproxydemo.proxy;

import com.cdxt.javaproxydemo.service.Impl.PersonServiceImpl;
import com.cdxt.javaproxydemo.service.PersonService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/***
 * 动态代理实现-
 */
public class DynamicProxyTest implements InvocationHandler {

    private Object object;

    public DynamicProxyTest(Object object) {
        this.object = object;
    }

    /***
     * 利用反射获取动态代理对象以及方法
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
     * 动态代理测试
     * @param args
     */
    public static void main(String[] args) {
        //创建需要被代理的类
        PersonServiceImpl p = new PersonServiceImpl();
        //获得被加载的代理对象 的类加载器
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        //获取被代理类实现的接口
        Class<?>[] interfaces = p.getClass().getInterfaces();
        //创建被代理的委托类 想被代理 直接委托它 调用invoke即可
        DynamicProxyTest test = new DynamicProxyTest(p);
        //生成代理类
        PersonService proxy = (PersonService) Proxy.newProxyInstance(loader, interfaces, test);
        //通过代理类 调用被代理类的方法
        proxy.sayHello("test",20);
        proxy.sayGoodBye(true,20.5);
        System.out.println("end");
    }
}
