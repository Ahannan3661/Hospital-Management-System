public class HospitalRecords 
{
	Record head;
	
	public void insert(Patient key)
	{ 
		
		long sum =  Long.parseLong(key.ID);
		
		if(head!=null)
		{
			Record temp = head;
			Record temp2 = head;
			while(temp!=null)
			{
				temp2 = temp;
				if(sum<(Long.parseLong(temp.patienthistory.head.patient.ID)))
					temp=temp.left;
				else if(sum == Long.parseLong(temp.patienthistory.head.patient.ID))
				{
					break;
				}
				else
					temp=temp.right;
			}
			if(sum<(Long.parseLong(temp2.patienthistory.head.patient.ID)))
				{
				    Record n = new Record(key);
					temp2.left = n;
				}
			else if(sum == Long.parseLong(temp2.patienthistory.head.patient.ID))
			{
				temp2.patienthistory.insert(key);
			}
			else
				{
					Record n = new Record(key);
					temp2.right = n;
				}
		}
		else
				{
					Record n = new Record(key);
					head=n;
				}
	}
	
	public boolean find(Patient key)
	{ 
		long sum = Long.parseLong(key.ID);
		if(head==null)
			return false;
		else
		{
			try
			{
				Record temp = head;
				while(Long.parseLong(temp.patienthistory.head.patient.ID)!=sum)
				{
					if(sum>(Long.parseLong(temp.patienthistory.head.patient.ID)))
						temp=temp.right;
					else temp = temp.left;	
				}
				if(Long.parseLong(temp.patienthistory.head.patient.ID)==sum)
					{
						return temp.patienthistory.find(key);
					} return false;
			}
			catch(NullPointerException e)
			{
				return false;
			}
		
			
		}
		
	} 
	
	
	public void traverse(Record n)
	{
		if(n!=null)
		{
			traverse(n.left);
			PatientHistoryNode temp = n.patienthistory.head;
			while(temp!=null)
			{
			Patient p = temp.patient;
			if(p instanceof inPatient)
			{
				inPatient p1 = (inPatient)p;
				System.out.println("Name:"+p1.firstName+" "+p1.lastName+" ID:"+p1.ID+"\tDisease: "+p1.disease+"\t AllotedDoc: "+p1.doctorAlloted+"AllotedRoom:"+p1.roomNo+" CheckedIn: "+p1.checkIn+"\t Bill: "+p1.bill);
			}
			else
			System.out.println("Name:"+p.firstName+" "+p.lastName+" ID:"+p.ID+"\tDisease: "+p.disease+"\t AllotedDoc: "+p.doctorAlloted+"\t Bill: "+p.bill);
			temp=temp.next;
			}
			
			
			traverse(n.right);
		}
	}
	
	int strToint(String key)
    {
  	  int sum=0;
        for (int i = 0; i < key.length(); i++) 
        {
            sum=sum+key.charAt(i);
        }
        
        return sum;
    }
}
