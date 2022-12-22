package GUI;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.ImageIcon;
public class AddDoctors extends JFrame {

	private JPanel contentPane;
//	Hospital hospital;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtQual;
	private JTextField txtID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton btnAddDoc;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddDoctors frame = new AddDoctors();
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
	public AddDoctors() 
	{
		setTitle("Doctor");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddDoctors.class.getResource("/GUI/hospital (1).png")));
		initcomponents();
		createevents();
//		MakeHospital();
	}

	private void initcomponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 417);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblQualification = new JLabel("Qualification:");
		lblQualification.setBounds(15, 221, 207, 20);
		lblQualification.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtQual = new JTextField();
		txtQual.setBounds(226, 221, 369, 20);
		txtQual.setForeground(Color.BLACK);
		txtQual.setBackground(Color.WHITE);
		txtQual.setColumns(10);
		
		JLabel label_1 = new JLabel("CNIC:");
		label_1.setBounds(15, 183, 207, 20);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtID = new JTextField();
		txtID.setBounds(226, 180, 369, 20);
		txtID.setColumns(10);
		
		JLabel label_2 = new JLabel("First Name:");
		label_2.setBounds(15, 47, 201, 20);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(226, 48, 369, 20);
		txtFirstName.setColumns(10);
		
		JLabel label_3 = new JLabel("Last Name :");
		label_3.setBounds(15, 86, 201, 20);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtLastName = new JTextField();
		txtLastName.setBounds(226, 86, 369, 20);
		txtLastName.setColumns(10);
		
		btnAddDoc = new JButton("Add");
		btnAddDoc.setBounds(504, 338, 96, 29);
		btnAddDoc.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(15, 338, 67, 29);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(btnBack);
		contentPane.add(btnAddDoc);
		contentPane.add(label_1);
		contentPane.add(lblQualification);
		contentPane.add(label_3);
		contentPane.add(label_2);
		contentPane.add(txtFirstName);
		contentPane.add(txtLastName);
		contentPane.add(txtID);
		contentPane.add(txtQual);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddDoctors.class.getResource("/GUI/doctorbg.jpg")));
		lblNewLabel.setBounds(0, 0, 620, 388);
		contentPane.add(lblNewLabel);
		
	}

	private void createevents()
	{
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Admin a = new Admin();
				dispose();
				a.setVisible(true);
						
			}
		});
		
		
		
		
		
		
		btnAddDoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if(txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtQual.getText().equals("") || txtID.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Fill all the Fields First");
				}
				else
				{
					
					String fn,ln,q,id;
					try
					{
						
						fn = txtFirstName.getText();
						ln = txtLastName.getText();
						q = txtQual.getText();
						id = txtID.getText();
						
						Integer.parseInt(id);
						Doctor d = new Doctor(fn,ln,id,q);
						Welcome.hospital.addDoctor(d);
						
						try 
						{
							Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
							con = DriverManager.getConnection("jdbc:ucanaccess://Hospital.accdb");
							
							pst = con.prepareStatement("insert into doctors(FirstName,LastName,Qualification,CNIC) values (?,?,?,?)");
							pst.setString(1, txtFirstName.getText());
							pst.setString(2, txtLastName.getText());
							pst.setString(3, txtQual.getText());
							pst.setString(4, txtID.getText());
							pst.executeUpdate();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						txtFirstName.setText("");
						txtLastName.setText("");
						txtQual.setText("");
						txtID.setText("");
					}
					catch(NumberFormatException e4)
					{
						JOptionPane.showMessageDialog(null,"CNIC can NOT contain characters");
					}
					
				}
				
			}
		});
		
	}
}
