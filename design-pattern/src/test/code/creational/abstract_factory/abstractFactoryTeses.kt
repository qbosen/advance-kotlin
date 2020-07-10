package creational.abstract_factory

import creational.abstract_factory.java.Shop
import creational.abstract_factory.java.factory.ArtDecoFurnitureFactory
import creational.abstract_factory.java.factory.ModernFurnitureFactory
import creational.abstract_factory.kotlin.FurnitureFactoryKt
import org.junit.jupiter.api.Test

/**
 * @author qiubaisen
 * @date 2020/7/10
 */

class TestAbstractFactory {
    @Test
    fun `体验一套家具`() {
        println("逛：现代风格家具店")
        Shop(Shop.Style.modern).buySeriesAndFeels()
        println("逛：艺术风格家具店")
        Shop(Shop.Style.artDeco).buySeriesAndFeels()
    }

    @Test
    fun `测试kotlin抽象工厂`() {
        val modernFactory = FurnitureFactoryKt<ModernFurnitureFactory>()
        modernFactory.createChair().sit()
        modernFactory.createSofa().sit()

        val artDecoFactory = FurnitureFactoryKt<ArtDecoFurnitureFactory>()
        artDecoFactory.createChair().sit()
        artDecoFactory.createSofa().sit()
    }
}