package _03체지향실습;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;

@Data
class Node3 {
	private String mark;

	public Node3(String mark) {
		this.mark = mark;
	}
}

@Data
class Player {
	private String name;
	private String mark;
	
	
	public Player(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		mark = name;
	}
}

class TicTakToe {
	private ArrayList<Player> plist;
	private ArrayList<ArrayList<Node3>> list;
	private Player player;
	private Scanner scanner;
	private final int size = 3;
	private int cnt;
	private int textsize = "[ ]".length();
	
	
	public TicTakToe() {
		plist = new ArrayList<Player>();
		for(int i = 1; i <= 2; i++)
			plist.add(new Player("p"+i));
	}
	
	public void run() {
		init();
		while (cnt != size * size) {
			PrintMap();
			if(Input()) break;
			System.out.println("=============");
		}
		scanner.close();
	}
	//입력 및 처리
	private boolean Input() {
		try {
			System.out.println("["+player.getName()+"]님의 차례");
			System.out.print("y입력 (0 ~ 2)\n>>");
			int idx = scanner.nextInt();
			if (CheckIdx(idx)) {
				throw new ArrayIndexOutOfBoundsException();
			}
			System.out.print("x입력 (0 ~ 2)\n>>");
			int idx2 = scanner.nextInt();
			if (CheckIdx(idx2)) {
				throw new ArrayIndexOutOfBoundsException();
			}
			if(CheckMark(idx, idx2)) {
				System.out.println("이미 선택된 장소 입니다.");
				return false;
			}
			list.get(idx).set(idx2, new Node3(player.getName()));
			cnt++;
			if(CheckTotal()) {
				PrintMap();
				System.out.println("["+player.getName()+"]님의 승리");
				return true;
			}else if(cnt == size * size) {
				PrintMap();
				System.out.println("무승부");
				return true;
			}
			ChangePlayer();
		} catch (InputMismatchException e) {
			System.err.println("[숫자]를 입력해주세요");
			scanner.nextLine();
		}catch(ArrayIndexOutOfBoundsException e) {
			System.err.println("0 ~ 2의 값중 입력해주세요");
		}
		return false;
	}
	//가로확인
	private boolean Checkgaro() {
		for(int i = 0 ; i < list.size(); i++) {
			int count = 0;
			for(int i2 = 0 ; i2 < list.get(i).size(); i2++) {
				if(player.getName().equals(list.get(i).get(i2).getMark())) count++;
			}
			if(count == 3) return true;
		}
		return false;
	}
	//세로 확인
	private boolean Checksero() {
		for(int i = 0 ; i < list.size(); i++) {
			int count = 0;
			for(int i2 = 0 ; i2 < list.get(i).size(); i2++) {
				if(player.getName().equals(list.get(i2).get(i).getMark())) count++;
			}
			if(count == 3) return true;
		}
		return false;
	}
	//대각선 확인
	private boolean Checkdegak() {
		int count = 0;
		int count2 = 0;
		int idx = list.size()-1;
		for(int i = 0 ; i < list.size();i++) {
			if(player.getName().equals(list.get(i).get(i).getMark())) {
				count++;
			}
			if(player.getName().equals(list.get(i).get(idx--).getMark())) {
				count2++;
			}
		}
		if(count == 3 || count2 == 3)return true;
		return false;
	}
	//전체 체크
	private boolean CheckTotal() {
		if(Checkgaro()||Checksero()||Checkdegak()) return true;
		return false;
	}
	//플레이어 변경
	private void ChangePlayer() {
		int idx = plist.indexOf(player);
		if(idx == plist.size()-1)idx = 0;
		for(int i = idx ; i < plist.size();i ++) {
			if(!player.getName().equals(plist.get(i).getName())) {
				player = plist.get(i);
				return;
			}
		}
	}
	//마크 가 있는지 체크
	private boolean CheckMark(int idx,int idx2) {
		if(list.get(idx).get(idx2).getMark() != null) return true;
		return false;
	}
	
	//인덱스 값 체크
	private boolean CheckIdx(int idx) {
		if (idx < 0 || idx >= size)
			return true;
		return false;
	}

	private void PrintMap() {
		
		for(int i = 0 ; i < textsize; i++)
			System.out.print(" ");
		for(int i = 0 ; i < list.size(); i++) 
			System.out.print("["+i+"]");
		System.out.println();
		int count =0 ;
		for (ArrayList<Node3> n : list) {
			System.out.print("["+(count++)+"]");
			for (Node3 n2 : n) {
				if (n2.getMark() == null) {
					System.out.print("[ ]");
				} else if (plist.get(0).getName().equals(n2.getMark())) {
					System.out.print("[X]");
				} else {
					System.out.print("[O]");
				}
			}
			System.out.println();
		}
		System.out.println("============");
	}

	private void init() {
		
		scanner = new Scanner(System.in);
		list = new ArrayList<ArrayList<Node3>>(size);
		for (int i = 0; i < size; i++) {
			list.add(new ArrayList<Node3>(size));
			for (int i2 = 0; i2 < size; i2++)
				list.get(i).add(new Node3(null));
		}
		player = new Player(plist.get(0).getName());
	}
}

public class _04틱택톡 {

	public static void main(String[] args) {
		TicTakToe t = new TicTakToe();
		t.run();
	}

}
