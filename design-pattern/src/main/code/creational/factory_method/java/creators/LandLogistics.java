package creational.factory_method.java.creators;

import creational.factory_method.java.products.Transport;
import creational.factory_method.java.products.Truck;

/**
 * 陆运载具工厂
 * @author qiubaisen
 * @date 2020/7/9
 */
public class LandLogistics extends LogisticsMethodFactory{
    @Override
    public Transport createTransport() {
        return new Truck();
    }
}
