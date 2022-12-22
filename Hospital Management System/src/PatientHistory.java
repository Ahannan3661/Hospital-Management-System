public class PatientHistory 
{
	PatientHistoryNode head;
	
	
	public void insert(Patient p)
	{
		PatientHistoryNode n = new PatientHistoryNode(p);
		n.next = head;
		head = n;
	}
	
	public boolean find(Patient key)
	{
		PatientHistoryNode temp2 = head;
		while(temp2!=null)
		{
			if(key==temp2.patient)
				return true;
			temp2=temp2.next;
		}return false;
	}
	
	
}
