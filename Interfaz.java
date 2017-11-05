import javax.swing.*; //libreria que tiene las clases para funciones graficas
public class Interfaz extends JFrame// extends por que es una clase que hereda de Jframe
{
	public Interfaz()
	{
		/* elementos a usar similar a variables */
		JPanel panelLogIn,panelMain,panelFooter,panelDetails;
		JLabel texto;
		JButton boton;
		/* ==================================== */

		/* establecer propiedades de la ventana */
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // para que se termine la execución
		setResizable(false); // desabilita la opción de cambiar tamaños
		setSize(new java.awt.Dimension(1100, 600));// Establecer tamaño
		setTitle("miSueldo");//titulo de la ventana
		setLayout(new java.awt.GridBagLayout());//para ordenar los elementos
		/* ==================================== */


		/* Armando la interfaz */
		// Panel de Log In
			panelLogIn = new JPanel(); 
			panelLogIn.setBackground(new java.awt.Color(187, 72, 72));
			//panelLogIn.setLayout();
			//panelLogIn.add(new JLabel("Log in",10));
			//panelLogIn.add(new JButton("Ingresar"));
		//panel Area Principal
			panelMain = new JPanel();
			panelMain.setBackground(new java.awt.Color(0, 72, 72));
		//panel Details
			panelDetails = new JPanel();
			panelDetails.setBackground(new java.awt.Color(72, 72, 0));
		//panel Footer
			panelFooter = new JPanel();
			panelFooter.setBackground(new java.awt.Color(72, 0, 72));
		/* ==================================== */

		/* Agregar paneles en orden a la ventana */
		// se usara getContentPane() porque la ventana no es el contenedor, 
		//sino un componente que tiene la ventana y se obtiene asi
		/*
		¨¨0¨¨¨¨¨¨1¨¨¨¨¨¨2¨¨¨¨:
		-Log---Main----Detai :
		|....|........|....| 0 
		|....|........|....| :
		|....|........|....| 1
		|....|........|....| :
		|....|........|....| 2
		|....|........|....| :
		|~~~~~~~~~~~~~~~~~~| 3
		-------Footer------- 
		*/
		java.awt.GridBagConstraints constraints = new java.awt.GridBagConstraints();
			//panel Log in
			constraints.gridx = 0; // El panelLogIn empieza en la columna cero
			constraints.gridy = 0; // El panelLogIn empieza en la fila cero
			constraints.gridwidth = 1; // El panelLogIn ocupa 1 columnas
			constraints.gridheight = 3; // El panelLogIn ocupa 3 filas
			constraints.weighty = 1.0; // La fila 0 debe estirarse, le ponemos un 1.0
			// El panel debe estirarse en ambos sentidos. Ponemos el campo fill
			constraints.fill = java.awt.GridBagConstraints.BOTH;
			this.getContentPane().add(panelLogIn, constraints); 

			//panel Main
			constraints.gridx = 1; // El panelMain empieza en la columna 1
			constraints.gridy = 0; // El panelMain empieza en la fila cero
			constraints.gridwidth = 2; // El panelMain ocupa 2 columnas.
			constraints.gridheight = 3; // El panelMain ocupa 3 filas.
			//constraints.weighty La fila 0 debe estirarse, le ponemos un 1.0 pero ya estaba definido so aqui ya no
			//constraints.fill El panel debe estirarse en ambos sentidos. Ponemos el campo fill.
			constraints.fill = java.awt.GridBagConstraints.BOTH;
			this.getContentPane().add(panelMain, constraints);

			//panel Details
			constraints.gridx = 3; // El panelDetails empieza en la columna 3
			constraints.gridy = 0; // El panelDetails empieza en la fila 0
			constraints.gridwidth = 1; // El panelDetails ocupa 1 columnas.
			constraints.gridheight = 3; // El panelDetails ocupa 3 filas.
			//constraints.weighty La fila 0 debe estirarse, ya tampoco es necesario
			//constraints.fill 
			this.getContentPane().add(panelDetails, constraints);
			constraints.weighty = 0.0; // Restauramos al valor por defecto, para no afectar 

			//panelFooter
			constraints.gridx = 0; // El panelFooter empieza en la columna 0
			constraints.gridy = 3; // El panelFooter empieza en la fila 3
			constraints.gridwidth = 3; // El panelFooter ocupa 3 columnas.
			constraints.gridheight = 1; // El panelFooter ocupa 1 filas.
			constraints.weightx = 1.0; // La columna 1 debe estirarse, ponemos el 1.0 en weigthx
			// El campo de texto debe estirarse sólo en horizontal.
			constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
			this.getContentPane().add(panelFooter, constraints);
			constraints.weightx = 0.0; // Restauramos al valor por defecto, para no afectar 

		setLocationRelativeTo(null); // debe estar al final,es para que quede centrado en la pantalla
		/* ==================================== */
	}
}