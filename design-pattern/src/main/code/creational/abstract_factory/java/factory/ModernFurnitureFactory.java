package creational.abstract_factory.java.factory;

import creational.abstract_factory.java.products.Chair;
import creational.abstract_factory.java.products.ModernChair;
import creational.abstract_factory.java.products.ModernSofa;
import creational.abstract_factory.java.products.Sofa;

/**
 * @author qiubaisen
 * @date 2020/7/10
 */
public class ModernFurnitureFactory implements FurnitureFactory{
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }
}
