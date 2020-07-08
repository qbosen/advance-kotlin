package creational.singleton.java;

/**
 * @author qiubaisen
 * @date 2020/6/22
 */
public enum EnumSingleton {
    INSTANCE;
    private long id = 0;

    public long getId() {
        return ++id;
    }
}
