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
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class ViewRecords extends JFrame {

	private JPanel contentPane;
    Hospital hospital;
	Patient currentInPatient;
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtID;
	private JButton btnSearch;
	private DefaultListModel records = new DefaultListModel();
	private JComboBox CBType;
	private JButton btnBack;
	private JButton btnCheck;
	private JList lstrecords;
	private JLabel lblNewLabel_2;
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
					ViewRecords frame = new ViewRecords();
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
	public ViewRecords() 
	{
		setResizable(false);
		setTitle("Records");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ViewRecords.class.getResource("/GUI/hospital (1).png")));
		initcomponents();
		createevents();
		
	}

	private void initcomponents() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 491, 352);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtID = new JTextField();
		txtID.setBounds(64, 51, 162, 20);
		txtID.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.setBounds(147, 272, 91, 25);
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		
		JLabel lblNewLabel_1 = new JLabel("CNIC :");
		lblNewLabel_1.setBounds(15, 53, 39, 16);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		
		
		CBType = new JComboBox();
		CBType.setBounds(64, 98, 162, 20);
		CBType.setModel(new DefaultComboBoxModel(new String[] {"", "Checked In", "Checked Out"}));
		
		JLabel lblNewLabel = new JLabel("Type :");
		lblNewLabel.setBounds(15, 99, 39, 17);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		btnBack = new JButton("Back");
		btnBack.setBounds(15, 272, 63, 25);
		btnBack.setFont(new Font("Times New Roman", Font.BOLD, 14));
		
		btnCheck = new JButton("Check Out");
		btnCheck.setBounds(84, 160, 99, 25);
		btnCheck.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnCheck.setEnabled(false);
		
		lstrecords = new JList(records);
		lstrecords.setBackground(SystemColor.inactiveCaption);
		lstrecords.setOpaque(false);
		lstrecords.setCellRenderer(new TransparentListCellRenderer());
		JScrollPane scrollPane = new JScrollPane(lstrecords);
		scrollPane.setOpaque(false);
		scrollPane.setBounds(244, 5, 216, 292);
		scrollPane.getViewport().setOpaque(false);
		contentPane.setLayout(null);
		contentPane.add(btnSearch);
		contentPane.add(lblNewLabel);
		contentPane.add(lblNewLabel_1);
		contentPane.add(CBType);
		contentPane.add(txtID);
		contentPane.add(btnBack);
		contentPane.add(btnCheck);
		contentPane.add(scrollPane);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(ViewRecords.class.getResource("/GUI/recordsbg.jpg")));
		lblNewLabel_2.setBounds(0, 0, 485, 323);
		contentPane.add(lblNewLabel_2);
		
	}

	private void createevents() 
	{
		
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Welcome.hospital.checkOut(currentInPatient);
				try 
				{
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					con = DriverManager.getConnection("jdbc:ucanaccess://Hospital.accdb");
					
					pst = con.prepareStatement("DELETE FROM inpatients where CNIC ="+currentInPatient.ID);
					pst.executeUpdate();
					
					pst = con.prepareStatement("insert into checkedout(FirstName,LastName,Disease,CNIC,RoomNo) values (?,?,?,?,?)");
					pst.setString(1, currentInPatient.firstName);
					pst.setString(2, currentInPatient.lastName);
					pst.setString(3, currentInPatient.disease);
					pst.setString(4, currentInPatient.ID);
					pst.setString(5, ((inPatient) currentInPatient).roomNo);
					pst.executeUpdate();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				txtID.setText("");
				CBType.setSelectedIndex(0);
				records.clear();
				btnCheck.setEnabled(false);
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
		
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				if(txtID.getText().equals("") || CBType.getSelectedIndex()==0)
				{
					JOptionPane.showMessageDialog(null, "Fill all the Fields");
				}
				else
				{
					records.clear();
					if(CBType.getSelectedIndex()==2)
					{
						try
						{
							PatientHistory ph = Welcome.hospital.searchPatient(Integer.parseInt(txtID.getText()));
							PatientHistoryNode temp = ph.head;
							while(temp!=null)
							{
								records.addElement(temp.patient.toString());
								temp = temp.next;
								btnCheck.setEnabled(false);
							}
						}
						catch(NullPointerException e1)
						{
							JOptionPane.showMessageDialog(null,"Data Not Found");
							btnCheck.setEnabled(false);
						}catch(NumberFormatException e2)
						{
							JOptionPane.showMessageDialog(null,"Invalid Input");
							btnCheck.setEnabled(false);
						}
						
					}
					else
					{
						try
						{
							currentInPatient = Welcome.hospital.searchInPatient(Integer.parseInt(txtID.getText()));
							records.addElement(currentInPatient.toString());
							btnCheck.setEnabled(true);
						}
						catch(NullPointerException e1)
						{
							JOptionPane.showMessageDialog(null,"Data Not Found");
						}catch(NumberFormatException e2)
						{
							JOptionPane.showMessageDialog(null,"Invalid Input");
							btnCheck.setEnabled(false);
						}
						
					}
					
				}
				
			}
		});
		
	}
}
class TransparentListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        setForeground(Color.BLACK);
        setFont(new Font ("TimesRoman", Font.PLAIN,14));
        setOpaque(isSelected);
        return this;
    }

}
