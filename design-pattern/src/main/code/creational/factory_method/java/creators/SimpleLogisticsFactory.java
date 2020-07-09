package creational.factory_method.java.creators;

import creational.factory_method.java.products.Ship;
import creational.factory_method.java.products.Transport;
import creational.factory_method.java.products.Truck;

/**
 * 简单工厂模式
 *
 * @author qiubaisen
 * @date 2020/7/9
 */
public class SimpleLogisticsFactory {

    public Transport createTransport(String type) {
        if ("land".equals(type)) {
            return new Truck();
        } else if ("sea".equals(type)) {
            return new Ship();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
