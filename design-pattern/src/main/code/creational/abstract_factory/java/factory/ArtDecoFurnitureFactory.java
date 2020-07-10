package creational.abstract_factory.java.factory;

import creational.abstract_factory.java.products.ArtDecoChair;
import creational.abstract_factory.java.products.ArtDecoSofa;
import creational.abstract_factory.java.products.Chair;
import creational.abstract_factory.java.products.Sofa;

/**
 * @author qiubaisen
 * @date 2020/7/10
 */
public class ArtDecoFurnitureFactory  implements FurnitureFactory{
    @Override
    public Chair createChair() {
        return new ArtDecoChair();
    }

    @Override
    public Sofa createSofa() {
        return new ArtDecoSofa();
    }
}
