package exemples

import java.sql.DriverManager

fun main(args: Array<String>) {
    val url = "jdbc:sqlite:Recordatorios.sqlite"
    val con = DriverManager.getConnection(url)
    val st = con.createStatement()

    val sentSQL = "INSERT INTO ALARMAS VALUES (2,'ALARMA2',20,20, 1,0,1, 0,0,0,1)"
//    val sentSQL = "CREATE TABLE ALARMAS("+
//            "id INTEGER PRIMARY KEY, "+
//            "nom TEXT, "+
//            "hora INTEGER, "+
//            "minuto INTEGER, "+
//            "lu INTEGER, "+
//            "ma INTEGER, "+
//            "mi INTEGER, "+
//            "ju INTEGER, "+
//            "vi INTEGER, "+
//            "sa INTEGER, "+
//            "do INTEGER "+
//            ")"



    st.executeUpdate(sentSQL)

    st.close();
    con.close()
}
