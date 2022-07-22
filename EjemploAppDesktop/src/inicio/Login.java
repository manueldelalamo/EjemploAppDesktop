package inicio;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private int fallos = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(22, 34, 103, 50);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(141, 44, 251, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contraseña");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(23, 124, 102, 34);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(141, 124, 251, 30);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					//Class.forName("com.mysql.cj.jdbc.Driver");
					String url = "jdbc:mysql://localhost:3306/indra22";
					Connection con;
					
					con = DriverManager.getConnection(url, "root", "curso");
					
					String usuario = textField.getText();
					String contraseña = passwordField.getText();
					
					
					Statement stm = con.createStatement();
					String sql = "";
					try {
						sql = "select * from empleados where nombre='"+usuario
								+"' and contraseña='"+ TestCripto.encriptar(contraseña)+"'";
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ResultSet rs = stm.executeQuery(sql);
					
					if (rs.next()) {
						dispose();
						JOptionPane.showInputDialog(this, "Login completado con éxito");
					} else {
						fallos++;
						JOptionPane.showInputDialog(this, "Usuario o contraseña incorrectos ");
						textField.setText("");
						passwordField.setText("");
						if (fallos>=3) {
							throw new IntentoException("Reinicia el programa para poder continuar");
							//IntentoException intentoException = new IntentoException("Reinicia el programa para poder continuar");
							
						}
						
					}
					
					
				} catch (SQLException e2) {
					// TODO: handle exception
					JOptionPane.showInputDialog("Error de conexión");
				} catch (IntentoException e3) {
					// TODO: handle exception
					System.err.println(e3.getMessage());
					System.exit(1);
				}
				
				
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(54, 195, 118, 34);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(264, 195, 118, 34);
		contentPane.add(btnNewButton_1);
	}
}
