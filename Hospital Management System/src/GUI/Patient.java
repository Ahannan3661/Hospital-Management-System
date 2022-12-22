package GUI;


public class Patient 
{
	String firstName;
	String lastName;
	String disease;
	String ID;
	String doctorAlloted;
	int bill;
	public Patient(String fn, String ln,String d,String id)
	{
		firstName = fn;
		lastName = ln;
		disease = d;
		ID = id;
	}
	
	public String toString()
	{
		return "Name:"+firstName+" "+lastName+" // CNIC:"+ID+" // Issue:"+disease+" // Doctor:"+doctorAlloted+" // Bill:$"+bill;
	}
}

class inPatient extends Patient
{
	String roomNo;
	String nurseAlloted;
	public inPatient(String fn, String ln,String d,String id,String RoomNo)
	{
		super(fn,ln,d,id);
		roomNo = RoomNo;
	}
	public inPatient(String fn, String ln,String d,String id)
	{
		super(fn,ln,d,id);
	}
	public void setroom(String i,Nurse nurse)
	{
		roomNo = i;
		nurseAlloted = nurse.lastName;
	}
	public String toString()
	{
		return super.toString()+" // Nurse:"+nurseAlloted+" // RoomNo:"+roomNo;
		 
	}
}

class outPatient extends Patient
{
	public outPatient(String fn, String ln,String d,String id)
	{
		super(fn,ln,d,id);	
	}
	
	
}


