package School_신현기;

public class Subject {
	int stuNo;
	String subName;
	int score;
	
	public Subject() {
		// TODO Auto-generated constructor stub
	}
	public Subject(int stuNo, String subName, int score) {
		// TODO Auto-generated constructor stub
		this.stuNo = stuNo;
		this.subName = subName;
		this.score = score;
		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return stuNo + "/"+subName+"/"+score;
	}
}
