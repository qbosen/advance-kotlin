package creational.factory_method.java.creators;

import creational.factory_method.java.products.Transport;

/**
 * 工厂方法类
 * @author qiubaisen
 * @date 2020/7/9
 */
public abstract class LogisticsMethodFactory {
    public abstract Transport createTransport();
}
