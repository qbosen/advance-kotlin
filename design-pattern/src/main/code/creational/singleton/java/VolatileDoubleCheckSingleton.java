package creational.singleton.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class VolatileDoubleCheckSingleton {
    // volatile 确保对象空间分配完成
    private static volatile VolatileDoubleCheckSingleton instance;
    private final AtomicLong idGenerator;

    private VolatileDoubleCheckSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        idGenerator = new AtomicLong();
    }

    public static VolatileDoubleCheckSingleton getInstance() {
        // 局部变量接收，减少volatile变量访问次数
        VolatileDoubleCheckSingleton temp = instance;
        if (null == temp) {
            synchronized (VolatileDoubleCheckSingleton.class) {
                temp = instance;
                if (null == temp) {
                    temp = new VolatileDoubleCheckSingleton();
                    instance = temp;
                }
            }
        }
        return instance;
    }

    public long generateId() {
        return idGenerator.incrementAndGet();
    }

}
