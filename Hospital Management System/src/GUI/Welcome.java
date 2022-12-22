package GUI;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class Welcome extends JFrame {

	private JPanel contentPane;
	private JTextField txtInPatient;
	private JTextField txtOutPatient;
	static Hospital hospital;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTextField txtViewRecords;
	private JTextField txtAdmin;
	private JLabel background;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
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
					Welcome frame = new Welcome();
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
	public Welcome() 
	{
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Welcome.class.getResource("/GUI/hospital (1).png")));
		MakeHospital();
		initcomponents();
		createevents();
		
	}


	private void initcomponents() 
	{
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 544);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtInPatient = new JTextField();
		txtInPatient.setBounds(13, 366, 134, 64);
		txtInPatient.setForeground(SystemColor.window);
		
		txtInPatient.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtInPatient.setText("In Patient");
		txtInPatient.setHorizontalAlignment(SwingConstants.CENTER);
		txtInPatient.setEditable(false);
		txtInPatient.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), new Color(255, 255, 255), Color.WHITE, Color.WHITE));
		txtInPatient.setBackground(SystemColor.desktop);
		txtInPatient.setColumns(10);
		
		txtOutPatient = new JTextField();
		txtOutPatient.setBounds(635, 383, 134, 64);
		txtOutPatient.setForeground(SystemColor.window);
		
		txtOutPatient.setText("Out Patient");
		txtOutPatient.setHorizontalAlignment(SwingConstants.CENTER);
		txtOutPatient.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtOutPatient.setEditable(false);
		txtOutPatient.setColumns(10);
		txtOutPatient.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		txtOutPatient.setBackground(SystemColor.desktop);
		
		txtViewRecords = new JTextField();
		txtViewRecords.setBounds(281, 263, 134, 65);
		txtViewRecords.setForeground(SystemColor.window);
		
		txtViewRecords.setText("View Records");
		txtViewRecords.setHorizontalAlignment(SwingConstants.CENTER);
		txtViewRecords.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtViewRecords.setEditable(false);
		txtViewRecords.setColumns(10);
		txtViewRecords.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		txtViewRecords.setBackground(SystemColor.desktop);
		
		txtAdmin = new JTextField();
		txtAdmin.setBounds(466, 263, 134, 65);
		txtAdmin.setForeground(SystemColor.window);
		
		txtAdmin.setText("Admin");
		txtAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		txtAdmin.setFont(new Font("Times New Roman", Font.BOLD, 16));
		txtAdmin.setEditable(false);
		txtAdmin.setColumns(10);
		txtAdmin.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.WHITE, Color.WHITE, Color.WHITE, Color.WHITE));
		txtAdmin.setBackground(SystemColor.desktop);
		
		JLabel lblNewLabel_3 = new JLabel("Hospital Management System");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setBounds(0, 0, 794, 69);
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setIcon(new ImageIcon(Welcome.class.getResource("/GUI/blood.png")));
		lblNewLabel_3.setOpaque(true);
		lblNewLabel_3.setBackground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(null);
		contentPane.add(txtViewRecords);
		contentPane.add(txtInPatient);
		contentPane.add(txtOutPatient);
		contentPane.add(txtAdmin);
		contentPane.add(lblNewLabel_3);
		lblNewLabel = new JLabel("");
		
		lblNewLabel.setIcon(new ImageIcon(Welcome.class.getResource("/GUI/arrowup.png")));
		lblNewLabel.setBounds(156, 365, 64, 65);
		contentPane.add(lblNewLabel);
		lblNewLabel_1 = new JLabel("");
		
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Welcome.class.getResource("/GUI/arrowright.png")));
		lblNewLabel_1.setBounds(635, 324, 134, 53);
		contentPane.add(lblNewLabel_1);
		
		background = new JLabel("");
		background.setIcon(new ImageIcon(Welcome.class.getResource("/GUI/background.jpg")));
		background.setBounds(0, 0, 800, 515);
		contentPane.add(background);
		
		
		
		
		
		
		
	}
	
	
	

	private void createevents() 
	{
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				InPatientPanel op = new InPatientPanel();
				dispose();
				op.setVisible(true);
			}
		});
		
		
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				OutPatientPanel op = new OutPatientPanel();
				dispose();
				op.setVisible(true);
			}
		});
		
		txtViewRecords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				ViewRecords vr = new ViewRecords();
				dispose();
				vr.setVisible(true);
			}
		});
		
		txtOutPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				OutPatientPanel op = new OutPatientPanel();
				dispose();
				op.setVisible(true);
			}
		});
		
		txtInPatient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				InPatientPanel op = new InPatientPanel();
				dispose();
				op.setVisible(true);
			}
		});
		
		txtAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try
				{
					
					if(JOptionPane.showInputDialog(null, "Enter password","Admin",JOptionPane.PLAIN_MESSAGE).equals("12345"))
					{
						Admin a = new Admin();
						dispose();
						a.setVisible(true);
					}
					else JOptionPane.showMessageDialog(null, "Wrong Password");
				}
				catch(Exception e1)
				{
					
				}
				
			}
		});
		
		
		
		
	}
	
	private void MakeHospital() 
	{
		hospital = new Hospital(100,150);
		Patient p;
		Doctor doctor;
		Nurse nurse;
		String fn,ln,d,id,rn;
		
		try
		{
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			con = DriverManager.getConnection("jdbc:ucanaccess://Hospital.accdb");
			
			pst = con.prepareStatement("select * from doctors");
			rs = pst.executeQuery();
			
			
			while(rs.next())
			{
				fn = rs.getString("FirstName");
				ln =  rs.getString("LastName");
				d =  rs.getString("Qualification").toLowerCase();
				id =  rs.getString("CNIC");
				
				doctor = new Doctor(fn,ln,id,d); 
				hospital.addDoctor(doctor);
			}
			
			pst = con.prepareStatement("select * from nurses");
			rs = pst.executeQuery();
			
			
			while(rs.next())
			{
				fn = rs.getString("FirstName");
				ln =  rs.getString("LastName");
				id =  rs.getString("CNIC");
				
				nurse = new Nurse(fn,ln,id); 
				hospital.addNurse(nurse);
			}
			
			pst = con.prepareStatement("select * from outpatients");
			rs = pst.executeQuery();
			
			
			while(rs.next())
			{
				fn = rs.getString("FirstName");
				ln =  rs.getString("LastName");
				d =  rs.getString("Disease").toLowerCase();
				id =  rs.getString("CNIC");
				
				p = new outPatient(fn,ln,d,id); 
				hospital.addPatient(p);   
			}
			pst = con.prepareStatement("select * from checkedout");
			rs = pst.executeQuery();
			
			
			while(rs.next())
			{
				fn = rs.getString("FirstName");
				ln =  rs.getString("LastName");
				d =  rs.getString("Disease").toLowerCase();
				id =  rs.getString("CNIC");
				rn = rs.getString("RoomNo");
				p = new inPatient(fn,ln,d,id,rn); 
				int i=0;
				while(true)
				{
					if(hospital.rooms[i]!=null)
					{
						if((hospital.rooms[i].roomNo.equals(((inPatient) p).roomNo)))
							break;
							
					}
					i++;
				}
				((inPatient) p).setroom(hospital.rooms[i].roomNo,hospital.rooms[i].nurse);
				int key = strToint(p.disease);
				 i = Hashdoc(key);
				int j = 1;
				
						while(j<40)
						{
							
							if(hospital.DL.doctors[i]==null)
							{
							JOptionPane.showMessageDialog(null, "Cure not available");
							}
							if(hospital.DL.doctors[i].qualification.equals(p.disease))
							{
								p.doctorAlloted = hospital.DL.doctors[i].lastName;
								p.bill = strToint(p.disease);
								break;
							}
							else
							{
								i = LinearRehashdoc(key, j);
								j++;
							}
						}		
				hospital.HR.insert(p);
			}
			
			pst = con.prepareStatement("select * from inpatients");
			rs = pst.executeQuery();
			
			
			while(rs.next())
			{
				fn = rs.getString("FirstName");
				ln =  rs.getString("LastName");
				d =  rs.getString("Disease").toLowerCase();
				id =  rs.getString("CNIC");
				rn = rs.getInt("RoomNo")+"";
				if(rn.equals("-1"))
				{
					p = new inPatient(fn,ln,d,id); 
				}
				else
					p = new inPatient(fn,ln,d,id,rn);
				hospital.addPatient(p);
			}
			
			
			
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	private int Hashdoc(int sum)
	  { 
	      return sum%hospital.DL.doctors.length;
	  }
	private int LinearRehashdoc(int key, int i)
	  {
	            return (key+i)%hospital.DL.doctors.length;
	  }


	private int strToint(String disease) {
		int sum=0;
      for (int i = 0; i < disease.length(); i++) 
      {
          sum=sum+disease.charAt(i);
      }
      
      return sum;
	}
}
