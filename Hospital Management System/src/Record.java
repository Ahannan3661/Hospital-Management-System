public class Record 
{
	Record left;
	Record right;
	PatientHistory patienthistory;
	public Record(Patient p)
	{
		patienthistory = new PatientHistory();
		patienthistory.head = new PatientHistoryNode(p);
		
	}
}
