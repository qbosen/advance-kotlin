package creational.abstract_factory.java;

import creational.abstract_factory.java.factory.ArtDecoFurnitureFactory;
import creational.abstract_factory.java.factory.FurnitureFactory;
import creational.abstract_factory.java.factory.ModernFurnitureFactory;

/**
 * 入口，
 * 可以根据配置获取工厂
 * 也可以根据
 * @author qiubaisen
 * @date 2020/7/10
 */
public class Shop {

    private final Style shopStyle;

    public enum Style {
        modern, artDeco
    }
    public Shop(Style shopStyle) {
        this.shopStyle = shopStyle;
    }

    // 店铺订购一套家具，感受下
    public void buySeriesAndFeels(){
        FurnitureFactory factory;
        if(shopStyle == Style.modern){
            factory = new ModernFurnitureFactory();
        }else if(shopStyle == Style.artDeco){
            factory = new ArtDecoFurnitureFactory();
        }else {
            throw new IllegalArgumentException();
        }
        factory.createChair().sit();
        factory.createSofa().sit();
    }
}
