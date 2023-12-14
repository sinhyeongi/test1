package 콜랙션백터_이론;

import java.util.Scanner;
import java.util.Vector;

import lombok.Setter;


@Setter
class Seat{
	int num;
	boolean check;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return num+","+check;
	}
	public Seat() {
		// TODO Auto-generated constructor stub
	}
	Seat(int i){
		num = i;
	}
	Seat(int i,boolean check){
		num = i;
		this.check = check;
	}
	void SetCheck(boolean ch) {
		check = ch;
	}
}
class SeatDAO{
	Scanner scanner;
	Vector<Seat> list;
	int size;
	public SeatDAO() {}
	public SeatDAO(int size ) {
		scanner = new Scanner(System.in);
		list =new Vector<Seat>(size);
		this.size = size;
		for(int i = 0 ; i < size; i++) {
			list.add(new Seat(i+1));
		}
		
	}
	void run() {
		while(true) {
			printSeat();
			if(chooseSeat() == -1)break; 
			
		}
		
	}
	boolean CheckIdx(int idx) {
		if(idx < 0 || idx >= size) return true;
		return false;
	}
	boolean CheckSeat(int idx) {
		if(list.get(idx).check) return true;
		return false;
	}
	int chooseSeat() {
		System.out.println("예약할 좌석 선택");
		System.out.print(">>");
		int i  = scanner.nextInt()-1;
		if(i == -1) {
			return -1;
		}
		if(CheckIdx(i)) {
			System.out.println("1 ~ "+list.size()+"중 입력해주세요");
			return i;
		}
		if(CheckSeat(i)) {
			System.out.println("이미 예약된 좌석입니다,");
			return i;
		}
		list.get(i).SetCheck(true);;
		System.out.println("예약완료");
		return i;
	}
	void printSeat() {
		for(int i = 0; i < 10 ; i++)
			System.out.print((i+1)+"\t");
		System.out.println();
		for(Seat s : list) {
			if(s.check) {
				System.out.print("◼\t");
			}else {
				System.out.print("☐\t");
			}
		}
		System.out.println();
	}
}
public class _05자리예매 {

	public static void main(String[] args) {
		SeatDAO dao = new SeatDAO(10);
		dao.run();
	}

}
