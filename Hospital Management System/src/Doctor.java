
class Doctor
{
	String firstName;
	String lastName;
	String ID;
	String qualification;
	public Doctor(String fn,String ln, String id, String qual)
	{
		firstName = fn;
		lastName = ln;
		ID = id;
		qualification = qual;
	}
	public String getQual()
	{
		return qualification;
	}
}
