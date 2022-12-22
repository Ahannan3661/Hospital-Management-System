public class testrun {

	public static void main(String[] args) 
	{
		Hospital h1 = new Hospital(10,10);
		Doctor d1 = new Doctor("abc","defg","123abc","apple");
		Doctor d2 = new Doctor("c","nice","123abc","aids");
		Nurse n1 = new Nurse("obaid","..","123");
		Nurse n2 = new Nurse("obaid","..","124");
		Nurse n3 = new Nurse("obaid","..","128");
		h1.addNurse(n1);
		h1.addNurse(n2);
		h1.addNurse(n3);
		h1.addDoctor(d1);
		h1.addDoctor(d2);
		//h1.deleteDoctor(d1);
		Patient p1 = new inPatient("abc","def","aids","12345");
		Patient p4 = new inPatient("abc","def","apple","12345");
		Patient p2 = new inPatient("abc","ef","aids","12346");
		Patient p3 = new outPatient("abc","defg","apple","12347");
		h1.addPatient(p1);
		h1.addPatient(p2);
		h1.addPatient(p3);
		h1.addPatient(p4);
		h1.displayInPatients();
		System.out.println();
		h1.deleteInPatient((inPatient)p1);
//		System.out.println(h1.searchInPatient((inPatient)p4));
		//h1.displayPatients();
		h1.displayInPatients();
	//	h1.ShowRoomsStatus();


	}

}
