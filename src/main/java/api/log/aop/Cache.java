package api.log.aop;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: chenenwei
 * @date: 2025/7/29
 */
public class Cache {

    /**
     * 方法缓存
     */
    public volatile static Set<Method> printMethods;

    /**
     * 接口方法缓存
     */
    public volatile static Map<String, Method> methodMap;

    static {
        printMethods = Collections.synchronizedSet(new HashSet<>(1));
        methodMap = new ConcurrentHashMap<>();
    }

    public static void put(Method method){
        Cache.printMethods.add(method);
    }
    public static void put (Collection<Method> methods){
        Cache.printMethods.addAll(methods);
    }

    public static void remove (Method method){
        Cache.printMethods.remove(method);
    }
    public static boolean contains (Method method){

        return Cache.printMethods.contains(method);
    }

    public static void clear(){
        Cache.printMethods.clear();
    }

}
