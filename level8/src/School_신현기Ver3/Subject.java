package School_신현기Ver3;

import lombok.Data;

@Data
public class Subject {
	private int stuNo;
	private String subName;
	private int score;
	
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
