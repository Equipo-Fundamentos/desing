import javax.swing.*; //libreria que tiene las clases para funciones graficas
public class Interfaz extends JFrame// extends por que es una clase que hereda de Jframe
{
	public Interfaz()
	{
		/* elementos a usar similar a variables */
		JPanel panelLogIn;
		JLabel texto;
		JButton boton;
		/* ==================================== */

		/* establecer propiedades de la ventana */
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // para que se termine la execución
		setResizable(false); // desabilita la opción de cambiar tamaños
		setSize(new java.awt.Dimension(1100, 600));// Establecer tamaño
		setTitle("miSueldo");//titulo de la ventana
		/* ==================================== */


		/* Armando la interfaz */
		// Panel de Log In
		panelLogIn = new JPanel(); 
		panelLogIn.setBackground(new java.awt.Color(187, 72, 72));
		//panelLogIn.setLayout();
		panelLogIn.add(new JLabel("Log in",10));
		panelLogIn.add(new JButton("Ingresar"));
		/* ==================================== */

		/* Agregar paneles en orden a la ventana */
		this.getContentPane().add(panelLogIn); // this hace referencia a la venana 
		setLocationRelativeTo(null); // debe estar al final,es para que quede centrado en la pantalla
		/* ==================================== */
	}
}