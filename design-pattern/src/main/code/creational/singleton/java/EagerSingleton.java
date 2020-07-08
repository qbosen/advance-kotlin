package creational.singleton.java;

import java.util.concurrent.TimeUnit;

/**
 * 饿汉式
 *
 * @author qiubaisen
 * @date 2020/6/22
 */
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private long id;

    private EagerSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    public long getId() {
        return ++id;
    }
}
