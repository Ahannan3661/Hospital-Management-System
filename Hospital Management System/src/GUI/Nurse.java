package GUI;

public class Nurse
{
	String firstName;
	String lastName;
	String ID;
	
	public Nurse(String fn,String ln, String ID)
	{
		firstName = fn;
		lastName = ln;
		this.ID = ID;
	}

	public String toString()
	{
		String r="";
		r+= "Name: "+firstName+" "+lastName+" // ID: "+ID;
		return r;
	}
}
