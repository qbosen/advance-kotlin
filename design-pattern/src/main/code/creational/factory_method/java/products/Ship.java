package creational.factory_method.java.products;

/**
 * 轮船
 * @author qiubaisen
 * @date 2020/7/9
 */
public class Ship implements Transport{
    @Override
    public void deliver() {
        System.out.println("海运中...");
    }
}
