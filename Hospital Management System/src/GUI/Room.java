package GUI;

public class Room 
{
	String roomNo;
	Nurse nurse;
	boolean availability;

	public Room(Nurse nurse)
	{
		this.nurse = nurse;
		roomNo = nurse.ID;
		availability = true;
	}
	
	public void changeavailability(boolean b)
	{
		availability = b;
	}
}
