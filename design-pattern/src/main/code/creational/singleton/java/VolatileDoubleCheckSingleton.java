package creational.singleton.java;

import java.util.concurrent.TimeUnit;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public class VolatileDoubleCheckSingleton {
    // volatile 确保对象空间分配完成
    private static volatile VolatileDoubleCheckSingleton instance;
    private long id = 0;

    private VolatileDoubleCheckSingleton() {
        // 模拟一个复杂的初始化过程
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public long getId() {
        return ++id;
    }

}
