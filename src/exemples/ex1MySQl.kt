package exemples

import java.sql.DriverManager

fun main(args: Array<String>) {
    val url = "jdbc:mysql://89.36.214.106:3306/geo_ad"
    val usuari = "geo_ad"
    val password = "geo_ad"

    val con = DriverManager.getConnection(url, usuari, password)
    System.out.println("Connexió completada")
    con.close()
}