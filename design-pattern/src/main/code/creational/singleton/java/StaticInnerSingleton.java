package creational.singleton.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class StaticInnerSingleton {
    private final AtomicLong idGenerator;

    private StaticInnerSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idGenerator = new AtomicLong();
    }

    private static class Holder {
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return Holder.instance;
    }

    public long generateId() {
        return idGenerator.incrementAndGet();
    }
}
