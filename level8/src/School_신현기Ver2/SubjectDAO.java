package School_신현기Ver2;

import java.util.ArrayList;

public class SubjectDAO {
	private ArrayList<Subject> sublist;

	public SubjectDAO() {
		// TODO Auto-generated constructor stub
		sublist = new ArrayList<Subject>();
	}
	//세이브 데이터 리턴
	public String SaveData(){
		String data ="";
		for(Subject sub : sublist) {
			data +=sub.toString()+"\n";
		}
		if(!data.isBlank())
			data = data.substring(0,data.length()-1);
		return data;
	}
	// 데이터 로드
	public void RoadFile(String data) {
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
	public void DeleteStudent(int No) {
		for (int i = 0; i < sublist.size(); i++) {
			if (sublist.get(i).getStuNo() == No) {
				sublist.remove(i);
				i--;
			}
		}
	}
	// sublist에 학번과 이름이 같은 것이 있는지 체크
	private boolean CheckStudentSubject(String name, int hack) {
		for (Subject s : sublist) {
			if (s.getStuNo() == hack && s.getSubName().equals(name))
				return true;
		}
		return false;
	}
	// sublist에 이름이 같은 것이 있는지 체크
	public boolean CheckStudentSubject(String name) {
		for (Subject s : sublist) {
			if (s.getSubName().equals(name))
				return false;
		}
		return true;
	}
	//받아온 ArraList를 기준으로 해당 subject값 과 점수 평균을 담아 리턴(점수기준 오름차순)
	public ArrayList<String> UserSubjectData(ArrayList<String> data) {
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<Double> sum = new ArrayList<Double>();
		for (int i = 0; i < data.size(); i++) {
			String dataa ="[";
			double dou = 0;
			int count = 0;
			for (int i2 = 0; i2 < sublist.size(); i2++) {
				if (Integer.parseInt(data.get(i).split("\t")[0]) == sublist.get(i2).getStuNo()) {
					dataa += sublist.get(i2).getSubName() + " " + sublist.get(i2).getScore() + "점,";
					dou += sublist.get(i2).getScore();
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
	//받아온 ArraList를 기준으로 해당 subject값 과 점수 평균을 담아 리턴(이름 오름차순)
	public ArrayList<String> UserSubjectData_Name(ArrayList<String> data) {
		ArrayList<String> d = new ArrayList<String>();
		ArrayList<Double> sum = new ArrayList<Double>();
		for (int i = 0; i < data.size(); i++) {
			String dataa ="[";
			double dou = 0;
			int count = 0;
			for (int i2 = 0; i2 < sublist.size(); i2++) {
				if (Integer.parseInt(data.get(i).split("\t")[0]) == sublist.get(i2).getStuNo()) {
					dataa += sublist.get(i2).getSubName() + " " + sublist.get(i2).getScore() + "점,";
					dou += sublist.get(i2).getScore();
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
	public void NewSubject(int hack, String name) {
		if (CheckStudentSubject(name, hack)) {
			System.err.println("중복된 과목 이름입니다.");
			return;
		}
		sublist.add(new Subject(hack, name, (int) (Math.random() * 51) + 50));
		System.out.println(name + "과목 추가 완료");

	}
	//받아온 과목명에 해당학생 NO리턴
	public String SubjectName(String name) {
		int count = 0;
		String No = "";
		for(Subject s : sublist) {
			if(s.getSubName().equals(name)) {
				No += s.getStuNo()+"/";
				count++;
			}
		}
		if(count == 0) return null;
		No = No.substring(0,No.length()-1);
		return No;
	}
	//서브젝트 이름과 학번을 받아와서 서브리스트에 있다면 해당 인덱스 리턴
	private int SubjectIdx(String name, int hack) {
		for (int i = 0; i < sublist.size(); i++) {
			if (sublist.get(i).getStuNo() == hack && name.equals(sublist.get(i).getSubName()))
				return i;
		}
		return -1;
	}
	//서브트 리스트 하나 삭제
	public void DeleteSubject(int hack, String name) {
		int idx = SubjectIdx(name, hack);
		if (idx == -1) {
			System.err.println(hack + "해당 학번에 " + name + "과목이 존재하지 않습니다.");
			return;
		}
		sublist.remove(idx);
		System.out.println("[과목삭제]과목 삭제 완료");
	}
}
