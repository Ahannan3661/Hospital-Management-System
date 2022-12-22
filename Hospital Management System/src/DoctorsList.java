
public class DoctorsList
{
	int L = 0;
	int numOccupied = 0;
	Doctor[] doctors;
	
	public DoctorsList(int s)
    {
      int size=s+(s/3);
      int newSize = getPrime(size);
      L=newSize;
      doctors =new Doctor[newSize];
    }
	
	
	public void insert(Doctor key)
	  { 
		
	      int sum=strToint(key.qualification);
	       numOccupied++;
	       if( numOccupied < (L*0.7))
	       {
	    	   int h=Hash(sum);
	    	   
	           if(doctors[h]==null) 
	           {
	        	   doctors[h]=key;
	           }

	           else
	           {
	           int j=1;
	           
	           while(doctors[h]!=null && j<11)
	           {
	        	   	 h=LinearRehash(sum,j);
	                 j++;
	           }
	           if(doctors[h]==null) 
	           {
	        	   doctors[h]=key;
	           }
	           else 
	             System.out.println("no space, resize table");
	           }
	       }
	       else
	    	   System.out.println("no space, resize table");
	  }
	
	public boolean search(Doctor key) 
	  {    
		  int sum=strToint(key.qualification);
		  int h=Hash(sum);
		   
	      if(doctors[h]==key) 
	      {
	    	  System.out.println("Data " + key + " was Found at Index [" + h + "]");
	          return true;
	      }
	      else
	      {
	      int j=1;
	      
	      while(doctors[h]!=key && j<11)
	      {
	    	  	h=LinearRehash(sum,j);
	            j++;
	      }
	      if(doctors[h]==key) 
	      {
	    	  System.out.println("Data " + key + " was Found at Index [" + h + "]");
	          return true;

	      }
	      else 
	      {
	    	  System.out.println("Data not found");
	    	  return false;
	      }
	      	
	      }
	 
	       
	  }  
	
	public void delete (Doctor key) 
	  {     
		  int sum=strToint(key.qualification);
		  int h=Hash(sum);
	      if (doctors[h] == key)
	      {
	    	  doctors[h] = null;
	      }
	      else
	      {
	          int num = 0;
	          while(doctors[h] != key && num < 10)
	          {
	              h = LinearRehash(sum, num);
	              num++;
	          }
	          if (doctors[h] == key)
	          {
	              doctors[h] = null;
	          }
	          else
	          {
	              System.out.println("Data not Found");
	          }
	      }
	  }
	
	
	
	  public int Hash(int sum)
	  { 
	      return sum%doctors.length;
	  }
	  public int LinearRehash(int key, int i)
	  {
	            return (key+i)%doctors.length;
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
	    
	    int strToint(String key)
	    {
	  	  int sum=0;
	        for (int i = 0; i < key.length(); i++) 
	        {
	            sum=sum+key.charAt(i);
	        }
	        
	        return sum;
	    }
	    
	    
	    public void displayTable() 
	    { 
	     
	        for (int i = 0; i < doctors.length; i++) 
	        {
	           if(doctors[i]!=null) System.out.println("["+i+"] "+doctors[i].qualification);
	        }
	    }
	    
}
