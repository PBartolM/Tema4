package exercisis


import java.io.FileInputStream
import java.io.ObjectInputStream
import java.sql.DriverManager

fun main(args: Array<String>) {
    val url = "jdbc:sqlite:Ruta.sqlite"
    val con = DriverManager.getConnection(url)
    val st = con.createStatement()
    val f = ObjectInputStream(FileInputStream("Rutes.obj"))
    var x = 1
    while (true){
        val e = f.readObject() as Ruta
        st.executeUpdate("INSERT INTO RUTES VALUES (x,e.nom,e.desnivell,e.desnivellAcumulat)")
        for (i in 0 until e.size()){
            st.executeUpdate("INSERT INTO PUNTS VALUES (x,(i + 1),e.getPuntNom(i),e.getPuntLatitud(i),e.getPuntLongitud(i))")
        }
        x++
    }


    f.close()
    st.close()
    con.close()
}