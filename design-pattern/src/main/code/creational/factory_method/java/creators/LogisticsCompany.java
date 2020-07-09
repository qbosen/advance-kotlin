package creational.factory_method.java.creators;

import creational.factory_method.java.products.Transport;

/**
 * 工厂方法的调用方
 * @author qiubaisen
 * @date 2020/7/9
 */
public class LogisticsCompany {

    public Transport createTransport(String type) {
        LogisticsMethodFactory factory = null;
        if ("land".equals(type)) {
            factory = new LandLogistics();
        } else if ("sea".equals(type)) {
            factory = new SeaLogistics();
        } else {
            throw new UnsupportedOperationException();
        }
        return factory.createTransport();
    }
}
