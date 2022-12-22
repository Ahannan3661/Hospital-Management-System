package GUI;

import javax.swing.JOptionPane;

public class NurseList
{
	Nurse[] nurses;
	int L = 0;
	int numOccupied = 0;
		public NurseList (int s)
	    {
	      int size=s+(s/3);
	      int newSize = getPrime(size);
	      L=newSize;
	      nurses= new Nurse[newSize];
	    }
	
		
		public int insert(Nurse key)
		  { 
			long sum = Long.parseLong(key.ID);
		       numOccupied++;
		       if( numOccupied < (L*0.7))
		       {
		    	   int h=(int)Hash(sum);
		    	   
		           if(nurses[h]==null) 
		           {
		        	   nurses[h]=key;
		        	   return h;
		           }

		           else
		           {
		           int j=1;
		           
		           while(nurses[h]!=null && j<11)
		           {
		        	   	 h=(int)LinearRehash(sum,j);
		        	   	 j++;
		           }
		           if(nurses[h]==null) 
		           {
		        	   nurses[h]=key;
		        	   return h;
		           }
		           else 
		             System.out.println("no space, resize table");
		           return 0;
		           }
		       }
		       else
		    	   System.out.println("no space, resize table");
		       return 0;
		  }
		
		
		public Nurse search (int key) 
		  {    
			long sum = key;
			  int h=(int)Hash(sum);
			  try
			  {
				  if(Integer.parseInt(nurses[h].ID)==key) 
			      {
			          return nurses[h];
			      }
			      else
			      {
			      int j=1;
			      
			      while(Integer.parseInt(nurses[h].ID)!=key && j<11)
			      {
			    	  	h=(int)LinearRehash(sum,j);
			            j++;
			      }
			      if(Integer.parseInt(nurses[h].ID)==key) 
			      {
			    	  System.out.println("Data " + key + " was Found at Index [" + h + "]");
			          return nurses[h];

			      }
			      else 
			      {
			    	  System.out.println("Data not found");
			    	  return null;
			      }
			      	
			      }  
			  }catch(Exception e)
			  {
				  JOptionPane.showMessageDialog(null, "ID doesn't match");
				  return null;
			  }
		      
		 
		       
		  }  
		
		public void delete (Nurse key) 
		  {     
			long sum = Long.parseLong(key.ID);
			  int h=(int)Hash(sum);
		      if (nurses[h] == key)
		      {
		    	  nurses[h] = null;
		          System.out.println("Data " + key + " was Deleted!");
		      }
		      else
		      {
		          int num = 0;
		          while(nurses[h] != key && num < 10)
		          {
		              h = (int)LinearRehash(sum, num);
		              num++;
		          }
		          if (nurses[h] == key)
		          {
		              System.out.println("Data " + key + " was Deleted!");
		              nurses[h] = null;
		          }
		          else
		          {
		              System.out.println("Data not Found");
		          }
		      }
		  }
		  public void displayTable() 
		  { 
		   
		      for (int i = 0; i < nurses.length; i++) 
		      {
		    	  if(nurses[i]!=null)
		          System.out.println("["+i+"] "+nurses[i].ID);
		      }
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
	
		    public long Hash(long sum)
		    { 
		        return sum%nurses.length;
		    }
		    
		    public long LinearRehash(long key, int i)
		    {
		              return (key+i)%nurses.length;
		    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	NurseNode head;
//	int maxsize;
//	public NurseList(int m)
//	{
//		maxsize = m;
//	}
//	public Room insert(Nurse key)
//	{ 
//		if(maxsize <=0)
//			return null;
//		long sum =  Long.parseLong(key.ID);
//		NurseNode n = new NurseNode(key);
//		if(head!=null)
//		{
//			NurseNode temp = head;
//			NurseNode temp2 = head;
//			while(temp!=null)
//			{
//				temp2 = temp;
//				if(sum<(Long.parseLong(temp.nurse.ID)))
//					temp=temp.left;
//				else if(sum == Long.parseLong(temp.nurse.ID))
//				{
//					break;
//				}
//				else
//					temp=temp.right;
//			}
//			if(sum<(Long.parseLong(temp2.nurse.ID)))
//				{
//				
//					temp2.left = n;
//				}
//			else
//				{
//					temp2.right = n;
//				}
//		}
//		else
//				{
//					head=n;
//				}
//		maxsize--;
//		return initializeroom(key);
//	}
//	
//	private Room initializeroom(Nurse nurse)
//	{	
//		return new Room(nurse);
//	}
//	
//	public boolean find(Nurse key)
//	{ 
//		long sum = Long.parseLong(key.ID);
//		if(head==null)
//			return false;
//		else
//		{
//			try
//			{
//				NurseNode temp = head;
//				while(Long.parseLong(temp.nurse.ID)!=sum)
//				{
//					if(sum>(Long.parseLong(temp.nurse.ID)))
//						temp=temp.right;
//					else temp = temp.left;	
//				}
//				if(temp.nurse == key)
//					return true;
//				return false;
//			}
//			catch(NullPointerException e)
//			{
//				return false;
//			}
//		
//			
//		}
//		
//	} 
	
}
