package School_신현기;

import java.util.ArrayList;

public class StudentDAO {
	ArrayList<Student> stulist;

	public StudentDAO() {
		// TODO Auto-generated constructor stub
		stulist = new ArrayList<Student>();
	}

	int GetSize() {
		return stulist.size();
	}

	void RoadFile(String data) {
		if(stulist.size() != 0)
			stulist.clear();
		ArrayList<String> list = new ArrayList<String>(); 
		for(int i = 0 ; i < data.split("\n").length; i++) {
			list.add(data.split("\n")[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			stulist.add(new Student(Integer.parseInt(list.get(i).split("/")[0]), list.get(i).split("/")[1], list.get(i).split("/")[2]));
		}
	}

	boolean CheckStuId(String id) {
		for (Student s : stulist) {
			if (s.stuId.equals(id))
				return true;
		}
		return false;
	}

	int StudentIdx(String id) {
		for (int i = 0; i < stulist.size(); i++) {
			if (id.equals(stulist.get(i).stuId)) {
				return i;
			}
		}
		return -1;
	}

	ArrayList<String> AlluserData() {
		if (stulist.size() == 0) {
			System.out.println("데이터가 없습니다 !");
			return null;
		}
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < stulist.size(); i++) {
			list.add(stulist.get(i).stuNo + "\t" + stulist.get(i).stuName + "\t" + stulist.get(i).stuId);
		}
		return list;
	}

	String SaveData() {
		String data = "";
		for (Student stu : stulist) {
			data += stu.toString() + "\n";
		}
		if (!data.isBlank())
			data = data.substring(0, data.length() - 1);
		return data;
	}

	ArrayList<String> SubjectStudentNameId(String data) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < data.split("/").length; i++) {
			String data2 = "";
			for (int i2 = 0; i2 < stulist.size(); i2++) {
				if (Integer.parseInt(data.split("/")[i]) == stulist.get(i2).stuNo) {
					data2 += data.split("/")[i] + "\t" + stulist.get(i2).stuName + "\t" + stulist.get(i2).stuId;
					break;
				}
			}
			list.add(data2);
		}
		return list;
	}

	

	int Studenthackbun(int id) {
		for (int i = 0; i < stulist.size(); i++) {
			if (id == stulist.get(i).stuNo) {
				return i;
			}
		}
		return -1;
	}

	String AllUserNo() {
		String s = "";
		for (Student st : stulist) {
			s += st.stuNo + "/";
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String data = "";
		for (Student s : stulist) {
			data += s.stuNo + "\t" + s.stuName + "\t" + s.stuId + "\n";
		}
		if (!data.isBlank()) {
			data = data.substring(0, data.length() - 1);
		}
		return data;
	}

	void NewStudent(String id, String name) {
		if (stulist.size() == 0) {
			stulist.add(new Student(1001, name, id));
		} else {
			stulist.add(new Student(stulist.get(stulist.size() - 1).stuNo + 1, name, id));
		}
		System.out.println(name + "학생 추가 완료");
	}

	int DeleteStudent(String id) {
		int idx = StudentIdx(id);
		int data = stulist.get(idx).stuNo;
		if (stulist.size() == 1) {
			stulist.clear();
		} else {
			if (idx == -1)
				return -1;
			stulist.remove(idx);
		}
		System.out.println("학생 삭제완료");
		return data;
	}
}
