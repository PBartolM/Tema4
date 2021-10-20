package exercisis


import java.io.*
import java.util.*


fun main(args: Array<String>){
    val f = ObjectInputStream(FileInputStream("Rutes.obj"))
    try {
            while (true) {
                val e = f.readObject() as Ruta
                println(e.nom)
                e.mostrarRuta()

            }

    } catch (eof: EOFException) {
        f.close()
    }
}
