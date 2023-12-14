package _02객체지향이론;

import java.util.Calendar;

class Test06{
	private int num;
	
	private Test06() {}
	private static Test06 ins = new Test06();
	
	public static Test06 getInstance() {
		return ins;
	}
	public int getNum() {
		return num;
	}
}

public class _10싱글톤객체 {
	public static void main(String[] args) {
		Test06 t = Test06.getInstance();
		System.out.println(t);
		Calendar calendar = Calendar.getInstance();
	}
}
