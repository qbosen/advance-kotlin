package creational.singleton.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class DoubleCheckSingleton {
    private static DoubleCheckSingleton instance;
    private final AtomicLong idGenerator;

    private DoubleCheckSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idGenerator = new AtomicLong();
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            // 类级别的锁
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }

    public long generateId() {
        return idGenerator.incrementAndGet();
    }

}
