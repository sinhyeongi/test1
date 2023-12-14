package School_신현기Ver2;

import java.util.ArrayList;

public class Controller {
	private Util u;
	private StudentDAO studao;
	private SubjectDAO subdao;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		u = new Util();
		studao = new StudentDAO();
		subdao = new SubjectDAO();
	}
	
	//파일 로드
	private void RoadFile(ArrayList<String> data) {
		studao.RoadFile(data.get(0));
		subdao.RoadFile(data.get(1));
		System.out.println("파일 로드 완료");
	}
	//학생 추가
	private void NewStudent() {
		String id = u.getString("id를 입력해주세요");
		if(studao.CheckStuId(id)) {
			System.err.println("중복된 id입니다.");
			return;
		}
		String name = u.getString("이름을 입력해주세요");
		studao.NewStudent(id, name);
	}
	//전체 학생출력
	private void PrintAllStudent() {
		if(studao.GetSize() == 0) {
			System.out.println("데이터가 없습니다!");
			return;
		}
		ArrayList<String >list = studao.AlluserData();
		if(list.size() == 0)return;
		list = subdao.UserSubjectData(list);
		for(int i2 = 0 ; i2< list.size(); i2++) { 
			System.out.println(list.get(i2));
			System.out.println("-----------------------");
		}
	}
	//학생삭제
	private void DeleteStudent() {
		if(studao.GetSize() == 0) {
			System.err.println("데이터가 없습니다!");
			return;
		}
		String id = u.getString("[학생삭제]아이디를 입력하세요");
		if(studao.CheckStuId(id) == false) {
			System.err.println("[학생삭제]잘못된 아이디 입니다.");
			return;
		}
		subdao.DeleteStudent(studao.DeleteStudent(id));
	}
	//과목추가
	private void NewSubject() {
		int hackbun = u.getInt("학번을 입력하세요");
		if(hackbun == -1) return;
		if(studao.Studenthackbun(hackbun) == -1) {
			System.err.println("[과목추가]잘못된 학번 입니다.");
			return;
		}
		String name = u.getString("과목 이름을 입력해주세요");
		subdao.NewSubject(hackbun, name);
	}	
	//subject삭제
	private void DeleteSubject() {
		int hackbun = u.getInt("학번을 입력하세요");
		if(hackbun == -1) return;
		if(studao.Studenthackbun(hackbun) == -1) {
			System.err.println("[과목삭제]잘못된 학번 입니다.");
			return;
		}
		String name = u.getString("과목 이름을 입력해주세요");
		subdao.DeleteSubject(hackbun, name);
	}
	//메인실행부분
	public void run() {
		RoadFile(u.RoadText());
		while(true) {
			mainMenu();
			int i = u.getInt("메뉴 선택");
			if(i == 0 ) {
				System.out.println("프로그램 종료");
				break;
			}
			if(CheckInp(i)) {
				System.out.println("0 ~ 8번 중 입력해주세요");
				continue;
			}
			if( i == 1) {
				NewStudent();
			}else if( i == 2) {
				//[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제 
				DeleteStudent();
			}else if(i == 3) {
				////학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
				NewSubject();
			}
			else if(i == 4) {
				//[4]과목삭제"); // 학번 입력후 과목삭제
				DeleteSubject();
			}
			else if(i == 5) {
				PrintAllStudent();
			}
			else if(i == 6) {
				System.out.println("[ 한 과목 학생 목록 ]");
				String name = u.getString("찾을 과목 이름 입력");
				if(subdao.CheckStudentSubject(name)) {
					System.out.println("해당 과목에 학생이없습니다.");
				}
				String data = subdao.SubjectName(name);
				ArrayList<String> list = studao.SubjectStudentNameId(data);
				list = subdao.UserSubjectData_Name(list);
				for(int i2 = 0 ; i2< list.size(); i2++) { 
					System.out.println(list.get(i2));
					System.out.println("-----------------------");
				}
			}
			else if(i == 7) {
				if(studao.GetSize() == 0) {
					System.err.println("저장할 데이터가 없습니다!");
					continue;
				}
				u.SaveText(studao.SaveData(), subdao.SaveData());
			}
			else if(i == 8) {
				RoadFile(u.RoadText());
			}
			
		}
	}
	private boolean CheckInp(int i) {
		if(i < 0 || i > 8) return true;
		return false;
	}
	private void mainMenu() {
		System.out.println("[1]학생추가"); // 학번(1001) 자동증가 : 학생id 중복 불가  
		System.out.println("[2]학생삭제"); // 학생 id 입력후 삭제 과목도 같이 삭제 
		System.out.println("[3]과목추가"); //학번 입력후 점수 랜덤 50-100 : 과목이름 중복 저장불가능
		System.out.println("[4]과목삭제"); // 학번 입력후 과목삭제 
		System.out.println("[5]전체학생목록"); // 점수로 출력
		System.out.println("[6]과목별학생목록"); // 과목이름 입력받아서 해당 과목 학생이름과 과목점수 출력 (오름차순 이름순) 
		System.out.println("[7]파일저장");
		System.out.println("[8]파일로드");
		System.out.println("[0] 종료");
	}
}
