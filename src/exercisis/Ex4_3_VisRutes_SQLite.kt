package exercisis



import javax.swing.JFrame
import java.awt.EventQueue
import java.awt.BorderLayout
import javax.swing.JPanel
import java.awt.FlowLayout
import java.sql.DriverManager
import javax.swing.JComboBox
import javax.swing.JButton
import javax.swing.JTextArea
import javax.swing.JLabel

class Finestra : JFrame() {

    init {
        // Sentències per a fer la connexió
        val url = "jdbc:sqlite:Rutes.sqlite"
        val con = DriverManager.getConnection(url)
        val st = con.createStatement()
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("JDBC: Visualitzar Rutes")
        setSize(450, 450)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        val panell2 = JPanel(BorderLayout())
        add(panell1, BorderLayout.NORTH)
        add(panell2, BorderLayout.CENTER)

        val llistaRutes = arrayListOf<String>()
        // Sentències per a omplir l'ArrayList amb el nom de les rutes

        val rs = st.executeQuery("select * from RUTES")
        while (rs.next()) {
            llistaRutes.add(rs.getString(2))
        }

        val combo = JComboBox<String>(llistaRutes.toTypedArray())
        panell1.add(combo)
        val eixir = JButton("Eixir")
        panell1.add(eixir)
        val area = JTextArea()
        panell2.add(JLabel("Llista de punts de la ruta:"),BorderLayout.NORTH)
        panell2.add(area,BorderLayout.CENTER)


        combo.addActionListener() {
            // Sentèncis quan s'ha seleccionat un element del JComboBox
            // Han de consistir en omplir el JTextArea
            val rutaEscogida= st.executeQuery("select * from RUTES  limit ${combo.selectedIndex},1 ")
            val puntosEscogidos=st.executeQuery("select * from Punts where num_r is ${rutaEscogida.getInt(1)}")
            var palabras = ""
            while (puntosEscogidos.next()) {
                palabras +=("${ rs.getString(3) } (${rs.getFloat(4)},${rs.getFloat(5)})\n ")
            }
            area.text = palabras
        }

        eixir.addActionListener(){
            // Sentències per a tancar la connexió i eixir
            st.close()
            con.close()
            System.exit(0)
        }
    }
}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        Finestra().isVisible = true
    }
}

