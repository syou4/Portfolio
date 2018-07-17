// package pfserver

import java.net.*

fun main(args: Array<String>) {
    //ポート12345で受信用ソケットを生成
    val rcvSocket = DatagramSocket(12345)
    //128バイトのバッファを用意
    val rcvData = ByteArray(128)

    System.out.println("Receiving ...")

    //受信パケットを格納するバッファを生成、大きさはデータ格納用バッファと同じ
    val rcvPkt = DatagramPacket(rcvData, rcvData.size)
    //受信待機
    rcvSocket.receive(rcvPkt)

    System.out.println("Received")

    //受信したパケットの内容をバッファにコピー
    rcvData.copyOf(rcvPkt.length)
    //受信したバイト情報を文字列に変換
    val rcvStr = String(rcvData,0,128)

    System.out.println("rcv Message > " + rcvStr)

    //受信ソケットクローズ
    rcvSocket.close()
    //2秒待機
    Thread.sleep(2000)

    //応答用ソケット生成
    val sendSocket = DatagramSocket()
    //応答文字列
    val sendStr : String = "ACK"
    //送信データのバイト配列
    val sendData = sendStr.toByteArray(Charsets.UTF_8)
    //送信パケット生成
    val sendPkt = DatagramPacket(sendData, sendData.size,Inet4Address.getByName("localhost"),12345)
    System.out.println("Responsing ...")
    sendSocket.send(sendPkt)
    System.out.println("Responsed ...")

    System.out.println("Fin.")
}
