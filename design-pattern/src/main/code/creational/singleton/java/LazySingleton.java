package creational.singleton.java;

import creational.singleton.IdGenerator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class LazySingleton implements IdGenerator {
    private static LazySingleton instance;
    private final AtomicLong idGenerator;

    private LazySingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.idGenerator = new AtomicLong();
    }

    // 未确保线程安全，可加 synchronized 锁
    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    @Override
    public long getId(){
        return idGenerator.incrementAndGet();
    }

}
