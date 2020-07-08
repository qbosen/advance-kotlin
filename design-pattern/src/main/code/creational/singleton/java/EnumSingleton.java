package creational.singleton.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public enum EnumSingleton {
    INSTANCE;
    private final AtomicLong idGenerator;

    EnumSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.idGenerator = new AtomicLong();
    }

    public long generateId() {
        return idGenerator.incrementAndGet();
    }
}
