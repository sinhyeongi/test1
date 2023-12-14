package School_신현기;

import java.util.ArrayList;

public class SubjectDAO {
	ArrayList<Subject> sublist;

	public SubjectDAO() {
		// TODO Auto-generated constructor stub
		sublist = new ArrayList<Subject>();
	}
	String SaveData(){
		String data ="";
		for(Subject sub : sublist) {
			data +=sub.toString()+"\n";
		}
		if(!data.isBlank())
			data = data.substring(0,data.length()-1);
		return data;
	}
	// 데이터 로드
	void RoadFile(String data) {
		if(sublist.size() != 0)
			sublist.clear();
		ArrayList<String>list = new ArrayList<String>();
		for(int i = 0 ; i < data.split("\n").length; i++) {
			list.add(data.split("\n")[i]);
		}
		for (int i = 0; i < list.size(); i++) {
			sublist.add(new Subject(Integer.parseInt(list.get(i).split("/")[0]), list.get(i).split("/")[1], Integer.parseInt(list.get(i).split("/")[2])));
		}
	}

	// student삭제시 subject삭제
	void DeleteStudent(int No) {
		for (int i = 0; i < sublist.size(); i++) {
			if (sublist.get(i).stuNo == No) {
				sublist.remove(i);
				i--;
			}
		}
	}

	// sublist에 학번과 이름이 같은 것이 있는지 체크
	boolean CheckStudentSubject(String name, int hack) {
		for (Subject s : sublist) {
			if (s.stuNo == hack && s.subName.equals(name))
				return true;
		}
		return false;
	}
	boolean CheckStudentSubject(String name) {
		for (Subject s : sublist) {
			if (s.subName.equals(name))
				return false;
		}
		return true;
	}
	
	ArrayList<String> UserSubjectData(ArrayList<String> data) {
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<Double> sum = new ArrayList<Double>();
		for (int i = 0; i < data.size(); i++) {
			String dataa ="[";
			double dou = 0;
			int count = 0;
			for (int i2 = 0; i2 < sublist.size(); i2++) {
				if (Integer.parseInt(data.get(i).split("\t")[0]) == sublist.get(i2).stuNo) {
					dataa += sublist.get(i2).subName + " " + sublist.get(i2).score + "점,";
					dou += sublist.get(i2).score;
					count++;
				}
			}
			if (dataa.length() > 2)
				dataa = dataa.substring(0, dataa.length() - 2);
			dataa += "]";
			if (dou == 0) {
				sum.add(0.00);
			} else {
				sum.add(Double.parseDouble(String.format("%.2f", dou / count)));
			}
			dataa = data.get(i) + "\n" + dataa;
			dataa += "\n총점 : " + (dou == 0 ? "0.00" : String.format("%.2f", dou / count));
			d.add(dataa);
		}
		for (int i = 0; i < d.size(); i++) {
			for (int i2 = i; i2 < d.size(); i2++) {
				if (sum.get(i) < sum.get(i2)) {
					double c = sum.get(i);
					sum.set(i, sum.get(i2));
					sum.set(i2, c);
					String s = d.get(i);
					d.set(i,d.get(i2));
					d.set(i2, s);
				}
			}
		}
		return d;
	}
	ArrayList<String> UserSubjectData_Name(ArrayList<String> data) {
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<Double> sum = new ArrayList<Double>();
		for (int i = 0; i < data.size(); i++) {
			String dataa ="[";
			double dou = 0;
			int count = 0;
			for (int i2 = 0; i2 < sublist.size(); i2++) {
				if (Integer.parseInt(data.get(i).split("\t")[0]) == sublist.get(i2).stuNo) {
					dataa += sublist.get(i2).subName + " " + sublist.get(i2).score + "점,";
					dou += sublist.get(i2).score;
					count++;
				}
			}
			if (dataa.length() > 2)
				dataa = dataa.substring(0, dataa.length() - 2);
			dataa += "]";
			if (dou == 0) {
				sum.add(0.00);
			} else {
				sum.add(Double.parseDouble(String.format("%.2f", dou / count)));
			}
			dataa = data.get(i) + "\n" + dataa;
			dataa += "\n총점 : " + (dou == 0 ? "0.00" : String.format("%.2f", dou / count));
			d.add(dataa);
		}
		for (int i = 0; i < d.size(); i++) {
			for (int i2 = i+1; i2 < d.size()-1; i2++) {
				if(d.get(i).length() == d.get(i2).length()) {
					for(int i3 = 0; i3 < d.get(i).length();i3++) {
						if(d.get(i).charAt(i3) > d.get(i2).charAt(i3)) {
							String c = d.get(i);
							d.set(i, d.get(i2));
							d.set(i2, c);
							break;
						}
					}
				}else if(d.get(i).length() > d.get(i2).length()){
					String c = d.get(i);
					d.set(i, d.get(i2));
					d.set(i2, c);
				}
			}
		}
		return d;
	}
	// 새로운 subject생성
	void NewSubject(int hack, String name) {
		if (CheckStudentSubject(name, hack)) {
			System.err.println("중복된 과목 이름입니다.");
			return;
		}
		sublist.add(new Subject(hack, name, (int) (Math.random() * 51) + 50));
		System.out.println(name + "과목 추가 완료");

	}
	//과목명 학생 NO리턴
	String SubjectName(String name) {
		int count = 0;
		String No = "";
		for(Subject s : sublist) {
			if(s.subName.equals(name)) {
				No += s.stuNo+"/";
				count++;
			}
		}
		if(count == 0) return null;
		No = No.substring(0,No.length()-1);
		return No;
	}
	int SubjectIdx(String name, int hack) {
		for (int i = 0; i < sublist.size(); i++) {
			if (sublist.get(i).stuNo == hack && name.equals(sublist.get(i).subName))
				return i;
		}
		return -1;
	}

	void DeleteSubject(int hack, String name) {
		int idx = SubjectIdx(name, hack);
		if (idx == -1) {
			System.err.println(hack + "해당 학번에 " + name + "과목이 존재하지 않습니다.");
			return;
		}
		sublist.remove(idx);
		System.out.println("[과목삭제]과목 삭제 완료");
	}
}
