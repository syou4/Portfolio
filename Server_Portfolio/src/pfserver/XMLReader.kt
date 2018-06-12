package pfserver

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuildFactory

class XMLReader () {
    val xmlFile: File
    val xmlDoc: Document

    constructor(){
        xmlFile = File("data/setting.xml")
        xmlDoc = DocumentBulidFactory.newInstance().newDocumentBuilder().parse(xmlFile)

        xmlDoc.documentElement.normalize()

        println("Root Node:" + xmlDoc.documentElement.nodeName)

        val workList: NodeList = xmlDoc.getElementByTagName("Work")

        for (i in 0..workList.length - 1) {
            var workNode: Node = workList.item(i)

            if (workNode.getNodeType() == Node.ELEMENT_NODE){
                val elem = workNode as Element

                val mMap = mutableMapOf<String, String>()
                for (j in 0..elem.atributes.length - 1){
                    mMap.putIfAbsent(elem.atributes.item(j).nodeName, elem.atributes.item(j).nodeValue)
                }
                println("Current Work : ${workNode.nodeName} - $mMap")
 
                println("Password: ${elem.getElementsByTagName("Password").item(0).textContent}")
                println("ClientName: ${elem.getElementsByTagName("ClientName").item(0).textContent}")
                println("Title: ${elem.getElementsByTagName("Title").item(0).textContent}")
                // println("Price: ${elem.getElementsByTagName("price").item(0).textContent}")
                // println("publish_date: ${elem.getElementsByTagName("publish_date").item(0).textContent}")
                // println("description: ${elem.getElementsByTagName("description").item(0).textContent}")
            }
        }
    }
}