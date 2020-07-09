package creational.factory_method.kotlin

import creational.factory_method.java.products.Ship
import creational.factory_method.java.products.Transport
import creational.factory_method.java.products.Truck

/**
 * @author qiubaisen
 * @date 2020/7/9
 */

/**[简单工厂],使用 [单例] 和 [重载invoke] 简化 */
object SimpleLogisticsFactoryKt {
    operator fun invoke(type: String): Transport {
        return when (type) {
            "sea" -> Ship()
            "land" -> Truck()
            else -> throw UnsupportedOperationException()
        }
    }
}

/** [静态工厂], 直接把工厂放进[产品]的伴生对象*/
interface TransportKt : Transport {
    companion object {
        operator fun invoke(type: String) = when (type) {
            "sea" -> Ship()
            "land" -> Truck()
            else -> throw UnsupportedOperationException()
        }
    }
}

/** 扩展工厂 通过[扩展方法] 给工厂添加分离的创建逻辑*/
enum class MountainHeight { High, Middle, Low }
object MountainsArea {
    fun TransportKt.Companion.mountainsTransport(height: MountainHeight) = when (height) {
        MountainHeight.High -> Transport { println("直升机：高山专用") }
        MountainHeight.Middle -> Transport { println("大型无人机：中海拔使用") }
        MountainHeight.Low -> Transport { println("小型无人机：低矮山地使用") }
    }
}

