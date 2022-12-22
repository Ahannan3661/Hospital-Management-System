public class Hospital
{
	DoctorsList DL;
	HospitalRecords HR;
	NurseList NL;
	CheckedInPatients CIP;
	Room[] rooms;
	
	
	
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
	
	
	
	public void addNurse(Nurse nurse)
	{
		rooms[NL.insert(nurse)] = new Room(nurse) ;
	}
	public boolean searchNurse(Nurse nurse)
	{
		return NL.search(nurse);
	}
	public void deleteNurse(Nurse nurse)
	{
		NL.delete(nurse);
	}
	public void displayNurses()
	{
		NL.displayTable();
	}
	
	public void ShowRoomsStatus()
	{
		for(int i=0 ; i<rooms.length ; i++)
			{
				if(rooms[i]!=null)
				System.out.println("["+i+"] "+"Nurse:"+rooms[i].nurse.firstName+" "+rooms[i].availability);
				else
					System.out.println("["+i+"] null");
			}
	}
	
	public void addPatient(Patient p)
	{
		allotdoctor(p);
		if(p instanceof inPatient)
		{
			allotroom(p);
			CIP.insert((inPatient) p);
		}
		else HR.insert(p);
	}
	private void allotroom(Patient p) 
	{
		for(int i=0 ; i<rooms.length ; i++)
		{
			if(rooms[i]!=null)
			{
				if(rooms[i].availability==true)
				{
					((inPatient) p).setroom(rooms[i].roomNo);
					rooms[i].changeavailability(false);
					return;
				}
			}
			
		}
		
	}

	
	public void deleteInPatient(inPatient p)
	{
		CIP.delete(p);
	}
	public boolean searchInPatient(inPatient p)
	{
		return CIP.find(p);
	}
	
	public void displayInPatients()
	{
		CIP.traverse();
	} 

	private void allotdoctor(Patient p) 
	{
		int sum=strToint(p.disease);
		int h=sum%DL.doctors.length;
		if(DL.doctors[h]!=null)
		{
			p.doctorAlloted = DL.doctors[h].lastName;
			p.bill = 1000*Math.random();
		}
		
	}


	private int strToint(String disease) {
		int sum=0;
        for (int i = 0; i < disease.length(); i++) 
        {
            sum=sum+disease.charAt(i);
        }
        
        return sum;
	}


	public void displayPatients()
	{
		HR.traverse(HR.head);
	}
	public boolean searchPatient(Patient p)
	{
		return HR.find(p);
	}
	
	public void addDoctor(Doctor doc)
	{
		DL.insert(doc);
	}
	public void searchDoctor(Doctor doc)
	{
		DL.search(doc);
	}
	public void deleteDoctor(Doctor doc)
	{
		DL.delete(doc);
	}
	public void displayDoctors()
	{
		DL.displayTable();
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
