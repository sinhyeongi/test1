package _03체지향실습;

import java.util.ArrayList;

class Card {
	// 포커카드
	private int num;
	private String shap;
	private static int count = 0;
	private static String[] shapes = { "♡", "◇", "♧", "♤" };
	private static int garoSize = 150;
	private static int seroSize = 200;
	
	public Card() {}
	public Card(int num, String s) {
		this.num = num;
		shap = s;
	}
	public Card(int num) {
		if (num % 10 == 0) {
			this.num = 10;
		} else
			this.num = num % 10;
		this.shap = shapes[(count++) / 10];

	}

	public String getShap() {
		return shap;
	}

	public int getNum() {
		return num;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.shap + " : " + num;
	}

	
	//
}

class Player1 {
	private String name;
	private Card c[];
	
	//가지는 카드 갯수
	private final int CardCount = 2;
	Player1(String name) {
		this.name = name;
		c = new Card[CardCount];
	}
	Player1(String name, Card[] c) {
		this.name = name;
		this.c = c;
	}
	public String getName() {
		return name;
	}

	public void SetCard(Card[] c) {
		this.c = c;
	}
	//특정 무늬 카드가 있는지 확인
	public boolean isString(String s) {
		for (int i = 0; i < c.length; i++) {
			if (s.equals(c[i].getShap()))
				return true;
		}
		return false;
	}
	//가지고 있는 카드 리턴
	public Card[] GetCard() {
		return c;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name + "(" + c[0].toString() + " " + c[1].toString() + ") " + TotalNum();
	}
	//카드 합한 값 리턴
	public int TotalNum() {
		int sum = 0;
		for (int i = 0; i < c.length; i++)
			sum += c[i].getNum();

		return sum;
	}
}

class GameManager {
	private ArrayList<Card> cardDeck;
	private final int SIZE = 40;
	private ArrayList<Player1> plist;
	//플레이어 인원 수
	private final int playerCount = 5;
	private void Setting() {
		cardDeck = new ArrayList<Card>();
		plist = new ArrayList<Player1>();
		for (int i = 1; i <= SIZE; i++) {
			cardDeck.add(new Card(i));
		}
		Temp();

		// test
//		Card[] c3 = new Card[2];
//		c3[0] = new Card(5,"♡");
//		c3[1] = new Card(5,"◇");
//		plist.add(new Player1("p3",c3));
//		c3 = new Card[2];
//		c3[0] = new Card(5,"♧");
//		c3[1] = new Card(5,"♤");
//		plist.add(new Player1("p4",c3));
		
		for(int i = 0 ; i < playerCount;i++)
		plist.add(new Player1("p"+(i+1)));
		
		for (int i = 0; i < plist.size(); i++) {
			Card c[] = new Card[2];
			for (int i2 = 0; i2 < c.length; i2++) {
				int r = (int) (Math.random()) * cardDeck.size();
				c[i2] = cardDeck.get(r);
				cardDeck.remove(r);
			}
			plist.get(i).SetCard(c);
		}

	}

	// 승자 출력
	private String Winer() {
		/*
		 *  2카드의 합산 값이 가장 큰 사람이 혼자 라면 승리
		 */
		String v = "";
		int max = 0;
		int count = 0;
		for (int i = 0; i < plist.size(); i++) {
			if (max < plist.get(i).TotalNum()) {
				v = plist.get(i).getName();
				max = plist.get(i).TotalNum();
			} else if (max == plist.get(i).TotalNum()) {
				count++;
			}
		}
		if (count == 0)
			return v + " 의 승리";
		
		/*
		 * 1. 2카드의 합산 값이 가장 큰 사람이 여러명이다.
		 * 2. 그 사람들의 n번째 카드의 값이 큰 사람이 혼자 라면 승리자
		 */
		ArrayList<Player1> max_user = new ArrayList<Player1>();
		for (int i = 0; i < plist.size(); i++) {
			if (max == plist.get(i).TotalNum())
				max_user.add(plist.get(i));
		}
		for (int i2 = 0; i2 < max_user.get(0).GetCard().length; i2++) {
			v = "";
			count = 0;
			max = 0;
			for (int i = 0; i < max_user.size(); i++) {
				if (max_user.get(i).GetCard()[i2].getNum() > max) {
					v = max_user.get(i).getName() + " 의 승리";
					max = max_user.get(i).GetCard()[i2].getNum();
				} else if (max == max_user.get(i).GetCard()[i2].getNum())
					count++;
			}
			if (count == 0)
				return v;
		}
		/*
		 * 1. 2카드의 값이 같은 사람 2명이 있다면 
		 * 2. ♤ 를 가지고 있는 사람의 승리
		 */
		for (int i = 0; i < max_user.size(); i++) {
			if (max_user.get(i).isString("♤"))
				return max_user.get(i).getName() + " 의 승리";
		}
		
		return "무승부";

	}
	//카드 섞기 및 출력
	private void Temp() {
		for (int i = 0; i < cardDeck.size(); i++) {
			int r2 = (int) (Math.random() * SIZE - 1) + 1;
			Card d = cardDeck.get(0);
			cardDeck.set(0, cardDeck.get(r2));
			cardDeck.set(r2, d);
		}
		for (Card d : cardDeck) {
			System.out.println(d);
		}
	}
	//메인 실행부분
	public void playGame() {
		Setting();
		System.out.println("================");
		for (Player1 p : plist) {
			System.out.println(p);
		}
		System.out.println("================");
		System.out.println(Winer());
		System.out.println("================");
	}
}

public class _05카드게임 {

	public static void main(String[] args) {
		GameManager gm = new GameManager();
		gm.playGame();
	}

}
