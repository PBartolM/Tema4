package exercisis

import java.io.*
import java.util.*


fun main(args: Array<String>){
    val f = DataInputStream(FileInputStream("Rutes.dat"))
    val g = ObjectOutputStream(FileOutputStream("Rutes.obj"))

    while (f.available() > 0) {
        var lista = mutableListOf<PuntGeo>()
        var nameRuta =f.readUTF()
        var desnivellRuta=f.readInt()
        var desnivellAcoRuta=f.readInt()
        val punts = f.readInt()
        for (i in 0..punts-1){
            var name = f.readUTF()
            var cooord= Coordenades(f.readDouble(),f.readDouble())
            var punticoGeo=PuntGeo(name,cooord)
            lista.add(punticoGeo)
        }
        var ruta =Ruta(nameRuta,desnivellRuta,desnivellAcoRuta,lista)
        ruta.mostrarRuta()
        g.writeObject(ruta)
    }
    f.close()
    g.close()
}
