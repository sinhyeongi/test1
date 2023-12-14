package School_신현기Ver3;

import java.util.ArrayList;

public class StudentDAO {
	private ArrayList<Student> stulist;

	public StudentDAO() {
		// TODO Auto-generated constructor stub
		stulist = new ArrayList<Student>();
	}
	//stulist사이즈 리턴
	public int GetSize() {
		return stulist.size();
	}
	//파일 불러오기
	public void RoadFile(String data) {
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
	//받아온 아이디 값이 있다면 true
	public boolean CheckStuId(String id) {
		for (Student s : stulist) {
			if (s.getStuId().equals(id))
				return true;
		}
		return false;
	}
	//받아온 id 값이 있다면 해당 인덱스 리턴
	private int StudentIdx(String id) {
		for (int i = 0; i < stulist.size(); i++) {
			if (id.equals(stulist.get(i).getStuId())) {
				return i;
			}
		}
		return -1;
	}
	//전체 유저 데이터 ArrList로 리턴
	public ArrayList<String> AlluserData() {
		if (stulist.size() == 0) {
			System.out.println("데이터가 없습니다 !");
			return null;
		}
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < stulist.size(); i++) {
			list.add(stulist.get(i).getStuNo() + "\t" + stulist.get(i).getStuName() + "\t" + stulist.get(i).getStuId());
		}
		return list;
	}
	//전체 데이터 스트링으로 리턴
	public String SaveData() {
		String data = "";
		for (Student stu : stulist) {
			data += stu.toString() + "\n";
		}
		if (!data.isBlank())
			data = data.substring(0, data.length() - 1);
		return data;
	}
	//서브젝트에서 받아온	No번에 해당하는 학생 정보를 list에 담아 리턴
	public ArrayList<String> SubjectStudentNameId(String data) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < data.split("/").length; i++) {
			String data2 = "";
			for (int i2 = 0; i2 < stulist.size(); i2++) {
				if (Integer.parseInt(data.split("/")[i]) == stulist.get(i2).getStuNo()) {
					data2 += data.split("/")[i] + "\t" + stulist.get(i2).getStuName() + "\t" + stulist.get(i2).getStuId();
					break;
				}
			}
			list.add(data2);
		}
		return list;
	}
	//학번 있는지 체크
	public int Studenthackbun(int id) {
		for (int i = 0; i < stulist.size(); i++) {
			if (id == stulist.get(i).getStuNo()) {
				return i;
			}
		}
		return -1;
	}
	//전체 유저No 리턴
	public String AllUserNo() {
		String s = "";
		for (Student st : stulist) {
			s += st.getStuNo() + "/";
		}
		s = s.substring(0, s.length() - 1);
		return s;
	}
	//학생추가
	public void NewStudent(String id, String name) {
		if (stulist.size() == 0) {
			stulist.add(new Student(1001, name, id));
		} else {
			stulist.add(new Student(stulist.get(stulist.size() - 1).getStuNo() + 1, name, id));
		}
		System.out.println(name + "학생 추가 완료");
	}
	//학생삭제
	public int DeleteStudent(String id) {

		int idx = StudentIdx(id);
		int data = stulist.get(idx).getStuNo();
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
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String data = "";
		for (Student s : stulist) {
			data += s.getStuNo() + "\t" + s.getStuName() + "\t" + s.getStuId() + "\n";
		}
		if (!data.isBlank()) {
			data = data.substring(0, data.length() - 1);
		}
		return data;
	}
}
