package creational.singleton.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class LazySingleton {
    private static LazySingleton instance;
    private final AtomicLong idGenerator;

    private LazySingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idGenerator = new AtomicLong();
    }

    // 未确保线程安全，可加 synchronized 锁
    public synchronized static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public long generateId() {
        return idGenerator.incrementAndGet();
    }

}
