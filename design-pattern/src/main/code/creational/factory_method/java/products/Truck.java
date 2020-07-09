package creational.factory_method.java.products;

/**
 * 卡车运输
 * @author qiubaisen
 * @date 2020/7/9
 */
public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("陆运中...");
    }
}
