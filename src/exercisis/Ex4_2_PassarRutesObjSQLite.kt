package exercisis


import java.io.EOFException
import java.io.FileInputStream
import java.io.ObjectInputStream
import java.sql.DriverManager

fun main(args: Array<String>) {
    val url = "jdbc:sqlite:Rutes.sqlite"
    val con = DriverManager.getConnection(url)
    val st = con.createStatement()
    val f = ObjectInputStream(FileInputStream("Rutes.obj"))
    var x = 1
    var palabras=""

        try {
            while (true){
            val e = f.readObject() as Ruta
            palabras = ("INSERT INTO RUTES VALUES ($x,'${e.nom}',${e.desnivell},${e.desnivellAcumulat})")
                st.executeUpdate(palabras)

            for (i in 0 until e.size() ) {
                palabras = ("INSERT INTO PUNTS VALUES ($x,${i + 1},'${e.getPuntNom(i)}',${e.getPuntLatitud(i)},${e.getPuntLongitud(i)})")
                st.executeUpdate(palabras)
            }

            x++
            }
        } catch (eof: EOFException) {
            f.close()
        }



    f.close()
    st.close()
    con.close()
}