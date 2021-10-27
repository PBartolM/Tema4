package exercisis

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

class GestionarRutesBD() {
    var con:Connection
    val url = "jdbc:sqlite:Rutes.sqlite"
    var st :Statement
    init {
        this.con= DriverManager.getConnection(url)
        this.st = con.createStatement()

    }
    fun close(){
        con.close()
    }
    fun inserir(r: Ruta){
        //
        var x = st.executeQuery("SELECT MAX(num_r) FROM RUTES").getInt(1)+1

        println ("INSERT INTO RUTES VALUES ($x,'${r.nom}',${r.desnivell},${r.desnivellAcumulat})")
//        st.executeUpdate(palabras)

        for (i in 0 until r.size() ) {
            println ("INSERT INTO PUNTS VALUES ($x,${i + 1},'${r.getPuntNom(i)}',${r.getPuntLatitud(i)},${r.getPuntLongitud(i)})")
//            st.executeUpdate(palabras)
        }
    }
}