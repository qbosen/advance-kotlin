package creational.singleton.java;

import java.util.concurrent.TimeUnit;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class DoubleCheckSingleton {
    private static DoubleCheckSingleton instance;
    private long id = 0;

    private DoubleCheckSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public long getId() {
        return ++id;
    }

}
