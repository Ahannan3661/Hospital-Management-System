package GUI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.ImageIcon;

public class InPatientPanel extends JFrame {

	private JPanel contentPane;
	private JTextField txtFirstName;
	private JTextField txtID;
	private JTextField txtLastName;
	private JTextField txtIssue;
	private JButton btndiagnose;
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
					InPatientPanel frame = new InPatientPanel();
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
	public InPatientPanel() 
	{
		setTitle("In-Patient");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(InPatientPanel.class.getResource("/GUI/hospital (1).png")));
		initcomponents();
		createevents();
	}

	private void initcomponents()
	{
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 368);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btndiagnose = new JButton("Diagnose");
		btndiagnose.setBounds(578, 297, 96, 29);
		btndiagnose.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		
		JLabel label = new JLabel("First Name:");
		label.setForeground(Color.WHITE);
		label.setBounds(15, 14, 122, 20);
		label.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		txtFirstName = new JTextField();
		txtFirstName.setBounds(150, 16, 524, 20);
		txtFirstName.setColumns(10);
		
		JLabel label_1 = new JLabel("CNIC:");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(15, 129, 125, 20);
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtID = new JTextField();
		txtID.setBounds(150, 130, 524, 20);
		txtID.setColumns(10);
		
		JLabel label_2 = new JLabel("Last Name :");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(15, 57, 122, 20);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtLastName = new JTextField();
		txtLastName.setBounds(150, 58, 524, 20);
		txtLastName.setColumns(10);
		
		JLabel label_3 = new JLabel("Issue         :");
		label_3.setForeground(Color.WHITE);
		label_3.setBounds(15, 170, 125, 20);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtIssue = new JTextField();
		txtIssue.setBounds(150, 171, 524, 20);
		txtIssue.setColumns(10);
		contentPane.setLayout(null);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(15, 297, 67, 29);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		contentPane.add(btnBack);
		contentPane.add(btndiagnose);
		contentPane.add(label_1);
		contentPane.add(label_3);
		contentPane.add(txtIssue);
		contentPane.add(txtID);
		contentPane.add(label);
		contentPane.add(label_2);
		contentPane.add(txtFirstName);
		contentPane.add(txtLastName);
		
		lblbg = new JLabel("");
		lblbg.setIcon(new ImageIcon(InPatientPanel.class.getResource("/GUI/inpatientbg.jpg")));
		lblbg.setBounds(0, 0, 694, 339);
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
		
		btndiagnose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
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
						Patient p = new inPatient(fn,ln,d,id);
						
						if(Welcome.hospital.addPatient(p))
						{
							try 
							{
								Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
								con = DriverManager.getConnection("jdbc:ucanaccess://Hospital.accdb");
								
								pst = con.prepareStatement("insert into inpatients(FirstName,LastName,Disease,CNIC,RoomNo) values (?,?,?,?,?)");
								pst.setString(1, fn);
								pst.setString(2, ln);
								pst.setString(3, d);
								pst.setString(4, id);
								pst.setInt(5, Integer.parseInt(((inPatient) p).roomNo));
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
