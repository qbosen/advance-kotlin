package extension

import extension.ObjectScope.printString

/**
 * @author qiubaisen
 * @date 2020/5/24
 */
open class Host(val hostname: String) {
    fun printHostname() {
        print(hostname)
    }
}

class Connection(val host: Host, val port: Int) {
    fun printPort() {
        print(port)
    }

    fun Host.printConnectionString() {
        printHostname()   // 调用 Host.printHostname()
        print(":")
        printPort()   // 调用 Connection.printPort()
    }

    fun connect() {
        /*……*/
        host.printConnectionString()   // 调用扩展函数
    }
}

object ObjectScope : Host("scoped") {
    fun Host.printString() {
        printHostname()   // 调用 Host.printHostname()
    }
}

fun main() {
    val connection = Connection(Host("kotl.in"), 443)
    connection.connect()                            // 内部调用扩展
    connection.run { Host("new host").printConnectionString() }     // receiver变为分发者再进行调用
//    Host("kotl.in").printConnectionString(443)  // 错误，该扩展函数在 Connection 外不可用
    Host("kotl.in").printString()               // object 内的扩展方法可以import
}