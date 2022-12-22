package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class ViewPatients extends JFrame {

	private JPanel contentPane;
	private JTextField txtIDin;
	private JButton btnSearchIn;
	private JButton btnShowIn;
	private JTextField txtIDout;
	private JButton btnSearchOut;
	private JButton btnShowOut;
	DefaultListModel lstinpatient = new DefaultListModel();
	DefaultListModel lstoutpatient = new DefaultListModel();
	private JButton btnback;
	private JScrollPane scrollPane;
	private JList lstOut;
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
					ViewPatients frame = new ViewPatients();
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
	public ViewPatients() 
	{
		setTitle("Patients");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewPatients.class.getResource("/GUI/hospital (1).png")));
		initcomponents();
		createevents();
		
		
	}

	private void initcomponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Checked Out Patients:-");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(371, 16, 143, 17);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JLabel lblCheckedInPatients = new JLabel("Checked In Patients:-");
		lblCheckedInPatients.setForeground(Color.WHITE);
		lblCheckedInPatients.setBounds(15, 16, 148, 17);
		lblCheckedInPatients.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(15, 42, 249, 245);
		
		txtIDin = new JTextField();
		txtIDin.setBackground(SystemColor.control);
		txtIDin.setBounds(15, 335, 134, 20);
		txtIDin.setColumns(10);
		
		btnSearchIn = new JButton("Search");
		btnSearchIn.setBounds(185, 332, 77, 23);
		btnSearchIn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		
		btnShowIn = new JButton("Show All");
		btnShowIn.setBounds(185, 298, 79, 23);
		btnShowIn.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		
		JLabel lblNewLabel_1 = new JLabel("Enter ID:-");
		lblNewLabel_1.setBounds(15, 315, 54, 15);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		txtIDout = new JTextField();
		txtIDout.setBackground(SystemColor.control);
		txtIDout.setBounds(371, 340, 129, 20);
		txtIDout.setColumns(10);
		
		btnSearchOut = new JButton("Search");
		btnSearchOut.setBounds(531, 334, 79, 23);
		btnSearchOut.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		
		btnShowOut = new JButton("Show All");
		btnShowOut.setBounds(531, 298, 79, 23);
		btnShowOut.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		
		JLabel label = new JLabel("Enter ID:-");
		label.setForeground(SystemColor.desktop);
		label.setBounds(371, 320, 54, 15);
		label.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		btnback = new JButton("Back");
		btnback.setBounds(15, 383, 59, 23);
		btnback.setFont(new Font("Times New Roman", Font.BOLD, 12));
		
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(371, 42, 239, 246);
		
		JList lstIn = new JList(lstinpatient);
		lstIn.setOpaque(false);
		lstIn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane_1.setViewportView(lstIn);
		contentPane.setLayout(null);
		contentPane.add(lblCheckedInPatients);
		contentPane.add(txtIDin);
		contentPane.add(lblNewLabel_1);
		contentPane.add(scrollPane_1);
		contentPane.add(btnback);
		contentPane.add(btnSearchIn);
		contentPane.add(btnShowIn);
		contentPane.add(txtIDout);
		contentPane.add(btnSearchOut);
		contentPane.add(btnShowOut);
		contentPane.add(scrollPane);
		
		lstOut = new JList(lstoutpatient);
		lstOut.setOpaque(false);
		lstOut.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		scrollPane.setViewportView(lstOut);
		contentPane.add(lblNewLabel);
		contentPane.add(label);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ViewPatients.class.getResource("/GUI/viewpatients.jpg")));
		lblNewLabel_2.setBounds(0, 0, 634, 421);
		contentPane.add(lblNewLabel_2);
		
	}

	private void createevents()
	{
		
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Admin a = new Admin();
				dispose();
				a.setVisible(true);
			}
		});
		
		btnSearchOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(txtIDout.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill all the Fields");
				}
				else
				{
					lstoutpatient.clear();
						try
						{
							PatientHistory ph = Welcome.hospital.searchPatient(Integer.parseInt(txtIDout.getText()));
							PatientHistoryNode temp = ph.head;
							while(temp!=null)
							{
								lstoutpatient.addElement(temp.patient.toString());
								temp = temp.next;
							}
							txtIDout.setText("");
						}
						catch(NullPointerException e1)
						{
							JOptionPane.showMessageDialog(null,"Data Not Found");
							
						}catch(NumberFormatException e1)
						{
							JOptionPane.showMessageDialog(null,"Invalid input");
							
						}
						
					}
					
			}
		});
		
		
		btnShowOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				lstoutpatient.clear();
				Record n = Welcome.hospital.HR.head;
				Tout(n);
			}
		});
		
		
		btnSearchIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(txtIDin.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Fill in the ID first");
				}
				else
				{
					lstinpatient.clear();
					
					
						try
						{
							Patient currentInPatient = Welcome.hospital.searchInPatient(Integer.parseInt(txtIDin.getText()));
							lstinpatient.addElement(currentInPatient.toString());
							txtIDin.setText("");
						}
						catch(NullPointerException e1)
						{
							JOptionPane.showMessageDialog(null,"Data Not Found");
							
						}
						catch(NumberFormatException e1)
						{
							JOptionPane.showMessageDialog(null,"Invalid input");
							
						}
						
					
				}
			}
		});
		
		btnShowIn.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				lstinpatient.clear();
				CheckedInNode n = Welcome.hospital.CIP.head;
				Tin(n);
			}
		});
		
	}
	
	
	public void Tin(CheckedInNode n) 
	{
		if(n!=null)
		{
			Tin(n.left);
			inPatient p1 = n.patient;
			lstinpatient.addElement("Name:"+p1.firstName+" "+p1.lastName+" // ID:"+p1.ID+" // Disease: "+p1.disease+" // AllotedDoc: "+p1.doctorAlloted+" // AllotedRoom:"+p1.roomNo+" // Bill: "+p1.bill+" // Nurse:"+p1.nurseAlloted+" // RoomNo:"+p1.roomNo);
			Tin(n.right);
		}
	} 
	public void Tout(Record n) 
	{
		if(n!=null)
		{
			Tout(n.left);
			PatientHistoryNode p1 = n.patienthistory.head;
			while(p1!=null)
			{
				
				lstoutpatient.addElement(p1.patient.toString());
				p1 = p1.next;
			}
			Tout(n.right);
		}
	} 
}
