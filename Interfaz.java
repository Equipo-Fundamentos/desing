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
	private static final long serialVersionUID = 1L; // ver para que sirve pero quita una advertencia
	public static void main(String[] args) throws IOException
	{
		Interfaz ventanaGrafica = new Interfaz();
		ventanaGrafica.setVisible(true); // se abra la ventana en la ejecución
	}

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~CREANDO LA INTEFAZ GRAFICA~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
		/* ====== elementos a usar similar a variables ==== */
			JPanel 	panelLogIn,panelLogInContent,panelLogInAux,panelLogInAux2, //panel login
					panelMain,panelMainTitle,panelMainButtons,panelMainList,panelBtnActVerBorrar, //panel main
					panelDetails,panelDetailsButtons,panelDetailsControles,panelDetailsSaveCancel, //panel details
					panelFooter,panelFooterHora; //panel footer

			JButton btnIngresar,// panel login
					btnAgregar,btnReportesGrales,btnVer,btnBorrar, btnActualizar, //panel main
					btnEditar, btnGuardar ,btnReporteInd, btnCancelar, //panel details
					btnCerrar; // panel footer

			JTextField 	txtUser, // panel login
						txtNombre,txtApp,txtApm,txtCargo,txtSueldo,txtNominaNum,txtFechaIngreso,
						txtDiasTrabajdos,
						txtBonos,txtFeriados,
						txtHorasExtra, txtAsignacionesOtros,
						txtIVA, txtISR,
						txtPrestamos, txtDeduccionesOtros; // panel details

			JPasswordField txtPass;
			JLabel lblTitulo,lblStatus;
			JList<String> list;
			DefaultListModel<String> listModel;
			JScrollPane listScroller;
            boolean editar = false;
            int indice = 0;
            double ivaGlobal = 16;
		/* ====== Base de datos (Arreglo bidimensional) ==== */
		String[][] bd = new String [100][15];
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
				7) Horas extra
				8) Bonos
                9) Otras asignaciones
                10) IVA (16%)
                11) Feriados
                12) Préstamos
                13) Otras deducciones
				14) Fecha de Ingreso (dd/mm/aaaa)
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
							btnAgregar = new JButton("Nuevo");
							btnAgregar.setToolTipText("Crear nuevo perfil");
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
				panelMainList.setLayout(new java.awt.BorderLayout());
				panelMainList.setBackground(new java.awt.Color(189, 195, 199));
					//generar  la lista!
					listModel = new DefaultListModel<>();
					list = new JList<>();
					list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					listScroller = new JScrollPane();
					actualizaList();// llena con lo que hay en la bd
					listScroller.setViewportView(list);
					list.setLayoutOrientation(JList.VERTICAL);
					// fin generacion lista
				panelMainList.add(new JLabel("Empleados(nóminas)"),java.awt.BorderLayout.NORTH);
				panelMainList.add(listScroller);

				panelBtnActVerBorrar = new JPanel();// panel que tendra el boton de borrar
					btnBorrar = new JButton("Borrar");
					btnBorrar.setToolTipText("Eliminar el empleado seleccionado");
					btnVer = new JButton("Ver");
					btnVer.setToolTipText("Mostrará los detalles de esta nómina->");
					btnActualizar = new JButton("Actualizar");
					btnActualizar.setToolTipText("Vuelve a leer la BD");
					panelBtnActVerBorrar.setBackground(new java.awt.Color(189, 195, 199));
					panelBtnActVerBorrar.add(btnActualizar);
					panelBtnActVerBorrar.add(btnVer);
					panelBtnActVerBorrar.add(btnBorrar);

			panelMain.add(panelMainTitle,java.awt.BorderLayout.NORTH); //quede hasta arriba
			panelMain.add(panelMainList); //cubra lo que sobra
			panelMain.add(panelBtnActVerBorrar,java.awt.BorderLayout.SOUTH);//quede hasta abajo

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
				panelDetailsControles.setLayout(new java.awt.GridLayout(16,4)); // para ordenar 10 filas x 2 columnas
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
					txtCargo.setToolTipText("Puesto u ocupación en la empresa");
					panelDetailsControles.add(txtCargo);

					panelDetailsControles.add(new JLabel("Salario Base ($):"));
					txtSueldo = new JTextField(8);
					txtSueldo.setToolTipText("$$$");
					panelDetailsControles.add(txtSueldo);

					panelDetailsControles.add(new JLabel("Fecha de ingreso:"));
					txtFechaIngreso = new JTextField(8);
					txtFechaIngreso.setToolTipText("dd/m/yyyy");
					panelDetailsControles.add(txtFechaIngreso);

					panelDetailsControles.add(new JLabel("Número de cuenta de nómina:"));
					txtNominaNum = new JTextField(8);
					txtNominaNum.setToolTipText("ej. 000");
					panelDetailsControles.add(txtNominaNum);

					panelDetailsControles.add(new JLabel("Dias Trabajados:"));
					txtDiasTrabajdos = new JTextField(8);
					txtDiasTrabajdos.setToolTipText("ej. 14");
					panelDetailsControles.add(txtDiasTrabajdos);

					panelDetailsControles.add(new JLabel("Bonos: "));
					txtBonos = new JTextField(8);
					txtBonos.setToolTipText("Bonos extra $$$");
					panelDetailsControles.add(txtBonos);

					panelDetailsControles.add(new JLabel("Días feriados: "));
					txtFeriados = new JTextField(8);
					txtFeriados.setToolTipText("Días feriados, ej. 4");
					panelDetailsControles.add(txtFeriados);

					panelDetailsControles.add(new JLabel("Horas Extra: "));
					txtHorasExtra = new JTextField(8);
					txtHorasExtra.setToolTipText("Cantidad de horas trabajas extra, ej. 3");
					panelDetailsControles.add(txtHorasExtra);


					panelDetailsControles.add(new JLabel("Otros Asignaciones:"));
					txtAsignacionesOtros = new JTextField(8);
					txtAsignacionesOtros.setToolTipText("Otras Asignaciones que se deban considerar $$$");
					panelDetailsControles.add(txtAsignacionesOtros);


					panelDetailsControles.add(new JLabel("IVA:"));
					txtIVA = new JTextField(8);
					txtIVA.setToolTipText("Deducciones por IVA %");
					panelDetailsControles.add(txtIVA);

					panelDetailsControles.add(new JLabel("ISR:"));
					txtISR = new JTextField(8);
					txtISR.setToolTipText("Deducciones por ISR %");
					panelDetailsControles.add(txtISR);

					panelDetailsControles.add(new JLabel("Prestamos:"));
					txtPrestamos = new JTextField(8);
					txtPrestamos.setToolTipText("Deducciones en prestamos $$$");
					panelDetailsControles.add(txtPrestamos);

					panelDetailsControles.add(new JLabel("Otras deducciones:"));
					txtDeduccionesOtros = new JTextField(8);
					txtDeduccionesOtros.setToolTipText("Otras deducciones que se deban considerar $$$");
					panelDetailsControles.add(txtDeduccionesOtros);

				panelDetailsSaveCancel = new JPanel(); // panel que tendra boton de guardar
					btnGuardar = new JButton("Guardar");
					btnGuardar.setToolTipText("Guardar los cambios");
					btnCancelar = new JButton("Cancelar");
					btnCancelar.setToolTipText("Ignorar Cambios");
					panelDetailsSaveCancel.add(btnGuardar);
					panelDetailsSaveCancel.add(btnCancelar);

			panelDetails.add(panelDetailsButtons,java.awt.BorderLayout.NORTH);//quede hasta arriba
			panelDetails.add(panelDetailsControles);//cubra lo que sobre
			panelDetails.add(panelDetailsSaveCancel,java.awt.BorderLayout.SOUTH);//que hasta abajo
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
			deshabilitaMainPanel();
			deshabilitaPanelDetails();
		/* ============================================ */

		/* ==== Agregar eventos a los controles ==== */
			// Inicio y cierre de sesión
			btnIngresar.addActionListener(new IngresarAlSistema());
			btnIngresar.addActionListener(new LectorCSV());
			btnCerrar.addActionListener(new CerrarElSistema());
			//Habilitar controles para agregar
			btnAgregar.addActionListener(new HabilitarTexFileds());
			btnCancelar.addActionListener(new DeshabilitaTextFields());
			btnVer.addActionListener(new VerDetallesEmpleado());
			btnEditar.addActionListener(new EditarEmpleado());
			btnActualizar.addActionListener(new ActualizaJList());
			// Adición a BD
			btnGuardar.addActionListener(new AgregaraBD());
			btnReportesGrales.addActionListener(new GenerarReportes());
            btnReporteInd.addActionListener(new GenerarReporte());
            // Borrar de BD
            btnBorrar.addActionListener(new BorrardeBD());

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
    // Actualiza la lista para que se vean los usuarios que han sido agregados o que no se van los que se borraron
	public class ActualizaJList implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				actualizaList();
			}
		}
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
						habiltaMainPanel();
						session = !session;
						txtUser.setEnabled(false);
						txtPass.setText("");
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
					deshabilitaMainPanel();
					deshabilitaPanelDetails();
					session = !session;
					txtPass.setText("");
					txtUser.setText("");
					txtUser.setEnabled(true);
					txtPass.setEnabled(true);
					btnIngresar.setText("Ingresar");
					lblStatus.setText("Esperando inciar sesión");
				}
			}
		}
	//clic en ver
		public class VerDetallesEmpleado implements ActionListener {
			public void actionPerformed(ActionEvent e) {
            double isr, base, salario, nomina, hExtra, bono, asignaciones, prestamos, deducciones;
            int diasF, dias, index;

                if (!list.isSelectionEmpty()) {// Si seleccionó a alguien
                    index = list.getSelectedIndex();
                    base = Double.parseDouble(bd[index][5]);
                    hExtra = Double.parseDouble(bd[index][7]);
                    bono = Double.parseDouble(bd[index][8]);
                    asignaciones = Double.parseDouble(bd[index][9]);
                    prestamos = Double.parseDouble(bd[index][12]);
                    deducciones = Double.parseDouble(bd[index][13]);
                    diasF = Integer.parseInt(bd[index][11]);
                    dias = Integer.parseInt(bd[index][6]);
                    salario = base*dias;
                    nomina = salario+bono+(hExtra*base)+(diasF*3*base)+asignaciones-prestamos-deducciones;
                    //Cálculo de ISR para que se vea después de dar click en ver
                    isr = calcISR(nomina,"t");

					btnReporteInd.setEnabled(true);
					btnEditar.setEnabled(true);
					btnCancelar.setEnabled(true);

					lblStatus.setText("Desplegando detalles de empleado seleccionado");
                        txtNombre.setText(bd[index][0]);// Nombre
                        txtApp.setText(bd[index][1]);// APP
                        txtApm.setText(bd[index][2]);// APM
                        txtNominaNum.setText(bd[index][3]);// No. Nómina
                        txtCargo.setText(bd[index][4]);// Cargo
                        txtSueldo.setText(bd[index][5]);// Sueldo
                        txtDiasTrabajdos.setText(bd[index][6]);// Días Trabajados
                        txtHorasExtra.setText(bd[index][7]);// Horas Extra
                        txtBonos.setText(bd[index][8]);// Bonos
                        txtAsignacionesOtros.setText(bd[index][9]);// Otras asignaciones
                        txtIVA.setText(Double.toString(ivaGlobal)+"%");
                        txtISR.setText(Double.toString(isr)+"%");
                        txtFeriados.setText(bd[index][11]);// Días feriados
                        txtPrestamos.setText(bd[index][12]);// Préstamos
                        txtDeduccionesOtros.setText(bd[index][13]);// Otras deducciones
                        txtFechaIngreso.setText(bd[index][14]);// Fecha de Ingreso
                }
				else {
					JOptionPane.showMessageDialog(null,"Por favor seleccione a alguien", "miSueldo",
					JOptionPane.WARNING_MESSAGE);
				}
            }
        }
	//clic en editar
		public class EditarEmpleado implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{

                indice = list.getSelectedIndex();
                editar = true;
				habiltaPanelDetails();
				deshabilitaMainPanel();
				btnReporteInd.setEnabled(false);
				btnEditar.setEnabled(false);
				lblStatus.setText("Modificando datos");

			}
		}
	//clic en nuevo
		public class HabilitarTexFileds implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				habiltaPanelDetails();
				limpiaTextFields();
				deshabilitaMainPanel();
				btnReporteInd.setEnabled(false);
				btnEditar.setEnabled(false);
				lblStatus.setText("Creando nuevo empleado");
			}
		}
	//clic en cancelar
		public class DeshabilitaTextFields implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				deshabilitaPanelDetails();
				habiltaMainPanel();
				lblStatus.setText("CONECTADO");
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
		public void actualizaList()
		{
			listModel.removeAllElements();
		          for (int i = 0; i < bd.length; i++) {
                      if (bd[i][0] == null) continue;
                    listModel.addElement(bd[i][0]+" - "+bd[i][3]);
                  }
                  list.setModel(listModel);
		}
		public void habiltaPanelDetails()//cuando presiona editar
		{
			txtNombre.setEditable(true);
			txtApp.setEditable(true);
			txtApm.setEditable(true);
			txtCargo.setEditable(true);
			txtSueldo.setEditable(true);
			txtNominaNum.setEditable(true);
			txtFechaIngreso.setEditable(true);
			txtDiasTrabajdos.setEditable(true);
			txtAsignacionesOtros.setEditable(true);
			txtDeduccionesOtros.setEditable(true);
			btnCancelar.setEnabled(true);
			btnGuardar.setEnabled(true);
			//btnReporteInd.setEnabled(true);
			btnEditar.setEnabled(true);
			txtBonos.setEditable(true);
			txtFeriados.setEditable(true);
			txtHorasExtra.setEditable(true);
			txtIVA.setEditable(false);
            txtIVA.setEnabled(false);
			txtISR.setEditable(false);
            txtISR.setEnabled(false);
			txtPrestamos.setEditable(true);
		}
		public void deshabilitaPanelDetails()
		{
			limpiaTextFields();
			txtNombre.setEditable(false);
			txtApp.setEditable(false);
			txtApm.setEditable(false);
			txtCargo.setEditable(false);
			txtSueldo.setEditable(false);
			txtNominaNum.setEditable(false);
			txtFechaIngreso.setEditable(false);
			txtDiasTrabajdos.setEditable(false);
			txtAsignacionesOtros.setEditable(false);
			txtDeduccionesOtros.setEditable(false);
			btnCancelar.setEnabled(false);
			btnGuardar.setEnabled(false);
			btnReporteInd.setEnabled(false);
			btnEditar.setEnabled(false);
			txtBonos.setEditable(false);
			txtFeriados.setEditable(false);
			txtHorasExtra.setEditable(false);
			txtIVA.setEditable(false);
			txtISR.setEditable(false);
			txtPrestamos.setEditable(false);
		}
		public void limpiaTextFields()
		{
			//limpiar por si hay algo
			txtNombre.setText("");
			txtApp.setText("");
			txtApm.setText("");
			txtCargo.setText("");
			txtSueldo.setText("");
			txtNominaNum.setText("");
			txtFechaIngreso.setText("");
			txtDiasTrabajdos.setText("");
			txtAsignacionesOtros.setText("");
			txtDeduccionesOtros.setText("");
			list.clearSelection();
			txtBonos.setText("");
			txtFeriados.setText("");
			txtHorasExtra.setText("");
			txtIVA.setText("");
			txtISR.setText("");
			txtPrestamos.setText("");
		}
		public void habiltaMainPanel()
		{
			btnAgregar.setEnabled(true);
			btnReportesGrales.setEnabled(true);
			btnBorrar.setEnabled(true);
			list.setEnabled(true);
			btnVer.setEnabled(true);
			lblStatus.setText("CONECTADO");
			btnActualizar.setEnabled(true);
		}
		public void deshabilitaMainPanel()
		{
			btnAgregar.setEnabled(false);
			btnReportesGrales.setEnabled(false);
			btnBorrar.setEnabled(false);
			list.setEnabled(false);
			list.clearSelection();
			btnVer.setEnabled(false);
			btnActualizar.setEnabled(false);
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
                   horasExtra = txtHorasExtra.getText(),
                   bonos = txtBonos.getText(),
				   asignaciones = txtAsignacionesOtros.getText(),
                   isr = txtISR.getText(),
                   prestamos = txtPrestamos.getText(),
				   deducciones = txtDeduccionesOtros.getText();
            String[] palabras = {nomb, app, apm, cargo,};
            boolean noError = true;

			// Texto
			if (!nomb.matches("^[a-zA-z]+[áéíóí]?(\\s[a-zA-Z][áéíóú]?|[a-zA-Z][áéíóú]?)*$")) {JOptionPane.showMessageDialog(null,"Error en dato: Nombre \n *Solo puedes usar letras \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE);noError = false;}
			if (!app.matches("^[a-zA-z]+[áéíóí]?(\\s[a-zA-Z][áéíóú]?|[a-zA-Z][áéíóú]?)*$")) {JOptionPane.showMessageDialog(null,"Error en dato: Apellido Paterno \n *Solo puedes usar letras \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
			if (!apm.matches("^[a-zA-z]+[áéíóí]?(\\s[a-zA-Z][áéíóú]?|[a-zA-Z][áéíóú]?)*$"))  {JOptionPane.showMessageDialog(null,"Error en dato: Apellido Materno \n *Solo puedes usar letras \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
			if (!cargo.matches("^\\w+[áéíóú]?(-?\\s?\\w+?)*$"))  {JOptionPane.showMessageDialog(null,"Error en dato: Cargo \n *Solo puedes usar letras \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
			// Número
            if (!fecha.matches("(\\d|[1-2][0-9]|30)\\/(\\d|[1][0-2])\\/(200[0-9]|201[0-7])")) {
                JOptionPane.showMessageDialog(null,"Error en dato: Fecha \n *Usa el formato d/m/yyyy \n *No puede estar vacío \n *No puede haber fechas futuras", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;
            }
			if (!sldo.matches("\\d+(\\.\\d+)?")) {JOptionPane.showMessageDialog(null,"Error en dato: Sueldo \n *Solo usa números \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
			if (!numNomina.matches("\\d{3}")) {JOptionPane.showMessageDialog(null,"Error en dato: No. de Nómina \n *Usa formato XXX \n *Solo usa números \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
                else if (editar) {// Verifica que la nómina no exista
                    for (int i = 0; i < bd[0].length; i++) {
                        if (indice == i) continue;
                        if (numNomina.equals(bd[i][3])) {JOptionPane.showMessageDialog(null,"No. de Nómina ocupado.","Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
					}
                }
                else {// Verifica que la nómina no exista
                    for (int i = 0; i < bd[3].length; i++) {
                        if (numNomina.equals(bd[i][3])) {JOptionPane.showMessageDialog(null,"No. de Nómina ocupado.","Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
    				}
                }
			if (!dias.matches("^([0-9]?[0-9]|[1-2][0-9][0-9]|3[0-5][0-9]|36[0-5])$")) {JOptionPane.showMessageDialog(null,"Error en dato: Días trabajados \n *Solo números \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
            if (!horasExtra.matches("^(\\d|1?[0-9]?[0-9]?[0-9]|2[0-8][0-9][0-9]|29[0-2][0-9]|293[0-6])$")) {JOptionPane.showMessageDialog(null,"Error en dato: Horas extra \n *Solo números (0-2936) \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
            if (!bonos.matches("\\d+(\\.\\d+)?")) {JOptionPane.showMessageDialog(null,"Error en dato: Bonos \n *Solo números \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
			if (!asignaciones.matches("\\d+(\\.\\d+)?")) {JOptionPane.showMessageDialog(null,"Error en dato: Asignaciones \n *Solo números \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
            if (!prestamos.matches("^\\d+(\\.\\d+)?$")) {JOptionPane.showMessageDialog(null,"Error en dato: Préstamos \n *Solo números o números decimales \n *Sin signo de porcentaje\n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}
			if (!deducciones.matches("^\\d+(\\.\\d+)?$")) {JOptionPane.showMessageDialog(null,"Error en dato: Deducciones \n *Solo números \n *No puede estar vacío", "Error al Guardar",JOptionPane.ERROR_MESSAGE); noError = false;}

            return noError;// Regresa si las validaciones fueron correctas.
        }
        public String aMayus(String texto) {
            int inicial, espacio, letra, l;

            l = texto.length();
            // Si la inicial es minúscula cambiala
            inicial = texto.codePointAt(0);
            if (inicial <= 122 && inicial >= 97) {
                texto = String.valueOf(Character.toChars(inicial - 32)) + texto.substring(1, l);
            }

            for (int i = 0; i < l; i++) {
                espacio = texto.codePointAt(i);
                if (espacio == 32) {
                        if (i == l - 1) texto = texto.substring(0, l - 1);
                        else {
                            letra = texto.codePointAt(i + 1);
                            texto = texto.substring(0, i + 1) + String.valueOf(Character.toChars(letra - 32)) + texto.substring(i + 2, l);
                        }
                    }
                }
            return texto;
            }
        public double calcISR(double sueldo, String regresaValor){
		double tasa=0;
		double cuotaFija=0;

		if (sueldo>=0.01 && sueldo<=496.07) {

			sueldo-=0.01;
			tasa=.0195;
		}else if (sueldo>=496.08 && sueldo<=4210.41) {
			sueldo -= 496.08;
			cuotaFija =9.52;
			tasa = .0640;
		}else if (sueldo>=4210.42 && sueldo<=7399.42) {
			sueldo -=4210.42;
			cuotaFija = 247.24;
			tasa = .1088;
		}else if (sueldo>=7399.43 && sueldo<=8601.50) {
			sueldo -= 7399.43;
			cuotaFija = 594.21;
			tasa = .16;
		}else if (sueldo>=8601.51 && sueldo<=10298.35) {
			sueldo -= 8601.51;
			cuotaFija = 786.54;
			tasa = .1792;
		}else if (sueldo>=10298.36 && sueldo<=20770.29) {
			sueldo -= 20298.36;
			cuotaFija = 1090.61;
			tasa = .2136;
		}else if (sueldo>=20770.30 && sueldo<=32736.83) {
			sueldo -= 20770.30;
			cuotaFija = 3327.42;
			tasa = .2352;
		}else if (sueldo>=32736.84 && sueldo<=62500.00) {
			sueldo -= 32736.84;
			cuotaFija = 6141.95;
			tasa = .30;
		}else if (sueldo>=62500.01 && sueldo<=83333.33) {
			sueldo -= 62500.01;
			cuotaFija = 15070.90;
			tasa = .32;
		}else if (sueldo>=83333.34 && sueldo<=250000.00) {
			sueldo -= 83333.34;
			cuotaFija = 21737.57;
			tasa = .34;
		}else{
			sueldo -= 250000.01;
			cuotaFija = 78404.23;
			tasa = .35;
		}
		sueldo-=(tasa*sueldo);
		sueldo+=cuotaFija;
        if (regresaValor.equals("s")) return sueldo;
        else if (regresaValor.equals("t")) return tasa*100;
        else return sueldo;
	}
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~FIN MÉTODOS AUXILIARES~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~MÉTODOS PARA BASE DE DATOS~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	public class AgregaraBD implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			boolean noError = validacion();

            if (noError && !editar) {
                for (int i = 0; i < bd.length; i++) {
				if (bd[i][0] == null) { // Si el espacio no esta asignado
					System.out.println("Si entro");
					// Asignar todos los valores colocados a esa nómina
					bd[i][0] = aMayus(txtNombre.getText());
					bd[i][1] = aMayus(txtApp.getText());
					bd[i][2] = aMayus(txtApm.getText());
					bd[i][3] = txtNominaNum.getText();
					bd[i][4] = aMayus(txtCargo.getText());
					bd[i][5] = txtSueldo.getText();
                    bd[i][6] = txtDiasTrabajdos.getText();
                    bd[i][7] = txtHorasExtra.getText();
                    bd[i][8] = txtBonos.getText();
                    bd[i][9] = txtAsignacionesOtros.getText();
                    bd[i][10] = Double.toString(ivaGlobal);
                    bd[i][11] = txtFeriados.getText();
                    bd[i][12] = txtPrestamos.getText();
                    bd[i][13] = txtDeduccionesOtros.getText();
					bd[i][14] = txtFechaIngreso.getText();
					deshabilitaPanelDetails();
					habiltaMainPanel();
					limpiaTextFields();
					JOptionPane.showMessageDialog(null,bd[i][0] + " " + bd[i][1] + " " + bd[i][2] + "\nRegistrado con nómina: " + bd[i][3]);
					break;
					}
				}
                escritorCSV();
				actualizaList();
			}
            else if (noError && editar) {
                bd[indice][0] = aMayus(txtNombre.getText());
                bd[indice][1] = aMayus(txtApp.getText());
                bd[indice][2] = aMayus(txtApm.getText());
                bd[indice][3] = txtNominaNum.getText();
                bd[indice][4] = aMayus(txtCargo.getText());
                bd[indice][5] = txtSueldo.getText();
                bd[indice][6] = txtDiasTrabajdos.getText();
                bd[indice][7] = txtHorasExtra.getText();
                bd[indice][8] = txtBonos.getText();
                bd[indice][9] = txtAsignacionesOtros.getText();
                bd[indice][10] = Double.toString(ivaGlobal);
                bd[indice][11] = txtFeriados.getText();
                bd[indice][12] = txtPrestamos.getText();
                bd[indice][13] = txtDeduccionesOtros.getText();
                bd[indice][14] = txtFechaIngreso.getText();
                deshabilitaPanelDetails();
                habiltaMainPanel();
                limpiaTextFields();
                escritorCSV();
				actualizaList();
                JOptionPane.showMessageDialog(null,bd[indice][0] + " " + bd[indice][1] + " " + bd[indice][2] + "\nNómina editada: " + bd[indice][3]);
            }
		}
	}


	public class LectorCSV implements ActionListener { // Para que se active cuando haya una acción
		public void actionPerformed(ActionEvent event) {
			int contador = 0;
			BufferedReader br = null;
			String linea = "",
			separador = ",",
			command = event.getActionCommand(),
			csv = System.getProperty("user.dir")+"/bd.csv"; // El archivo que se quiera utilizar como base de datos siempre se debe imprimir
			File archivo = new File(csv);
                try {
					if (!archivo.exists()) archivo.createNewFile();
					br = new BufferedReader(new FileReader(archivo));
					while ((linea = br.readLine()) != null) { // Mientras el archivo tenga valores
                        String[] perfil = linea.split(separador); // Crear un arreglo con los valores de la línea
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
                                bd[i][10] = Double.toString(ivaGlobal);
                                bd[i][11] = perfil[11];
                                bd[i][12] = perfil[12];
                                bd[i][13] = perfil[13];
                                bd[i][14] = perfil[14];
								contador++;
								break;
							}
						}
					}
				} catch (FileNotFoundException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException ex){
					System.out.println("bd vacía LectorCSV");
				} finally {
					if (br != null) {
						try {
							br.close();
						} catch (IOException e) {
                            e.printStackTrace();
                            }
                    }
				}
                actualizaList();
            }
        }
    public class BorrardeBD implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int index;
            if (!list.isSelectionEmpty()) {
                index = list.getSelectedIndex();
                for (int i = 0; i < bd[0].length; i++) {
                    bd[index][i] = null;
                }
                escritorCSV();
                actualizaList();
            }
            else {
                JOptionPane.showMessageDialog(null,"Por favor seleccione a alguien", "miSueldo",
                JOptionPane.WARNING_MESSAGE);
            }
        }
    }
	public class GenerarReportes implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            String d = "", os = "";
			BufferedWriter be = null;
			String perfiles = "";
            int dias;
			double base, salario, hExtra, diasF, bono, asignaciones, prestamos ,deducciones, nomina, iva , isr;



			d = System.getProperty("user.dir") + "/reporte.csv";
            os = System.getProperty("os.name");

			try {
					File reporte = new File(d); // Especifica el nombre del archivo para el reporte
					if (reporte.exists()) reporte.delete();
					reporte.createNewFile(); //Crea el archivo del reporte
					be = new BufferedWriter(new FileWriter(reporte));
					for (int i = -1; i < bd.length; i++) {
						if (i == -1) { // Escribe los títulos de columna para el reporte
							be.append("Nombre,");
							be.append("Apellido Paterno,");
							be.append("Apellido Materno,");
							be.append("No. Nomina,");
							be.append("Cargo,");
							be.append("Salario,");
							be.append("Dias Trabajados,");
							be.append("Horas Extra,");
                            be.append("Bonos,");
                            be.append("Otras Asignaciones,");
							be.append("IVA (%),");
                            be.append("Días Feriados,");
                            be.append("Prestamos,");
                            be.append("Otras Deducciones,");
							be.append("Fecha de Ingreso,");
                            be.append("Salario,");
                            be.append("Nomina bruta,");
                            be.append("IVA aplicado,");
                            be.append("ISR aplicado,");
                            be.append("Nomina neta\n");
							continue;
						}

						// Si la nómina no está asignada continúa
						if (bd[i][0] == null) continue;
                        // Asigna los valores a las variables
                        base = Double.parseDouble(bd[i][5]);
                        dias = Integer.parseInt(bd[i][6]);
                        hExtra = Double.parseDouble(bd[i][7]);
                        bono = Double.parseDouble(bd[i][8]);
                        asignaciones = Double.parseDouble(bd[i][9]);
                        diasF = Integer.parseInt(bd[i][11]);
                        prestamos = Double.parseDouble(bd[i][12]);
                        deducciones = Double.parseDouble(bd[i][13]);

                        // Escribe al reporte
						be.append(bd[i][0]+",");// Nombre
						be.append(bd[i][1]+",");// APP
						be.append(bd[i][2]+",");// APM
						be.append(bd[i][3]+",");// No. Nómina
						be.append(bd[i][4]+",");// Cargo
						be.append(bd[i][5]+",");// Sueldo
						be.append(bd[i][6]+",");// Días Trabajados
						be.append(bd[i][7]+",");// Horas Extra
						be.append(bd[i][8]+",");// Bonos
						be.append(bd[i][9]+",");// Otras asignaciones
                        be.append(bd[i][10]+",");// IVA
                        be.append(bd[i][11]+",");// Días feriados
                        be.append(bd[i][12]+",");// Préstamos
                        be.append(bd[i][13]+",");// Otras deducciones
                        be.append(bd[i][14]+",");// Fecha de Ingreso
						//Cálculo de nómina
                        salario = base*dias;
                        be.append(salario+",");
                        nomina = salario+bono+(hExtra*base)+(diasF*3*base)+asignaciones-prestamos-deducciones;
                        be.append(nomina+",");
                        iva = (nomina*ivaGlobal/100);
                        be.append(iva+",");
                        isr = calcISR(nomina,"s");
                        be.append(isr+",");
                        nomina -= isr+iva;
                        be.append(nomina+"\n");
					}
                    if (os.equals("Mac OS X")) Runtime.getRuntime().exec(new String[]{"open",d});
                    if (os.equals("Linux")) Runtime.getRuntime().exec(new String[] {"xdg-open",d});
                    if (os.equals("Windows")) Runtime.getRuntime().exec(new String[] {d});
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
        public class GenerarReporte implements ActionListener {
    		public void actionPerformed(ActionEvent event) {
                String d = "", os = "";
    			BufferedWriter be = null;
    			String perfiles = "";
                int dias, index;
    			double base, salario, hExtra, diasF, bono, asignaciones, prestamos ,deducciones, nomina, iva , isr;




                os = System.getProperty("os.name");
                index = list.getSelectedIndex();
                d = System.getProperty("user.dir") + "/reporte"+bd[index][1]+".csv";
                // Asigna los valores a las variables
                base = Double.parseDouble(bd[index][5]);
                dias = Integer.parseInt(bd[index][6]);
                hExtra = Double.parseDouble(bd[index][7]);
                bono = Double.parseDouble(bd[index][8]);
                asignaciones = Double.parseDouble(bd[index][9]);
                diasF = Integer.parseInt(bd[index][11]);
                prestamos = Double.parseDouble(bd[index][12]);
                deducciones = Double.parseDouble(bd[index][13]);
                salario = base*dias;
                nomina = salario+bono+(hExtra*base)+(diasF*3*base)+asignaciones-prestamos-deducciones;
                iva = (nomina*ivaGlobal/100);
                isr = calcISR(nomina,"s");

    			try {
    					File reporte = new File(d); // Especifica el nombre del archivo para el reporte
    					if (reporte.exists()) reporte.delete();
    					reporte.createNewFile(); //Crea el archivo del reporte
    					be = new BufferedWriter(new FileWriter(reporte));

    							be.append("Nombre,"+bd[index][0]+"\n");
    							be.append("Apellido Paterno,"+bd[index][1]+"\n");
    							be.append("Apellido Materno,"+bd[index][2]+"\n");
    							be.append("No. Nomina,"+bd[index][3]+"\n");
    							be.append("Cargo,"+bd[index][4]+"\n");
    							be.append("Salario,"+bd[index][5]+"\n");
    							be.append("Dias Trabajados,"+bd[index][6]+"\n");
    							be.append("Horas Extra,"+bd[index][7]+"\n");
                                be.append("Bonos,"+bd[index][8]+"\n");
                                be.append("Otras Asignaciones,"+bd[index][9]+"\n");
    							be.append("IVA (%),"+bd[index][10]+"\n");
                                be.append("Días Feriados,"+bd[index][11]+"\n");
                                be.append("Prestamos,"+bd[index][12]+"\n");
                                be.append("Otras Deducciones,"+bd[index][13]+"\n");
    							be.append("Fecha de Ingreso,"+bd[index][14]+"\n");
                                be.append("Salario,"+salario+"\n");

                                be.append("Nomina bruta,"+nomina+"\n");
                                be.append("IVA aplicado,"+iva+"\n");
                                be.append("ISR aplicado,"+isr+"\n");
                                nomina -= isr+iva;
                                be.append("Nomina neta,"+nomina+"\n");

                        if (os.equals("Mac OS X")) Runtime.getRuntime().exec(new String[]{"open",d});
                        if (os.equals("Linux")) Runtime.getRuntime().exec(new String[] {"xdg-open",d});
                        if (os.equals("Windows")) Runtime.getRuntime().exec(new String[] {d});
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
		public void escritorCSV() {
			BufferedWriter bw = null;
            String d = "";

            try {
                d = System.getProperty("user.dir") + "/bd.csv";
                File archivoCSV = new File(d);
                bw = new BufferedWriter(new FileWriter(archivoCSV));
                for (int i = 0; i < bd.length; i++) {
                    if (bd[i][0] == null) continue;

        			bw.append(bd[i][0] + ",");
        			bw.append(bd[i][1] + ",");
        			bw.append(bd[i][2] + ",");
        			bw.append(bd[i][3] + ",");
        			bw.append(bd[i][4] + ",");
    				bw.append(bd[i][5] + ",");
        			bw.append(bd[i][6] + ",");
        			bw.append(bd[i][7] + ",");
        			bw.append(bd[i][8] + ",");
                    bw.append(bd[i][9] + ",");
                    bw.append(bd[i][10] + ",");
                    bw.append(bd[i][11] + ",");
                    bw.append(bd[i][12] + ",");
                    bw.append(bd[i][13] + ",");
        			bw.append(bd[i][14] + "\n");
                }
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
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~FIN MÉTODOS BD~~~~~~~~~~~~~~~~~
	~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
}
