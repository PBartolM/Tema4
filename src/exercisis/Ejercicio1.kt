package ejercicios

import java.io.DataInputStream
import java.io.FileInputStream
import java.io.RandomAccessFile
import java.util.*


    fun main(args: Array<String>){
        val f = DataInputStream(FileInputStream("Rutes.dat"))
        while (f.available() > 0) {
            System.out.println("Ruta: " + f.readUTF())
            System.out.println("Desnivell: " + f.readInt())
            System.out.println("Desnivell acumulat: " + f.readInt())
            val punts = f.readInt()
            System.out.println("Te ${punts} punts")
            for (i in 1..punts){
                println("Punt ${i}: ${f.readUTF()} (${f.readDouble()} ${f.readDouble()})")
            }
            println("")
        }
        f.close()
}
