package exercisis



import java.awt.EventQueue
import java.awt.GridLayout
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.JTable
import javax.swing.JScrollPane
import exercisis.GestionarRutesBD.*
import java.sql.DriverManager

class FinestraComplet : JFrame() {
    val gRutes = GestionarRutesBD()
    var llista = arrayListOf<Ruta>()
    var numActual = 0

    val qNom = JTextField(15)
    val qDesn = JTextField(5)
    val qDesnAcum = JTextField(5)
    val punts = JTable(1,3)
    val primer = JButton(" << ")
    val anterior = JButton(" < ")
    val seguent = JButton(" > ")
    val ultim = JButton(" >> ")
    val tancar = JButton("Tancar")
    init {

        val url = "jdbc:sqlite:Rutes.sqlite"
        val con = DriverManager.getConnection(url)
        val st = con.createStatement()

        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("JDBC: Visualitzar Rutes Complet")
        setLayout(GridLayout(0,1))

        val p_prin = JPanel()
        p_prin.setLayout(BoxLayout(p_prin, BoxLayout.Y_AXIS))
        val panell1 = JPanel(GridLayout(0,2))
        panell1.add(JLabel("Ruta:"))
        qNom.setEditable(false)
        panell1.add(qNom)
        panell1.add(JLabel("Desnivell:"))
        qDesn.setEditable(false)
        panell1.add(qDesn)
        panell1.add(JLabel("Desnivell acumulat:"))
        qDesnAcum.setEditable(false)
        panell1.add(qDesnAcum)
        panell1.add(JLabel("Punts:"))

        val panell2 = JPanel(GridLayout(0,1))
        punts.setEnabled(false)
        val scroll = JScrollPane(punts)
        panell2.add(scroll, null)

        val panell5 = JPanel(FlowLayout())
        panell5.add(primer)
        panell5.add(anterior)
        panell5.add(seguent)
        panell5.add(ultim)

        val panell6 = JPanel(FlowLayout())
        panell6.add(tancar)

        add(p_prin)
        p_prin.add(panell1)
        p_prin.add(panell2)
        p_prin.add(panell5)
        p_prin.add(panell6)
        pack()

        primer.addActionListener{
            // instruccions per a situar-se en la primera ruta, i visualitzar-la
            val gRutes = GestionarRutesBD()
            val primeraRuta:Ruta= gRutes.buscar(1)
            qNom.text=primeraRuta.nom
            qDesn.text=primeraRuta.desnivell.toString()
            qDesnAcum.text=primeraRuta.desnivellAcumulat.toString()
            plenarTaula(primeraRuta.llistaDePunts)

        }
        anterior.addActionListener{
            // instruccions per a situar-se en la ruta anterior, i visualitzar-la
            //Pujada a Penyagolosa
            //La Magdalena
            val getnom = "select num_r FROM RUTES where nom_r=\'${qNom.text.toString()} \'"
            val anteriorq = st.executeQuery(getnom)
            val anteriorInt=(anteriorq.getInt(1).toInt())-1
            val gRutes = GestionarRutesBD()
            val primeraRuta:Ruta= gRutes.buscar(anteriorInt)
            qNom.text=primeraRuta.nom
            qDesn.text=primeraRuta.desnivell.toString()
            qDesnAcum.text=primeraRuta.desnivellAcumulat.toString()
            plenarTaula(primeraRuta.llistaDePunts)
        }
        seguent.addActionListener{
            // instruccions per a situar-se en la ruta següent, i visualitzar-la
            val getnom = "select num_r FROM RUTES where nom_r=\'${qNom.text.toString()} \'"
            val seguentq = st.executeQuery(getnom)
            val seguentInt=(seguentq.getInt(1).toInt())+1
            val gRutes = GestionarRutesBD()
            val primeraRuta:Ruta= gRutes.buscar(seguentInt)
            qNom.text=primeraRuta.nom
            qDesn.text=primeraRuta.desnivell.toString()
            qDesnAcum.text=primeraRuta.desnivellAcumulat.toString()
            plenarTaula(primeraRuta.llistaDePunts)
        }
        ultim.addActionListener{
            // instruccions per a situar-se en l'última ruta, i visualitzar-la
            val querryUltimo="SELECT count(*) FROM RUTES"
            val ultimo = st.executeQuery(querryUltimo)
            val ultimoInt=ultimo.getInt(1)
            val gRutes = GestionarRutesBD()
            val primeraRuta:Ruta= gRutes.buscar(ultimoInt)
            qNom.text=primeraRuta.nom
            qDesn.text=primeraRuta.desnivell.toString()
            qDesnAcum.text=primeraRuta.desnivellAcumulat.toString()
            plenarTaula(primeraRuta.llistaDePunts)
        }
        tancar.addActionListener{
        }

        inicialitzar()
        VisRuta()
    }

    fun plenarTaula(ll_punts: MutableList<PuntGeo>){
        var ll = Array(ll_punts.size) { arrayOfNulls<String>(3) }
        for (i in 0 until ll_punts.size){
            ll[i][0]=ll_punts.get(i).nom
            ll[i][1]=ll_punts.get(i).coord.latitud.toString()
            ll[i][2]=ll_punts.get(i).coord.longitud.toString()
        }
        val caps = arrayOf("Nom punt","Latitud","Longitud")
        punts.setModel(javax.swing.table.DefaultTableModel(ll,caps))
    }

    fun inicialitzar() {
        // instruccions per a inicialitzar llista i numActual

    }

    fun VisRuta(){
        // instruccions per a visualitzar la ruta actual (l'índex el tenim en numActual)

        ActivarBotons()
    }

    fun ActivarBotons(){
        // instruccions per a activar o desactivar els botons de moviment ( isEnabled )

    }

}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        FinestraComplet().isVisible = true
    }
}

