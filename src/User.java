
public class User {
	String user_id;
	String name;
	long   phoneNumber;
	String city;
	
	User(String id,String nme,long phn,String cty) {
		user_id = id;
		name = nme;
		phoneNumber = phn;
		city = cty;
	}
	
	String getid() {
		return user_id;
	}
	String getname() {
		return name;
	}
	long getnum() {
		return phoneNumber;
	}
	String getcity() {
		return city;
	}
}