package _03체지향실습;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;




@Data
class Node{
	private String front;
	private String back;
	public Node(String front) {
		this.front = front;
		back = "";
	}
	public String getFront() {
		return front;
	}
	public void setFront(String front) {
		this.front = front;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	@Override
	public String toString() {
		return back.equals("") ? "[ ]" : "[" + back+"]" ;
	}
	
}
class MemotyGame{
	private ArrayList<Node> list;
	final private int size;
	private int count = 0;
	private Scanner scanner;
	
	//생성자
	public MemotyGame(int size) {
		// TODO Auto-generated constructor stub
		this.size = size;
		list = new ArrayList<Node>(size);
		Setting();
	}
	//초기 생성 세팅
	private void Setting() {
		scanner = new Scanner(System.in);
		int ra = size / 2;
		for(int i = 0 ; i < size; i++) {
			list.add(new Node(String.valueOf(((int)(Math.random()*ra)) + 1)));
			int count = 0;
			for(int i2 = 0 ; i2 < list.size();i2++) {
				if(list.get(i).getFront().equals(list.get(i2).getFront())) {
					count++;
					if(count > 2) {
						break;
					}
				}
			}
			if(count > 2) {
				list.remove(i);
				i--;
			}
		}
		
	}
	
	//출력
	private void PrintBack() {
		for(int i = 1 ; i <= size; i++) {
			System.out.print("["+i+"]");
		}
		System.out.println();
		System.out.println("------------------------------");
		for(int i = 0 ; i < list.size();i++) {
			System.out.print(list.get(i).toString());
		}
		System.out.println();
		System.out.println("------------------------------");
	}
	private void PrintBack(int idx, int idx2) {
		System.out.println("------------------------------");
		for(int i = 0 ; i < list.size();i++) {
			if(i == idx || i == idx2) {
				System.out.print("["+list.get(i).getFront()+"]");
			}else {
				System.out.print(list.get(i).toString());
			}
		}
		System.out.println();
		System.out.println("------------------------------");
	}
	//인덱스 입력 및 처리
	private void InpIndx() {
		System.out.print("인덱스1 을 입력하세요 : (0 ~ 9)\n>>");
		int idx = getInt();
		if(idx == -1 )return;
		if(!list.get(idx).getBack().equals("")) {
			System.out.println("이미 맞춘 장소입니다.");
			return;
		}
		System.out.print("인덱스2 을 입력하세요 : (0 ~ 9)\n>>");
		int idx2 = getInt();
		if(idx2 == -1 )return;
		if(!list.get(idx2).getBack().equals("")) {
			System.out.println("이미 맞춘 장소입니다.");
			return;
		}
		if(idx == idx2) {
			System.err.println("서로 다른 인덱스를 입력하세요");
			return;
		}
		
		PrintBack();
		System.out.println("=====================================");
		if(list.get(idx).getFront().equals(list.get(idx2).getFront())) {
			System.out.println("정답");
			System.out.println("=====================================");
			list.get(idx).setBack(list.get(idx).getFront());
			list.get(idx2).setBack(list.get(idx2).getFront());
			count+=2;
			return;
		}
		System.err.println("오답");
		
		System.out.println("=====================================");
		PrintBack(idx,idx2);
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//인덱스 입력 처리
	private int getInt() {
		int i;
		try {
			i = scanner.nextInt();
		}catch(InputMismatchException e) {
			System.err.println("숫자만 입력해주세요");
			scanner.nextLine();
			return -1;
		}
		if(CheckIndx(i)) {
			System.err.println("0 ~ 9 중 입력해주세요");
			i = -1;
		}		
		return i;
	}
	//인덱스범위 체크
	private boolean CheckIndx(int i) {
		if( i < 0 || i >= size) return true;
		return false;
	}
	//메인 실행 부분
	public void run() {
		while(true) {
//			for(int i = 0 ; i < list.size();i++) {
//					System.out.print("["+list.get(i).getFront()+"]");
//			}
			System.out.println();
			PrintBack();
			if(count == size) {
				System.out.println("게임 종료");
				break;
			}
			InpIndx();
		}
		
	}
	
}
public class _02기억력게임 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MemotyGame g = new MemotyGame(10);
		g.run();
	}

}
