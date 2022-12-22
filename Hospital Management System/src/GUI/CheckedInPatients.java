package GUI;

import javax.swing.JOptionPane;

public class CheckedInPatients
{
	CheckedInNode head;

	public void insert(inPatient key)
	{
		long sum =  Long.parseLong(key.ID);
		CheckedInNode n = new CheckedInNode(key);
		if(head!=null)
		{
			CheckedInNode temp = head;
			CheckedInNode temp2 = head;
			while(temp!=null)
			{
				temp2 = temp;
				if(sum<(Long.parseLong(temp.patient.ID)))
					temp=temp.left;
				else
					temp=temp.right;
			}
			if(sum<(Long.parseLong(temp2.patient.ID)))
				{
				    
					temp2.left = n;
				}
			else
				{
					temp2.right = n;
				}
		}
		else
				{
					head=n;
				}
		
	}
	
	public Patient find(int key)
	{ 
		long sum = key;
		if(head==null)
			return null;
		else
		{
			try
			{
				CheckedInNode temp = head;
				while(Long.parseLong(temp.patient.ID)!=sum && temp!=null)
				{
					if(sum>(Long.parseLong(temp.patient.ID)))
						temp=temp.right;
					else temp = temp.left;	
				}
				 if(Long.parseLong(temp.patient.ID)==key)
					 return temp.patient;
			}
			catch(NullPointerException e)
			{
				return null;
			}
			return null;
			
		}
		
	}
	
	public void delnochild(CheckedInNode temp2, CheckedInNode temp)
	{
		if(Long.parseLong(temp.patient.ID)<Long.parseLong(temp2.patient.ID))
		{
			temp2.left=null;
			
		}
		else
			temp2.right=null;
	}
	
	public void delonechild(CheckedInNode temp2, CheckedInNode temp)
	{
		if(Long.parseLong(temp.patient.ID)<Long.parseLong(temp2.patient.ID))
		{
			if(temp.left==null)
				temp2.left=temp.right;
			else
				temp2.left=temp.left;
		}
		else
		{
			if(temp.left==null)
				temp2.right=temp.right;
			else
				temp2.right=temp.left;
		}
	}
	
	public void delete(String d)
	{
		long sum = Integer.parseInt(d);
		CheckedInNode temp = head;
		CheckedInNode temp2 = head;
		while(temp!=null && !(d.equals(temp.patient.ID)))
		{
			temp2 = temp;
			if(sum<Long.parseLong(temp.patient.ID))
				temp=temp.left;
			else
				temp=temp.right;
		}
		if(temp==head && (temp.left==null || temp.right==null))
		{
			if(temp.left==null && temp.right==null)
				head=null;
			
			else if((temp.left==null &&temp.right!=null) || (temp.left!=null && temp.right==null))
			{
				if(temp.right==null)
					head=temp.left;
				else
					head=temp.right;
			}
		
			
		}
		else if(temp!=null)
		{
			//no child case
			if(temp.left==null && temp.right==null)
			{
				delnochild(temp2,temp);
				
			}
			
			//one child case
			else if((temp.left==null &&temp.right!=null) || (temp.left!=null && temp.right==null))
			{
				delonechild(temp2, temp);
			
			}
			
			//two child case
			else
			{
				CheckedInNode t1 = temp.right;
				CheckedInNode t2 = temp.right;
				while(t1.left!=null)
				{
					t2=t1;
					t1=t1.left;
				}
				temp.patient=t1.patient;
				//no child case
				if(t1.left==null && t1.right==null)
				{
					if(t1==t2)
						delnochild(temp,t1);
					else
					delnochild(t2,t1);
				}
				
				//one child case
				else if((t1.left==null &&t1.right!=null) || (t1.left!=null && t1.right==null))
				{
					if(t1==t2)
						delonechild(temp,t1);
					else
						delonechild(t2, t1);
					
				}
			}
		}
		else
			System.out.println("Data Not Found!");
		
	}
	
	public void traverse()
	{
		T(head);
	}
	public void T(CheckedInNode n) // print tree
	{
		if(n!=null)
		{
			T(n.left);
			inPatient p1 = n.patient;
			System.out.println("Name:"+p1.firstName+" "+p1.lastName+" ID:"+p1.ID+"\tDisease: "+p1.disease+"\t AllotedDoc: "+p1.doctorAlloted+"AllotedRoom:"+p1.roomNo+" CheckedIn: "+"\t Bill: "+p1.bill);
			T(n.right);
		}
	} 
	
	
}
