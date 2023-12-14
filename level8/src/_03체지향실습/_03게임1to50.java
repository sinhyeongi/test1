package _03체지향실습;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.Data;

@Data
class Node2 {
	private int num;

	Node2(int i) {
		num = i;
	}
}

class Game1to50 {
	private ArrayList<ArrayList<Node2>> list;
	private final int SIZE = 25;
	private int count;

	private void init() {
		list = new ArrayList<ArrayList<Node2>>(2);
		int c = 1;
		for (int i = 0; i < 50 / SIZE; i++) {
			list.add(new ArrayList<Node2>(SIZE));
			for (int i2 = 0; i2 < SIZE; i2++) {
				list.get(i).add(new Node2(c++));
			}
		}
		count = 1;
		for (int i = 0; i < list.size(); i++) {
			for (int i2 = 0; i2 < 100; i2++) {
				int r = (int) (Math.random() * SIZE);
				Node2 n = list.get(i).get(0);
				list.get(i).set(0, list.get(i).get(r));
				list.get(i).set(r, n);
			}
		}
	}

	private void Print() {
		int count = 0;
		for (Node2 i : list.get(0)) {
			System.out.printf("%-3d", i.getNum());
			count++;
			if (count == 5) {
				System.out.println();
				count = 0;
			}
		}
	}

	

	private void selectIdx() {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("[" + count + "]의 위치 입력");
			for (int i = 0; i < list.get(0).size(); i++)
				if (list.get(0).get(i).getNum() == count)
					System.out.println("힌트 >> y :" + (i / 5) + " x :" + (i % 5));
			System.out.println("y 입력 >>");
			int idx = scanner.nextInt();
			if (CheckIdx(idx)) {
				throw new ArrayIndexOutOfBoundsException();
			}
			System.out.println("x 입력 >>");
			int idx2 = scanner.nextInt();
		
			if (CheckIdx(idx2)) {
				throw new ArrayIndexOutOfBoundsException();
			}
			idx = idx * 5;
			idx += idx2;

			if (list.get(0).get(idx).getNum() == count) {
				count++;
				for (int i = 0; i < list.size() - 1; i++) {
					if (list.get(i + 1).get(idx).getNum() > count) {
						list.get(0).set(idx, new Node2(list.get(i + 1).get(idx).getNum()));
						break;
					}
				}
				if (list.get(0).get(idx).getNum() < count) {
					list.get(0).set(idx, new Node2(0));
				}
			} else {
				System.err.println("[" + count + "]의 위치가 아닙니다!");
			}
			
		} catch (InputMismatchException e) {
			System.out.println("숫자만 입력해주세요");
		}catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("잘못된 인덱스 입니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	private boolean CheckIdx(int idx) {
		if (idx < 0 || idx > (SIZE / 5) - 1)
			return true;
		return false;
	}

	public void run() {
		init();
		while (true) {
			Print();
			if (count > SIZE * list.size()) {
				System.out.println("게임 종료");
				break;
			}
			selectIdx();
		}
	}

}

public class _03게임1to50 {

	public static void main(String[] args) {
		Game1to50 g = new Game1to50();
		g.run();
	}

}
