package creational.singleton.java;

import creational.singleton.IdGenerator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class StaticInnerSingleton implements IdGenerator {
    private final AtomicLong idGenerator;

    private StaticInnerSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.idGenerator = new AtomicLong();
    }

    private static class Holder {
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }
    public static StaticInnerSingleton getInstance() {
        return Holder.instance;
    }

    @Override
    public long getId(){
        return idGenerator.incrementAndGet();
    }
}
