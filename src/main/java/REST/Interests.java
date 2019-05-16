package REST;


public class Interests {
	int userId;
	String subject; //subject from school
	String sport;	//favourite sport discipline

	void setSubject(String subject) {
		this.subject=subject;
	}
	String getSubject() {
		return subject;
	}
	
	void setSport(String sport) {
		this.sport=sport;
	}
	String getSport() {
		return sport;
	}
	
	void setUserId(int userId) {
		this.userId=userId;
	}
	int getUserId() {
		return userId;
	}
}
