package creational.factory_method

import creational.factory_method.java.creators.LogisticsCompany
import creational.factory_method.java.creators.SimpleLogisticsFactory
import creational.factory_method.java.products.Ship
import creational.factory_method.java.products.Truck
import creational.factory_method.kotlin.MountainHeight
import creational.factory_method.kotlin.MountainsArea
import creational.factory_method.kotlin.SimpleLogisticsFactoryKt
import creational.factory_method.kotlin.TransportKt
import org.junit.jupiter.api.Test

/**
 * @author qiubaisen
 * @date 2020/7/9
 */

class TestFactories {
    @Test
    fun `测试简单工厂模式`() {
        val factory = SimpleLogisticsFactory()
        factory.createTransport("sea").also { assert(it is Ship) }.deliver()
        factory.createTransport("land").also { assert(it is Truck) }.deliver()
    }

    @Test
    fun `测试工厂方法模式`() {
        val client = LogisticsCompany()
        client.createTransport("sea").also { assert(it is Ship) }.deliver()
        client.createTransport("land").also { assert(it is Truck) }.deliver()
    }


    @Test
    fun `测试kotlin简单工厂`() {
        SimpleLogisticsFactoryKt("sea").also { assert(it is Ship) }.deliver()
        SimpleLogisticsFactoryKt("land").also { assert(it is Truck) }.deliver()
    }

    @Test
    fun `测试kotlin静态工厂`() {
        TransportKt("sea").also { assert(it is Ship) }.deliver()
        TransportKt("land").also { assert(it is Truck) }.deliver()
    }

    @Test
    fun `测试kotlin扩展工厂方法`() {
        MountainsArea.run {
            TransportKt.mountainsTransport(MountainHeight.High).deliver()
            TransportKt.mountainsTransport(MountainHeight.Middle).deliver()
            TransportKt.mountainsTransport(MountainHeight.Low).deliver()
            TransportKt("land").deliver()
        }
    }
}