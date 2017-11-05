public class Principal
{
	public static void main(String[] args)
	{
		Interfaz ventanaGrafica = new Interfaz();
		ventanaGrafica.setVisible(true);	
	}
}
/*
		// Panel de Log In
			panelLogIn = new JPanel();
			panelLogIn.setBackground(new java.awt.Color(187, 72, 72));
			panelLogIn.setLayout(new java.awt.BorderLayout());//java.awt.GridLayout(3,1,0,200));
				panelLogInAux = new JPanel();
				panelLogIn.add(panelLogInAux,java.awt.BorderLayout.NORTH);
				panelLogInAux.add(new JLabel("Log in"));
				panelLogInContent = new JPanel();
				panelLogIn.add(panelLogInContent,java.awt.BorderLayout.CENTER);
				panelLogInContent.add(new JLabel("Usuario"));
				txtUser = new JTextField(10);
				panelLogInContent.add(txtUser);
				panelLogInContent.add(new JLabel("Contraseña"));
				txtPass = new JTextField(10);
				panelLogInContent.add(txtPass);
			btnIngresar = new JButton("Ingresar");
			panelLogIn.add(btnIngresar,java.awt.BorderLayout.SOUTH);

		//panel Area Principal
		*/