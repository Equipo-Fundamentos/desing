/*
*https://www.tutorialspoint.com/swing/swing_tutorial.pdf
*http://dalila.sip.ucm.es/~manuel/JSW1/Slides/Swing.pdf
*
*
*
*
*/
import java.io.BufferedReader;// Lector de flujo
import java.io.BufferedWriter;// Escritor en flujo
import java.io.File;// Administrador de archivos
import java.io.FileReader;// Lector de archivo
import java.io.FileWriter;// Escritor de archivo
import java.io.FileNotFoundException;// Excepcion de archivo no encontrado
import java.io.IOException;
import java.awt.event.*; // Eventos a los controles
import javax.swing.*; // Librería que tiene las clases para funciones graficas
import java.util.Arrays;

public class Interfaz extends JFrame // extends por que es una clase que hereda de Jframe
{
	public static void main(String[] args)
	{
		Interfaz ventanaGrafica = new Interfaz();
		ventanaGrafica.setVisible(true); // se abra la ventana en la ejecución
		 System.out.println("Working Directory = " +  System.getProperty("user.dir"));
	}

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~CREANDO LA INTEFAZ GRAFICA~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		/* ====== elementos a usar similar a variables ==== */
			JPanel 	panelLogIn,panelLogInContent,panelLogInAux,panelLogInAux2, //panel login
					panelMain,panelMainTitle,panelMainButtons,panelMainList,panelBtnBorrar, //panel main
					panelDetails,panelDetailsButtons,panelDetailsControles,panelDetailsSave, //panel details
					panelFooter,panelFooterHora; //panel footer

			JButton btnIngresar,// panel login
					btnAgregar,btnReportesGrales,btnBorrar, //panel main
					btnEditar, btnGuardar ,btnReporteInd, //panel details
					btnCerrar; // panel footer

			JTextField 	txtUser, // panel login
						txtNombre,txtApp,txtApm,txtCargo,txtSueldo,txtNominaNum,txtFechaIngreso,
						txtDiasTrabajdos,txtAsignaciones, txtDeducciones; // panel details

			JPasswordField txtPass;
			JLabel lblTitulo,lblStatus;
			JList list;
        /* ====== Base de datos (Arreglo bidimensional) ==== */
        String[][] bd = new String [100][10];
            /*
            -Cada renglón es una nómina
            -Cada columna es un valor:
                0) Nombre
                1) Apellido Paterno
                2) Apellido Materno
                3) Nómina
                4) Cargo
                5) Sueldo (80-...)
                6) Días Trabajados (1-365)
                7) Asignaciones
                8) Deducciones
                9) Fecha de Ingreso (dd/mm/aa)
            */
		/* =============================================== */
	public Interfaz()
	{
		/* ===establecer propiedades de la ventana== */
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // para que se termine la execución cuando se cierra
			setResizable(false); // desabilita la opción de cambiar tamaño
			setSize(new java.awt.Dimension(1000, 550));// Establecer tamaño
			setTitle("miSueldo");//titulo de la ventana
			setLayout(new java.awt.GridBagLayout());//para ordenar los elementos con cordenadas
		/* ========================================== */

		/* ===Armando la interfaz=== */
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
						panelMainButtons = new JPanel();//panel para los botones dentro del maintitle
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
				lblStatus = new JLabel("Esperando inciar sesión");
				panelFooterHora.add(lblStatus);
			btnCerrar = new JButton("Cerrar");
			btnCerrar.setToolTipText("Salir de miSueldo");
			panelFooter.add(panelFooterHora,java.awt.BorderLayout.WEST);
			panelFooter.add(btnCerrar,java.awt.BorderLayout.EAST);
		/* ==================================== */

		/* =====Agregar paneles en orden a la ventana==== */
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
		/* ============================================ */

		/* ====Deshabilitar todo hasta que se loggue==== */
			deshabilitarTodo();
		/* ============================================ */

		/* ==== Agregar eventos a los controles ==== */
            // Inicio y cierre de sesión
			btnIngresar.addActionListener(new IngresarAlSistema());
            btnIngresar.addActionListener(new LectorCSV());
			btnCerrar.addActionListener(new CerrarElSistema());
            // Adición a BD
            btnAgregar.addActionListener(new AgregaraBD());
            btnGuardar.addActionListener(new AgregaraBD());
            btnReportesGrales.addActionListener(new EscritorCSV());

            //quitar en production
            txtUser.setText("rob");
            txtPass.setText("123");

		/* ========================================== */
	}
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~FIN CREACION DE LA INTERFAZ GRAFICA~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~CREACIÓN DE MÉTODOS PARA LOS CONTROLES~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/* ######agregar a los controles instanciados los eventos#####*/
		boolean session = false;
	// Iniciar Sesión
		public class IngresarAlSistema implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(!session) // si no he iniciado
				{
					//intenta iniciar
					if(txtUser.getText().equals("rob") &&
						String.valueOf(txtPass.getPassword()).equals("123")) //entra
					{
						habiltarTodo();
						session = !session;
						txtUser.setEnabled(false);
						txtPass.setEnabled(false);
						btnIngresar.setText("Salir");
						lblStatus.setText("CONECTADO");

					}
					else // no entra
					{
						JOptionPane.showMessageDialog(null,"Datos incorrectos", "Error",
						JOptionPane.ERROR_MESSAGE);
						txtUser.setText("");
						txtPass.setText("");
					}
				}
				else // ya inicio la sesion, entonces va a cerrar session
				{
					deshabilitarTodo();
					session = !session;
					txtPass.setText("");
					txtUser.setText("");
					txtUser.setEnabled(true);
					txtPass.setEnabled(true);
					btnIngresar.setText("Ingresar");
					lblStatus = new JLabel("Esperando inciar sesión");
				}
			}
		}
	// Cerrar el sistema
		public class CerrarElSistema implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(session)
				{
					JOptionPane.showMessageDialog(null,"Se cerrará la sesión", "miSueldo",
					JOptionPane.WARNING_MESSAGE);
				}
				dispose();
			}
		}
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~FIN CREACIÓN DE MÉTODOS PARA LOS CONTROLES~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~MÉTODOS AUXILIARES~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		public void habiltarTodo()
		{
			btnAgregar.setEnabled(true);
			btnReportesGrales.setEnabled(true);
			btnBorrar.setEnabled(true);
			btnEditar.setEnabled(true);
			btnGuardar.setEnabled(true);
			btnReporteInd.setEnabled(true);
			txtNombre.setEnabled(true);
			txtApp.setEnabled(true);
			txtApm.setEnabled(true);
			txtCargo.setEnabled(true);
			txtSueldo.setEnabled(true);
			txtNominaNum.setEnabled(true);
			txtFechaIngreso.setEnabled(true);
			txtDiasTrabajdos.setEnabled(true);
			txtAsignaciones.setEnabled(true);
			txtDeducciones.setEnabled(true);
			//list.setEnabled(true);
		}
		public void deshabilitarTodo()
		{
			btnAgregar.setEnabled(false);
			btnReportesGrales.setEnabled(false);
			btnBorrar.setEnabled(false);
			btnEditar.setEnabled(false);
			btnGuardar.setEnabled(false);
			btnReporteInd.setEnabled(false);
			txtNombre.setEnabled(false);
			txtApp.setEnabled(false);
			txtApm.setEnabled(false);
			txtCargo.setEnabled(false);
			txtSueldo.setEnabled(false);
			txtNominaNum.setEnabled(false);
			txtFechaIngreso.setEnabled(false);
			txtDiasTrabajdos.setEnabled(false);
			txtAsignaciones.setEnabled(false);
			txtDeducciones.setEnabled(false);
			//list.setEnabled(false);
		}
        public boolean validacion() {

			String nomb = txtNombre.getText(),
			       app = txtApp.getText(),
			       apm = txtApm.getText(),
                   fecha = txtFechaIngreso.getText(),
                   cargo = txtCargo.getText(),
                   sldo = txtSueldo.getText(),
                   numNomina =txtNominaNum.getText(),
                   dias = txtDiasTrabajdos.getText(),
                   asignaciones = txtAsignaciones.getText(),
                   deducciones = txtDeducciones.getText();


            boolean noError = true;

                    // Texto
                    if (!nomb.matches("\\p{Alpha}+")) {JOptionPane.showMessageDialog(null,"Error en dato: Nombre."); noError = false;}
                    if (!app.matches("\\p{Alpha}+")) {JOptionPane.showMessageDialog(null,"Error en dato: Apellido paterno."); noError = false;}
                    if (!apm.matches("\\p{Alpha}+")) {JOptionPane.showMessageDialog(null,"Error en dato: Apellido materno."); noError = false;}
                    if (!cargo.matches("\\p{Alpha}+")) {JOptionPane.showMessageDialog(null,"Error en dato: Cargo"); noError = false;}
                    // Número
                    if (!fecha.matches("[1-30]+/[1-12]+/\\d+")) {JOptionPane.showMessageDialog(null,"Error en dato: Fecha de ingreso"); noError = false;}
                    if (!sldo.matches("(\\d++.\\d+|\\d+)")) {JOptionPane.showMessageDialog(null,"Error en dato: Sueldo"); noError = false;}
                    if (!numNomina.matches("\\d+")) {JOptionPane.showMessageDialog(null,"Error en dato: No. de Nómina"); noError = false;}
                        else {// Verifica que la nómina no exista ya
                            for (int i = 0; i < bd[3].length;) {
                                if (numNomina.equals(bd[3][i])) {JOptionPane.showMessageDialog(null,"No. de Nómina ocupado."); noError = false;}
                            }
                        }
                        if (!dias.matches("\\[1-365]")) {JOptionPane.showMessageDialog(null,"Error en dato: Días trabajados"); noError = false;}
                        if (!asignaciones.matches("(\\d++.\\d+|\\d+)")) {JOptionPane.showMessageDialog(null,"Error en dato: Asignaciones"); noError = false;}
                        if (!deducciones.matches("(\\d++.\\d+|\\d+)")) {JOptionPane.showMessageDialog(null,"Error en dato: Deducciones"); noError = false;}

                return noError;
            }
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~FIN MÉTODOS AUXILIARES~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~MÉTODOS PARA BASE DE DATOS~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    public class AgregaraBD implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand(), origen;
            BufferedWriter bw = null;
            int indice = 0;



            if (session && command.equals("Agregar") && validacion()) {
/* ******************** Agregar datos a arreglo BD******************** */
                for (int i = 0; i < bd.length; i++) {
                    if (bd[i][0] == null) { // Si el espacio no esta asignado
                        indice = i;
                        // Asignar todos los valores colocados a esa nómina
                        bd[i][0] = txtNombre.getText();
                        txtNombre.setText("");
                        bd[i][1] = txtApp.getText();
                        txtApp.setText("");
                        bd[i][2] = txtApm.getText();
                        txtApm.setText("");
                        bd[i][3] = txtNominaNum.getText();
                        txtNominaNum.setText("");
                        bd[i][4] = txtCargo.getText();
                        txtCargo.setText("");
                        bd[i][5] = txtSueldo.getText();
                        txtSueldo.setText("");
                        bd[i][6] = txtDiasTrabajdos.getText();
                        txtDiasTrabajdos.setText("");
                        bd[i][7] = txtAsignaciones.getText();
                        txtAsignaciones.setText("");
                        bd[i][8] = txtDeducciones.getText();
                        txtDeducciones.setText("");
                        bd[i][9] = txtFechaIngreso.getText();
                        txtFechaIngreso.setText("");
                        JOptionPane.showMessageDialog(null,bd[i][0] + " " + bd[i][1] + " " + bd[i][2] + "\nRegistrado con nómina: " + bd[i][3]);
                        break;
                    }
                }
/* ******************** Agregar datos a archivo BD******************** */
                try {
                    File archivoCSV = new File(System.getProperty("user.dir")  + "/bd.csv");
                    if (!archivoCSV.exists()) archivoCSV.createNewFile();
                    bw = new BufferedWriter(new FileWriter(archivoCSV));
                        bw.append(bd[indice][0] + ",");
                        bw.append(bd[indice][1] + ",");
                        bw.append(bd[indice][2] + ",");
                        bw.append(bd[indice][3] + ",");
                        bw.append(bd[indice][4] + ",");
                        bw.append(bd[indice][5] + ",");
                        bw.append(bd[indice][6] + ",");
                        bw.append(bd[indice][7] + ",");
                        bw.append(bd[indice][8] + ",");
                        bw.append(bd[indice][0] + "\n");
                } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                } catch (IOException e1) {
                        e1.printStackTrace();
                } finally {
                    if (bw != null) {
                        try {
                            bw.close();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
            else e.finalize();
        }
    }

    public class LectorCSV implements ActionListener { // Para que se active cuando haya una acción
        public void actionPerformed(ActionEvent event) {
            int contador = 0;
            BufferedReader br = null;
            String c = "",
            linea = "",
            separador = ",",
            command = event.getActionCommand(),
            csv = System.getProperty("user.dir")+"/bd.csv"; // El archivo que se quiera utilizar como base de datos siempre se debe imprimir
            File archivo = new File(csv);


                if (command.equals("Ingresar")) {
                    if (txtUser.getText().equals("rob") && String.valueOf(txtPass.getPassword()).equals("123")) {
                        try {
                        if (!archivo.exists()) archivo.createNewFile();
                        br = new BufferedReader(new FileReader(archivo));
                        while ((linea = br.readLine()) != null) { // Mientras el archivo tenga valores
                            String[] perfil = linea.split(separador); // Crear un arreglo con los valores de la línea
                            System.out.println(Arrays.toString(perfil));// Debug
                            for (int i = 0;i < bd.length; i++) { // Corrobora que espacio de la base de datos esta vacío
                                if (bd[i][0] == null) { // Si el espacio esta vacío asigna los valores de la línea a la base de datos
                                    bd[i][0] = perfil[0];
                                    bd[i][1] = perfil[1];
                                    bd[i][2] = perfil[2];
                                    bd[i][3] = perfil[3];
                                    bd[i][4] = perfil[4];
                                    bd[i][5] = perfil[5];
                                    bd[i][6] = perfil[6];
                                    bd[i][7] = perfil[7];
                                    bd[i][8] = perfil[8];
                                    bd[i][9] = perfil[9];
                                    contador++;
                                    break;
                                    }
                                }
                            }
                        }catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        if (br != null) {
                            try {
                                br.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
            c = Integer.toString(contador);
            if (txtUser.getText().equals("rob") && String.valueOf(txtPass.getPassword()).equals("123")) {
                JOptionPane.showMessageDialog(null,c + " perfiles agregados a la base de datos del archivo csv");
            }
        }
    }
    public class EscritorCSV implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String command = event.getActionCommand(), d = "";
            BufferedWriter be = null;
            String perfiles = "";
            double sueldo, dias, asignaciones, deducciones, nomina;

            d = System.getProperty("user.dir") + "/reporte.csv";

            try {
                if (command.equals("Generar Reportes")) {
                    File reporte = new File(d); // Especifica el nombre del archivo para el reporte
                    Runtime.getRuntime().exec(new String[]{"rm",d});// Borra el reporte pasado
                    reporte.createNewFile(); //Crea el archivo del reporte
                    be = new BufferedWriter(new FileWriter(reporte));
                    for (int i = -1; i < bd.length; i++) {
                        if (i == -1) { // Escribe los títulos de columna para el reporte
                            be.append("Nombre,");
                            be.append("Apellido Paterno,");
                            be.append("Apellido Materno,");
                            be.append("No. Nómina,");
                            be.append("Cargo,");
                            be.append("Sueldo,");
                            be.append("Días Trabajados,");
                            be.append("Asignaciones,");
                            be.append("Deducciones,");
                            be.append("Fecha de Ingreso,");
                            be.append("Nómina Calculada\n");
                            continue;
                        }

                        // Escribe al reporte
                        if (bd[i][0] == null) continue;
                        be.append(bd[i][0]+",");// Nombre
                        be.append(bd[i][1]+",");// APP
                        be.append(bd[i][2]+",");// APM
                        be.append(bd[i][3]+",");// No. Nómina
                        be.append(bd[i][4]+",");// Cargo
                        be.append(bd[i][5]+",");// Sueldo
                        be.append(bd[i][6]+",");// Días Trabajados
                        be.append(bd[i][7]+",");// Asignaciones
                        be.append(bd[i][8]+",");// Deducciones
                        be.append(bd[i][9]+",");// Fecha de Ingreso
                        //Cálculo de nómina
                        sueldo = Double.parseDouble(bd[i][5]);
                        dias = Double.parseDouble(bd[i][6]);
                        asignaciones = Double.parseDouble(bd[i][7]);
                        deducciones = Double.parseDouble(bd[i][8]);
                        nomina = sueldo*dias+asignaciones-deducciones;
                        be.append(Double.toString(nomina)+"\n");// Nómina
                    }
                    Runtime.getRuntime().exec(new String[]{"open",d});
                }
            }  catch (IOException ioe) {
                ioe.printStackTrace();
                }
                finally {
                    try {
                        if (be != null) be.close();
                    } catch (Exception ex) {
                        System.out.println("Error in closing the BufferedWriter"+ex);
                    }
                }
            }
        }
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~FIN MÉTODOS BD~~~~~~~~~~~~~~~~~
    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
