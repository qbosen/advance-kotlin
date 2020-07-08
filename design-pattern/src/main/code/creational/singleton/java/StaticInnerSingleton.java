package creational.singleton.java;

import java.util.concurrent.TimeUnit;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class StaticInnerSingleton {
    private long id;

    private StaticInnerSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Holder {
        private static final StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return Holder.instance;
    }

    public long getId() {
        return ++id;
    }
}
