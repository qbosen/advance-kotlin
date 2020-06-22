package creational.singleton.java;

import creational.singleton.IdGenerator;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public enum EnumSingleton implements IdGenerator {
    INSTANCE;
    private final AtomicLong idGenerator = new AtomicLong();

    @Override
    public long getId(){
        return idGenerator.incrementAndGet();
    }
}
