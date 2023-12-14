package School_신현기Ver3;

import lombok.Data;

@Data
public class Student {
	private int stuNo;
	private String stuName;
	private String stuId;
	public Student() {
		// TODO Auto-generated constructor stub
		
	}
	public Student(int stuNo, String stuName, String stuId) {
		// TODO Auto-generated constructor stub
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuId = stuId;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return stuNo+"/"+stuName+"/"+stuId;
	}
}
