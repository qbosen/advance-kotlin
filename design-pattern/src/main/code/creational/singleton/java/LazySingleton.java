package creational.singleton.java;

import java.util.concurrent.TimeUnit;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class LazySingleton {
    private static LazySingleton instance;
    private long id;

    private LazySingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 未确保线程安全，可加 synchronized 锁
    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public long getId() {
        return ++id;
    }

}
