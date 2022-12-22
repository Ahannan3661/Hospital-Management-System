package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class AddNurse extends JFrame {

	private JPanel contentPane;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtID;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JButton btnAddNurse;
	private JButton btnBack;
	private JLabel lblNewLabel;

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
					AddNurse frame = new AddNurse();
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
	public AddNurse() 
	{
		setTitle("Nurse");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddNurse.class.getResource("/GUI/hospital (1).png")));
		initcomponents();
		createevents();
	}

	private void initcomponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700,315);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label_1 = new JLabel("CNIC:");
		label_1.setForeground(SystemColor.desktop);
		label_1.setBounds(143, 131, 92, 20);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtID = new JTextField();
		txtID.setBackground(SystemColor.control);
		txtID.setBounds(237, 128, 310, 20);
		txtID.setColumns(10);
		
		JLabel label_2 = new JLabel("First Name:");
		label_2.setForeground(SystemColor.control);
		label_2.setBounds(143, 34, 92, 20);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtFirstName = new JTextField();
		txtFirstName.setBackground(SystemColor.control);
		txtFirstName.setBounds(237, 31, 310, 20);
		txtFirstName.setColumns(10);
		
		JLabel label_3 = new JLabel("Last Name :");
		label_3.setForeground(SystemColor.control);
		label_3.setBounds(143, 77, 92, 20);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtLastName = new JTextField();
		txtLastName.setBackground(SystemColor.control);
		txtLastName.setBounds(237, 78, 310, 20);
		txtLastName.setColumns(10);
		
		btnAddNurse = new JButton("Add");
		btnAddNurse.setBounds(451, 216, 96, 29);
		btnAddNurse.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(143, 216, 67, 29);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(btnBack);
		contentPane.add(btnAddNurse);
		contentPane.add(label_2);
		contentPane.add(txtFirstName);
		contentPane.add(label_1);
		contentPane.add(label_3);
		contentPane.add(txtLastName);
		contentPane.add(txtID);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(AddNurse.class.getResource("/GUI/nursebg.jpg")));
		lblNewLabel.setBounds(0, 0, 694, 286);
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
		
		
		btnAddNurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtID.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Fill all the Fields First");
				}
				else
				{
					try
					{
						String fn,ln,q,id;
						fn = txtFirstName.getText();
						ln = txtLastName.getText();
						id = txtID.getText();
						Integer.parseInt(id);
						Nurse d = new Nurse(fn,ln,id);
						Welcome.hospital.addNurse(d);
						
						try 
						{
							Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
							con = DriverManager.getConnection("jdbc:ucanaccess://Hospital.accdb");
							
							pst = con.prepareStatement("insert into nurses(FirstName,LastName,CNIC) values (?,?,?)");
							pst.setString(1, txtFirstName.getText());
							pst.setString(2, txtLastName.getText());
							pst.setString(3, txtID.getText());
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