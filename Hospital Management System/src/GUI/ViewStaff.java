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
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ListModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class ViewStaff extends JFrame {

	private JPanel contentPane;
	//Hospital hospital;
	private DefaultListModel doctorsList = new DefaultListModel();
	private DefaultListModel NurseList = new DefaultListModel();
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JButton btnshowdoc;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_3;
	private JTextField txtQual;
	private JButton btnsearchdoc;
	private JButton btnBack;
	private JLabel label;
	private JLabel lblNursesQualification;
	private JTextField txtnurseID;
	private JButton btnsearchnurse;
	private JButton btnshownurse;
	private JLabel lblNewLabel;
	private JList listdoc;
	private JList listnurse;

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
					ViewStaff frame = new ViewStaff();
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
	public ViewStaff() 
	{
		setTitle("Staff");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewStaff.class.getResource("/GUI/hospital (1).png")));
		initcomponents();
		createevents();
	}

	private void initcomponents()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 994, 673);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		scrollPane.setBounds(187, 36, 278, 544);
		
		btnshowdoc = new JButton("Show All");
		btnshowdoc.setBounds(187, 591, 87, 25);
		btnshowdoc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lblNewLabel_1 = new JLabel("Search :-");
		lblNewLabel_1.setBounds(5, 16, 67, 17);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel_3 = new JLabel("Doctor's Qualification :-");
		lblNewLabel_3.setBounds(15, 51, 162, 17);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		txtQual = new JTextField();
		txtQual.setBackground(SystemColor.control);
		txtQual.setBounds(15, 74, 124, 20);
		txtQual.setColumns(10);
		
		btnsearchdoc = new JButton("Search");
		btnsearchdoc.setBounds(15, 112, 73, 25);
		btnsearchdoc.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		btnBack = new JButton("Back");
		btnBack.setBounds(15, 591, 63, 25);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		label = new JLabel("Search :-");
		label.setBounds(539, 16, 73, 17);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		lblNursesQualification = new JLabel("Nurse's ID:-");
		lblNursesQualification.setBounds(539, 51, 112, 17);
		lblNursesQualification.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		txtnurseID = new JTextField();
		txtnurseID.setBackground(SystemColor.control);
		txtnurseID.setBounds(539, 74, 124, 20);
		txtnurseID.setColumns(10);
		
		btnsearchnurse = new JButton("Search");
		btnsearchnurse.setBounds(539, 112, 73, 25);
		btnsearchnurse.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		
		btnshownurse = new JButton("Show All");
		btnshownurse.setBounds(685, 591, 87, 25);
		btnshownurse.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setOpaque(false);
		scrollPane_1.setBounds(685, 36, 278, 544);
		contentPane.setLayout(null);
		contentPane.add(lblNewLabel_3);
		contentPane.add(btnsearchdoc);
		contentPane.add(txtQual);
		contentPane.add(btnBack);
		contentPane.add(lblNewLabel_1);
		contentPane.add(btnshowdoc);
		contentPane.add(scrollPane);
		
		listdoc = new JList(doctorsList);
		listdoc.setForeground(new Color(51, 51, 51));
		listdoc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		listdoc.setBackground(SystemColor.control);
		listdoc.setOpaque(false);
		scrollPane.setViewportView(listdoc);
		//scrollPane.getViewport().setOpaque(false);
		contentPane.add(label);
		contentPane.add(lblNursesQualification);
		contentPane.add(txtnurseID);
		contentPane.add(btnsearchnurse);
		contentPane.add(scrollPane_1);
		
		listnurse = new JList(NurseList);
		listnurse.setForeground(new Color(51, 51, 51));
		listnurse.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		listnurse.setBackground(SystemColor.control);
		listnurse.setOpaque(false);
		scrollPane_1.setViewportView(listnurse);
		//scrollPane_1.getViewport().setOpaque(false);
		contentPane.add(btnshownurse);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewStaff.class.getResource("/GUI/staffbg.jpg")));
		lblNewLabel.setBounds(0, 0, 988, 644);
		contentPane.add(lblNewLabel);
		
	}

	private void createevents() 
	{
		
		btnshownurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				NurseList.clear();
				for(int i =0 ;i<Welcome.hospital.NL.nurses.length ; i++)
				{
					Nurse temp = Welcome.hospital.NL.nurses[i];
					if(temp!=null)
					{
						NurseList.addElement(temp.toString());
					}
				}
			}
		});
		
		btnsearchnurse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Nurse n;
				NurseList.clear();
				if(txtnurseID.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please Enter an ID");
				}
				else
				{
					try
					{
						n = Welcome.hospital.searchNurse(Integer.parseInt(txtnurseID.getText()));
						if(n!=null) NurseList.addElement(n.toString());
						
					}catch(NumberFormatException e1)
					{
						JOptionPane.showMessageDialog(null, "Invalid Input");
					}
						

				}
			}
		});
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Admin a = new Admin();
				dispose();
				a.setVisible(true);
			}
		});
		
		btnsearchdoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Doctor doc;
				doctorsList.clear();
				if(txtQual.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill one of the Fields First");
				}
				else
				{
					
						doc = Welcome.hospital.searchDoctor(txtQual.getText().toLowerCase());
						if(doc==null)
							JOptionPane.showMessageDialog(null, "No such Doctor exists");
						else
						doctorsList.addElement(doc.toString());
				}
			}
		});
		
		btnshowdoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				doctorsList.clear();
				for(int i =0 ;i<Welcome.hospital.DL.doctors.length ; i++)
				{
					Doctor temp = Welcome.hospital.DL.doctors[i];
					if(temp!=null)
					{
						doctorsList.addElement(temp.toString());
					}
				}
			}
		});
		
	}
}

