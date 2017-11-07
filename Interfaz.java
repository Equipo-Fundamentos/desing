/*
Interfaz.java contiene todo lo que pertenece a la interfaz gráfica, es decir el armado de el diseño y la interfaz.
Su objeto esta instanciado en Principal.java
*/
import javax.swing.*; //libreria que tiene las clases para funciones graficas
public class Interfaz extends JFrame// extends por que es una clase que hereda de Jframe
{
	public static void main(String[] args)
	{
		Interfaz ventanaGrafica = new Interfaz();
		ventanaGrafica.setVisible(true);
	}
	public Interfaz()
	{
		/* elementos a usar similar a variables */
			JPanel 	panelLogIn,panelLogInContent,panelLogInAux,panelLogInAux2,
					panelMain,panelMainTitle,panelMainButtons,panelMainList,panelBtnBorrar,
					panelDetails,panelDetailsButtons,panelDetailsControles,panelDetailsSave,
					panelFooter,panelFooterHora;

			JButton btnIngresar,btnAgregar,btnReportesGrales,btnBorrar,btnEditar, btnGuardar ,btnReporteInd, btnSalir;

			JTextField 	txtUser, txtNombre,txtApp,txtApm,txtCargo,txtSueldo,txtNominaNum,txtFechaIngreso,
						txtDiasTrabajdos,txtAsignaciones, txtDeducciones;
			JPasswordField txtPass;
			JLabel lblTitulo,lblHora;
			JList list;
		/* ==================================== */

		/* establecer propiedades de la ventana */
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // para que se termine la execución cuando se cierra
			setResizable(false); // desabilita la opción de cambiar tamaño
			setSize(new java.awt.Dimension(1000, 550));// Establecer tamaño
			setTitle("miSueldo");//titulo de la ventana
			setLayout(new java.awt.GridBagLayout());//para ordenar los elementos con cordenadas
		/* ==================================== */

		/* Armando la interfaz */
		// Panel de Log In
			panelLogIn = new JPanel(); // panel derecho area log in 
			//panelLogIn.setBackground(new java.awt.Color(187, 72, 72)); //dar color
			panelLogIn.setLayout(new java.awt.GridLayout(3,1,0,5));// ordenar elementos (3 paneles)
				panelLogInAux = new JPanel();// panel aux contendrá al label Log in
					panelLogInAux.setLayout(new java.awt.BorderLayout());// posicionar la etiqueta
					panelLogInAux.add(new JLabel("                Log in"),java.awt.BorderLayout.SOUTH);
				panelLogInContent = new JPanel();// tendra textfields
					panelLogInContent.add(new JLabel("Usuario"));
					txtUser = new JTextField(10);
					txtUser.setToolTipText("Ingrese su Usuario");
					panelLogInContent.add(txtUser);
					panelLogInContent.add(new JLabel("Contraseña"));
					txtPass = new JPasswordField(10);
					txtPass.setToolTipText("Ingrese Contraseña");
					panelLogInContent.add(txtPass);
				panelLogInAux2 = new JPanel();// tendra el boton de ingresar
					btnIngresar = new JButton("Ingresar");
					panelLogInAux2.add(btnIngresar);

			panelLogIn.add(panelLogInAux);// primer panel agregado
			panelLogIn.add(panelLogInContent);// segundo panel agregado
			panelLogIn.add(panelLogInAux2);// tercer panel agregado

		//panel Area Principal
			panelMain = new JPanel(); // panel de en medio
			panelMain.setBackground(new java.awt.Color(189, 195, 199));//darle color
			panelMain.setLayout(new java.awt.BorderLayout());// para ordenar
				panelMainTitle = new JPanel();// panel donde esta lbl y botones
				panelMainTitle.setBackground(new java.awt.Color(189, 195, 199));
						panelMainButtons = new JPanel();//panel para los botones
						panelMainButtons.setBackground(new java.awt.Color(189, 195, 199));
						btnAgregar = new JButton("Agregar");
						btnAgregar.setToolTipText("Crear nuevo empleado");
						panelMainButtons.add(btnAgregar);
						btnReportesGrales = new JButton("Generar Reportes");
						btnReportesGrales.setToolTipText("Genera el reporte para todos los empleados");
						panelMainButtons.add(btnReportesGrales);
					panelMainTitle.setLayout(new java.awt.BorderLayout());// para ordenar
						lblTitulo = new JLabel("miSueldo");
						lblTitulo.setFont(new java.awt.Font("", 1, 30));
					panelMainTitle.add(lblTitulo,java.awt.BorderLayout.NORTH);//para que el label quede hasta arriba
					panelMainTitle.add(panelMainButtons);// para que se posicionen abajo del label
				panelMainList = new JPanel(); //panel que contiene el Jlist
				panelMainList.setBackground(new java.awt.Color(189, 195, 199));
					panelMainList.add(new JLabel("Empleados"));
				panelBtnBorrar = new JPanel();// panel que tendra el boton de borrar
				btnBorrar = new JButton("Borrar");
				btnBorrar.setToolTipText("Eliminar el empleado seleccionado");
				panelBtnBorrar.setBackground(new java.awt.Color(189, 195, 199));
				panelBtnBorrar.add(btnBorrar);//quede centrado

			panelMain.add(panelMainTitle,java.awt.BorderLayout.NORTH); //quede hasta arriba
			panelMain.add(panelMainList); //cubra lo que sobra
			panelMain.add(panelBtnBorrar,java.awt.BorderLayout.SOUTH);//quede hasta abajo
			//panelMain.setEnabled(false);////** false para que se habilite cuando se loguee

		//panel Details
			panelDetails = new JPanel();// panel de lado derecho
			panelDetails.setBackground(new java.awt.Color(218,223,225));
			panelDetails.setLayout(new java.awt.BorderLayout());//para ordenar Norte Centro Sur
				panelDetailsButtons = new JPanel();// panel con los botones editar y generar reporte
					btnEditar = new JButton("Editar");
					btnEditar.setToolTipText("Modificar datos del empleado seleccionado");
					panelDetailsButtons.add(btnEditar);
					btnReporteInd = new JButton("Generar Reporte");
					btnReporteInd.setToolTipText("Generar el reporte para el emepleado seleccionado");
					panelDetailsButtons.add(btnReporteInd);

				panelDetailsControles = new JPanel(); // panel con todos las datos
				panelDetailsControles.setLayout(new java.awt.GridLayout(10,2,-50,20)); // para ordenar 10 filas x 2 columnas
					panelDetailsControles.add(new JLabel("Nombre:"));
					txtNombre = new JTextField(8);
					txtNombre.setToolTipText("ej. Fernando");
					panelDetailsControles.add(txtNombre);

					panelDetailsControles.add(new JLabel("Apellido Paterno:"));
					txtApp = new JTextField(8);
					txtApp.setToolTipText("ej. Pérez");
					panelDetailsControles.add(txtApp);

					panelDetailsControles.add(new JLabel("Apellido Materno:"));
					txtApm = new JTextField(8);
					txtApm.setToolTipText("ej. Pérez");
					panelDetailsControles.add(txtApm);

					panelDetailsControles.add(new JLabel("Cargo:"));
					txtCargo = new JTextField(8);
					txtCargo.setToolTipText("puesto o ocupación en la empresa");
					panelDetailsControles.add(txtCargo);

					panelDetailsControles.add(new JLabel("Suledo Base:"));
					txtSueldo = new JTextField(8);
					txtSueldo.setToolTipText("$$$");
					panelDetailsControles.add(txtSueldo);

					panelDetailsControles.add(new JLabel("Fecha de ingreso:"));
					txtFechaIngreso = new JTextField(8);
					txtFechaIngreso.setToolTipText("dd/mm/yyyy");
					panelDetailsControles.add(txtFechaIngreso);

					panelDetailsControles.add(new JLabel("Numero de cuemta de nomina:"));
					txtNominaNum = new JTextField(8);
					txtNominaNum.setToolTipText("ej. 000000");
					panelDetailsControles.add(txtNominaNum);

					panelDetailsControles.add(new JLabel("Dias Trabajados:"));
					txtDiasTrabajdos = new JTextField(8);
					txtDiasTrabajdos.setToolTipText("ej. 14");
					panelDetailsControles.add(txtDiasTrabajdos);

					panelDetailsControles.add(new JLabel("Asignaciones:"));
					txtAsignaciones = new JTextField(8);
					panelDetailsControles.add(txtAsignaciones);

					panelDetailsControles.add(new JLabel("Deducciones:"));
					txtDeducciones = new JTextField(8);
					panelDetailsControles.add(txtDeducciones);

				panelDetailsSave = new JPanel(); // panel que tendra boton de guardar
					btnGuardar = new JButton("Guardar");
					btnGuardar.setToolTipText("Guardar los cambios");
					panelDetailsSave.add(btnGuardar);

			panelDetails.add(panelDetailsButtons,java.awt.BorderLayout.NORTH);//quede hasta arriba
			panelDetails.add(panelDetailsControles);//cubra lo que sobre
			panelDetails.add(panelDetailsSave,java.awt.BorderLayout.SOUTH);//que hasta abajo
			//panelDetails.setEnabled(false);////***** false para que se habilite cuando se loguee
		//panel Footer
			panelFooter = new JPanel();
			panelFooter.setBackground(new java.awt.Color(238, 238, 238));
			panelFooter.setLayout(new java.awt.BorderLayout());//para ordenar oeste centro este
				panelFooterHora = new JPanel();
				lblHora = new JLabel("Insertar hora aqui");
				panelFooterHora.add(lblHora);
			btnSalir = new JButton("Salir");
			btnSalir.setToolTipText("Salir de miSueldo");
			panelFooter.add(panelFooterHora,java.awt.BorderLayout.WEST);
			panelFooter.add(btnSalir,java.awt.BorderLayout.EAST);
			//panelFooter.setEnabled(false);////***** false para que se habilite cuando se loguee
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
			constraints.gridwidth =4 ; // El panelFooter ocupa 3 columnas.
			constraints.gridheight = 1; // El panelFooter ocupa 1 filas.
			constraints.weightx = 1.0; // La columna 1 debe estirarse, ponemos el 1.0 en weigthx
			// El campo de texto debe estirarse sólo en horizontal.
			constraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
			this.getContentPane().add(panelFooter, constraints);
			constraints.weightx = 0.0; // Restaura el valor por defecto, para no afectar 

		setLocationRelativeTo(null); // debe estar al final,es para que quede centrado en la pantalla
		/* ==================================== */
	}
}