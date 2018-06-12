package pfserver

import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.File
import javax.xml.parsers.DocumentBuildFactory

class XMLReader () {
    val xmlFile : File
    val xmlDoc: Document


    constructor(){
        xmlFile = File("data/setting.xml")
        xmlDoc = DocumentBulidFactory.newInstance().newDocumentBuilder().parse
        xmlDoc.documentElement.normalize()
        println("Root Node:" + xmlDoc.documentElement.nodeName)

    }
}