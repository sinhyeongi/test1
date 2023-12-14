package _02객체지향이론;

import java.util.ArrayList;
import java.util.Random;

class Monster {
	public static final int DEFAULT = 10;

	private String name; // 몬스터 이름
	private int num; // 몬스터 번호
	private int hp; // 몬스터 체력
	private static int cnt; // 몬스터 갯수
	private boolean dead; // 죽었는지

	public Monster(String name, int num) {
		super();
		this.name = name;
		this.num = num;
		this.hp = DEFAULT;
		this.cnt += 1;
	}

	public void getDamage(int damage) {

		if (damage != 0)
			System.out.println(toString() + "가 [-" + damage + "]");
		else
			System.out.println(toString() + " [miss]");
		if (!dead)
			this.hp -= damage;
		if (this.hp <= 0) {
			System.out.println(toString() + "가 [사망]");
			this.dead = true;
			hp = 0;
			this.cnt -= 1;
		}

	}

	public boolean isDead() {
		return this.dead;
	}

	@Override
	public String toString() {
		return "(%d) %s(%d/%d)".formatted(num, name, hp, DEFAULT);
	}

	public static int getMonsterCnt() {
		return cnt;
	}
}

public class _06스태틱1 {

	public static void main(String[] args) {
		Random rd = new Random();
		Monster orc1 = new Monster("오크", 1);
		Monster orc2 = new Monster("오크", 2);
		Monster orc3 = new Monster("오크", 3);
		ArrayList<Monster> list = new ArrayList<Monster>();
		
		list.add(orc1);
		list.add(orc2);
		list.add(orc3);
		System.out.println(list);
		while (true) {
			for (Monster orc : list) {
				if (orc.isDead() == false) {
					int damage = rd.nextInt(3);
					orc.getDamage(damage);
				}
			}
			System.out.println("=====================");
			if (Monster.getMonsterCnt() == 0)
				break;
		}
		
		System.out.println("==== 공격 종료 ====");
		for (Monster m : list) {
			System.out.println(m);
		}
	}

}
