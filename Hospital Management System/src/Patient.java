
public class Patient 
{
	String firstName;
	String lastName;
	String disease;
	String ID;
	String doctorAlloted;
	double bill;
	public Patient(String fn, String ln,String d,String id)
	{
		firstName = fn;
		lastName = ln;
		disease = d;
		ID = id;
	}
	
}

class inPatient extends Patient
{
	String roomNo;
	String nurseAlloted;
	boolean checkIn;
	public inPatient(String fn, String ln,String d,String id)
	{
		super(fn,ln,d,id);
		checkIn = true;
	}
	public void setroom(String i)
	{
		roomNo = i;
		checkIn = true;
	}
}

class outPatient extends Patient
{
	public outPatient(String fn, String ln,String d,String id)
	{
		super(fn,ln,d,id);	
	}
}