package creational.abstract_factory.kotlin

import creational.abstract_factory.java.factory.ArtDecoFurnitureFactory
import creational.abstract_factory.java.factory.FurnitureFactory
import creational.abstract_factory.java.factory.ModernFurnitureFactory

/**
 * @author qiubaisen
 * @date 2020/7/10
 */

/**
 * 1. 抽象工厂可以是[密封类],模式匹配sum类型
 * 2. [伴生对象]、[invoke运算符] 简化具体工厂创建
 * 3. [inline] [reified]，通过具体范型类型，创建对应的工厂
 */
interface FurnitureFactoryKt : FurnitureFactory {
    companion object {
        inline operator fun <reified T : FurnitureFactory> invoke(): FurnitureFactory {
            return when (T::class) {
                ArtDecoFurnitureFactory::class -> ArtDecoFurnitureFactory()
                ModernFurnitureFactory::class -> ModernFurnitureFactory()
                else -> throw UnsupportedOperationException()
            }
        }
    }
}

