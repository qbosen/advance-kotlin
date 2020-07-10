package creational.abstract_factory.java.factory;

import creational.abstract_factory.java.products.Chair;
import creational.abstract_factory.java.products.Sofa;

/**
 * @author qiubaisen
 * @date 2020/7/10
 */
public interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
}
