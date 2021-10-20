package exercisis


import java.io.*
import java.util.*
import exercisis.Ruta
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args: Array<String>){
    val f = ObjectInputStream(FileInputStream("Rutes.obj"))

    val doc = DocumentBuilderFactory.newInstance ().newDocumentBuilder().newDocument()
    val arrel = doc.createElement ("rutes")
    doc.appendChild(arrel)
    try {
        while (true) {
            val e = f.readObject() as Ruta
            val ruta = doc.createElement ("ruta")

            val nom = doc.createElement ("nom")
            nom.appendChild(doc.createTextNode(e.nom))
            ruta.appendChild(nom)

            val des = doc.createElement("desnivell")
            des.textContent = e.desnivell.toString()
            ruta.appendChild(des)

            val desAC = doc.createElement("desnivellAcumulat")
            desAC.textContent = e.desnivellAcumulat.toString()
            ruta.appendChild(desAC)

            val punts = doc.createElement("punts")
            for (i in 0 until e.size()){
                val punt = doc.createElement("punt");
                punt.setAttribute("num", (i + 1).toString())
                val nomPunt = doc.createElement("nom");
                nomPunt.textContent = e.getPuntNom(i).toString()
                val latitud = doc.createElement("latitud");
                latitud.textContent = e.getPuntLatitud(i).toString()
                val longitud = doc.createElement("longitud");
                longitud.textContent = e.getPuntLongitud(i).toString()

                punt.appendChild(nomPunt)
                punt.appendChild(latitud)
                punt.appendChild(longitud)
                punts.appendChild(punt)

            }

            ruta.appendChild(punts)
            arrel.appendChild(ruta)
        }

    } catch (eof: EOFException) {
        f.close()
    }
    val trans = TransformerFactory.newInstance().newTransformer()

    trans.transform(DOMSource(doc), StreamResult("Ruta.xml"))
}
