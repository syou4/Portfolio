// package pfserver

import java.net.*

fun main(args: Array<String>) {
    // val xmlReader = XMLReader()
    val socket = DatagramSocket()
    socket.connect(Inet4Address.getByName("127.0.0.1"),12345)

    val data = ByteArray(128)

    System.out.println("Receiving ...")

    val pkt = DatagramPacket(data, data.size)
    socket.receive(pkt)

    val header = (0 shl 30) or (4 shl 27) or (3 shl 24) or (15 shl 16) or (4 shl 8)
    data[0] = (header shr 24).toByte()
    data[1] = (header shr 16).toByte()
    data[2] = (header shr 8).toByte()
    data[3] = (header shr 0).toByte()

    pkt.length =  4 * 12
    socket.send(pkt)

    System.out.println(data.copyOf(pkt.length).joinToString(
        separator = " ",
        transform = { b -> String.format("%02x", b) }
    ))
}
