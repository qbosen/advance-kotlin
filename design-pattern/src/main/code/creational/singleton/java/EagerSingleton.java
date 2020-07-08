package creational.singleton.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式
 *
 * @author qiubaisen
 * @date 2020/6/22
 */
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private final AtomicLong idGenerator;

    private EagerSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idGenerator = new AtomicLong();
    }

    public static EagerSingleton getInstance() {
        return instance;
    }

    public long generateId() {
        return idGenerator.incrementAndGet();
    }
}
