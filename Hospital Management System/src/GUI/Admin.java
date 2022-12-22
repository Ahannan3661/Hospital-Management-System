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
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class Admin extends JFrame {

	private JPanel contentPane;
	Hospital hospital;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtAddDoc;
	private JTextField txtAddNurse;
	private JTextField txtViewStaff;
	private JButton btnBack;
	private JTextField txtViewPatients;
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
					Admin frame = new Admin();
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
	public Admin() 
	{
		setResizable(false);
		setTitle("Admin");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Admin.class.getResource("/GUI/hospital (1).png")));
		initcomponents();
		createevents();
	}

	private void initcomponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 350);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtAddDoc = new JTextField();
		txtAddDoc.setBounds(130, 143, 111, 65);
		txtAddDoc.setForeground(Color.WHITE);
		
		txtAddDoc.setText("Add Doctor");
		txtAddDoc.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddDoc.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtAddDoc.setEditable(false);
		txtAddDoc.setColumns(10);
		txtAddDoc.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtAddDoc.setBackground(SystemColor.desktop);
		
		txtAddNurse = new JTextField();
		txtAddNurse.setBounds(512, 143, 104, 65);
		txtAddNurse.setForeground(Color.WHITE);
		txtAddNurse.setText("Add Nurse");
		txtAddNurse.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddNurse.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtAddNurse.setEditable(false);
		txtAddNurse.setColumns(10);
		txtAddNurse.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtAddNurse.setBackground(SystemColor.desktop);
		
		txtViewStaff = new JTextField();
		txtViewStaff.setBounds(130, 33, 111, 65);
		txtViewStaff.setForeground(Color.WHITE);
		
		txtViewStaff.setText("View Staff");
		txtViewStaff.setHorizontalAlignment(SwingConstants.CENTER);
		txtViewStaff.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtViewStaff.setEditable(false);
		txtViewStaff.setColumns(10);
		txtViewStaff.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtViewStaff.setBackground(SystemColor.desktop);
		
		btnBack = new JButton("Back");
		btnBack.setBounds(15, 277, 67, 29);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 16));
		
		txtViewPatients = new JTextField();
		txtViewPatients.setBounds(512, 33, 104, 65);
		txtViewPatients.setForeground(Color.WHITE);
		
		txtViewPatients.setText("View Patients");
		txtViewPatients.setHorizontalAlignment(SwingConstants.CENTER);
		txtViewPatients.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtViewPatients.setEditable(false);
		txtViewPatients.setColumns(10);
		txtViewPatients.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		txtViewPatients.setBackground(SystemColor.desktop);
		contentPane.setLayout(null);
		contentPane.add(btnBack);
		contentPane.add(txtViewStaff);
		contentPane.add(txtAddDoc);
		contentPane.add(txtAddNurse);
		contentPane.add(txtViewPatients);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Admin.class.getResource("/GUI/adminbg.jpg")));
		lblNewLabel.setBounds(0, 0, 744, 321);
		contentPane.add(lblNewLabel);
		
	}

	private void createevents() 
	{
		
		txtViewPatients.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ViewPatients vp = new ViewPatients();
				dispose();
				vp.setVisible(true);
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Welcome w = new Welcome();
				dispose();
				w.setVisible(true);
			}
		});
		
		
		txtViewStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ViewStaff vd = new ViewStaff();
				dispose();
				vd.setVisible(true);
			}
		});
		
		
		txtAddDoc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				AddDoctors ad = new AddDoctors();
				dispose();
				ad.setVisible(true);
			}
		});
		
		txtAddNurse.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				AddNurse an = new AddNurse();
				dispose();
				an.setVisible(true);
			}
		});
		
	}
}
