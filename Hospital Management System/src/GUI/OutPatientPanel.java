import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

public class OutPatientPanel extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtIssue;
	private JTextField txtID;
	private JButton btnDignose;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnBack;
	private JLabel lblbg;

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
					OutPatientPanel frame1 = new OutPatientPanel();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OutPatientPanel()
	{
		setTitle("Out-Patient");
		setBounds(new Rectangle(0, 0, 600, 400));
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(OutPatientPanel.class.getResource("/GUI/hospital (1).png")));
		
		initcomponents();
		createevents();
		
		
	}

	private void initcomponents() 
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 607, 409);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("First Name :");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(13, 11, 85, 20);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(153, 12, 431, 20);
		txtFirstName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Last Name :");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setBounds(15, 52, 83, 20);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtLastName = new JTextField();
		txtLastName.setBounds(153, 53, 431, 20);
		txtLastName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("CNIC         :");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBounds(15, 100, 83, 20);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		JLabel lblIssue = new JLabel("Issue           :");
		lblIssue.setForeground(Color.BLACK);
		lblIssue.setBounds(15, 146, 128, 20);
		lblIssue.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtIssue = new JTextField();
		txtIssue.setBounds(153, 147, 431, 20);
		txtIssue.setColumns(10);
		
		txtID = new JTextField();
		txtID.setBounds(153, 101, 431, 20);
		txtID.setColumns(10);
		
		btnDignose = new JButton("Diagnose");
		btnDignose.setBackground(Color.WHITE);
		btnDignose.setBounds(488, 331, 96, 29);
		btnDignose.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(15, 331, 67, 29);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(btnBack);
		contentPane.add(btnDignose);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(lblNewLabel_3);
		contentPane.add(lblIssue);
		contentPane.add(txtID);
		contentPane.add(txtIssue);
		contentPane.add(txtLastName);
		contentPane.add(txtFirstName);
		
		lblbg = new JLabel("");
		lblbg.setIcon(new ImageIcon(OutPatientPanel.class.getResource("/GUI/outpatientbg.jpg")));
		lblbg.setBounds(0, 0, 601, 380);
		contentPane.add(lblbg);
		
	}

	private void createevents() 
	{
		
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Welcome w = new Welcome();
				dispose();
				w.setVisible(true);
				
			}
		});
		
		
		btnDignose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(txtFirstName.getText().equals("") || txtLastName.getText().equals("") || txtIssue.getText().equals("") || txtID.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Fill all the Fields First");
				}
				else
				{
					try
					{
						String fn,ln,d,id;
						fn = txtFirstName.getText();
						ln = txtLastName.getText();
						d = txtIssue.getText();
						id = txtID.getText();
						Integer.parseInt(id);
						Patient p = new outPatient(fn,ln,d,id);
						if(Welcome.hospital.addPatient(p))
						{
							try 
							{
								Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
								con = DriverManager.getConnection("jdbc:ucanaccess://Hospital.accdb");
								
								pst = con.prepareStatement("insert into outpatients(FirstName,LastName,Disease,CNIC) values (?,?,?,?)");
								pst.setString(1, fn);
								pst.setString(2, ln);
								pst.setString(3, d);
								pst.setString(4, id);
								pst.executeUpdate();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
						txtFirstName.setText("");
						txtLastName.setText("");
						txtIssue.setText("");
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
