package GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Hospital
{
	DoctorsList DL;
	HospitalRecords HR;
	NurseList NL;
	CheckedInPatients CIP;
	Room[] rooms;
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public Hospital(int numdocs,int numnurses)
	{
		DL = new DoctorsList(numdocs);
		NL = new NurseList(numnurses);
		HR = new HospitalRecords();
		CIP = new CheckedInPatients();
		int size=numnurses+(numnurses/3);
	      int newSize = getPrime(size);
		rooms = new Room[newSize];
	}


	public void addNurse(Nurse nurse)//BIG O = constant
	{
		rooms[NL.insert(nurse)] = new Room(nurse) ;
	}
	public Nurse searchNurse(int Key)//BIG O = constant
	{
		return NL.search(Key);
	}
	
	public boolean addPatient(Patient p)//BIG O = log(n) for outPatient
	{									//BIG O = log(n)+ n for inPatient
		if(allotdoctor(p))
		{
			if(p instanceof inPatient)
			{
				if(allotroom(p))
				{
					CIP.insert((inPatient) p);
					return true;
				}
				return false;
			}
			else {
				HR.insert(p);
				return true;
			}
		}return false;
	}

	private boolean allotdoctor(Patient p) 
	{
		int key = strToint(p.disease.toLowerCase());
		int i = Hashdoc(key);
		int j = 1;
		
				while(j<40)
				{
					if(DL.doctors[i]==null)
					{
					JOptionPane.showMessageDialog(null, "Cure not available");
					return false;
					}
					if(DL.doctors[i].qualification.equals(p.disease))
					{
						p.doctorAlloted = DL.doctors[i].lastName;
						p.bill = strToint(p.disease);
						return true;
					}
					else
					{
						i = LinearRehashdoc(key, j);
						j++;
					}
				}		
				JOptionPane.showMessageDialog(null, "Cure not available");
				return false;
	}
	
	private boolean allotroom(Patient p) 
	{
		if(((inPatient) p).roomNo!=null)
		{
			int i=0;
			while(true)
			{
				if(rooms[i]!=null)
				{
					if((rooms[i].roomNo.equals(((inPatient) p).roomNo)))
						break;
						
				}
				i++;
			}
			((inPatient) p).setroom(rooms[i].roomNo,rooms[i].nurse);
			rooms[i].changeavailability(false);
			return true;
		}
		else
		{
			for(int i=0 ; i<rooms.length ; i++)
			{
				if(rooms[i]!=null)
				{
					if(rooms[i].availability==true)
					{
						((inPatient) p).setroom(rooms[i].roomNo,rooms[i].nurse);
						rooms[i].changeavailability(false);
						
							try {
								Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
								con = DriverManager.getConnection("jdbc:ucanaccess://Hospital.accdb");
								
								pst = con.prepareStatement("update inpatients SET RoomNo=? WHERE CNIC=?");
								pst.setString(1,rooms[i].roomNo );
								pst.setString(2, p.ID);
								pst.executeUpdate();
							} catch (ClassNotFoundException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
					
						return true;
						
						
					}
				}
				
			}
		}
		JOptionPane.showMessageDialog(null, "No Rooms Avaliable");
		return false;
		
	}
	
	public void checkOut(Patient p)//BIG(O)=log(n) for one/no child
	{								//BIG(O)=log(n)+n for 2 child
		deleteInPatient(p.ID);
		HR.insert(p);
		String room = ((inPatient) p).roomNo;
		clearRoom(room);
	}
	
	public void clearRoom(String id)//BIG O = constant
	{
		int h = Hashnurse(Integer.parseInt(id));
		int j = 1;
		while(!(rooms[h].roomNo.equals(id)))
		{
			
			h = LinearRehashnurse(Integer.parseInt(id),j);
			j++;
		}
		rooms[h].changeavailability(true);
	}
	private int Hashnurse(int sum)
	  { 
	      return sum%NL.nurses.length;
	  }
	private int LinearRehashnurse(int key, int i)
	  {
	            return (key+i)%NL.nurses.length;
	  }
	
	public void deleteInPatient(String id)
	{
		CIP.delete(id);
	}
	
	public Patient searchInPatient(int p)//BIG(O)=LOG(N)
	{
		return CIP.find(p);
	}
	
	private int Hashdoc(int sum)
	  { 
	      return sum%DL.doctors.length;
	  }
	private int LinearRehashdoc(int key, int i)
	  {
	            return (key+i)%DL.doctors.length;
	  }


	private int strToint(String disease)
	{
		int sum=0;
        for (int i = 0; i < disease.length(); i++) 
        {
            sum=sum+disease.charAt(i);
        }
        
        return sum;
	}


	public PatientHistory searchPatient(int p)//BIG(O)=LOG(N)
	{
		return HR.find(p);
	}
	
	public void addDoctor(Doctor doc) //BIG(O)=CONSTANT
	{
		DL.insert(doc);
	}
	public Doctor searchDoctor(String qual)//BIG(O)=CONSTANT
	{
		return DL.search(qual);
	}

	
	
	public int LinearRehash(int key, int i,int l)
	  {
	            return (key+i)%l;
	  }
	
	private int getPrime(int n) 
	   {
			while(true) 
			{
				if (isPrime(n)) return n;
				n++;
			}
		}
	   
	    private boolean isPrime(int n)
		{
			for (int i = 2; i <= n/2; i++) 
			{
				if (n % i == 0) return false;
			}
			return true;
		}
}
