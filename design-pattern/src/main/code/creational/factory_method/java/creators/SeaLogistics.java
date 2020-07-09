package creational.factory_method.java.creators;

import creational.factory_method.java.products.Ship;
import creational.factory_method.java.products.Transport;

/**
 * 海运载具工厂
 * @author qiubaisen
 * @date 2020/7/9
 */
public class SeaLogistics extends LogisticsMethodFactory{
    @Override
    public Transport createTransport() {
        return new Ship();
    }
}
