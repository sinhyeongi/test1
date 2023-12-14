package _02객체지향이론;



class Test03{
	public int a;
	static public int b;
	void prin() {
		System.out.println(a+" : "+b);
	}
	public Test03(int a) {
		this.a = a;
	}
	
}
public class _07스태틱2 {

	public static void main(String[] args) {
		Test03 t = new Test03(10);
		t.b = 20;
		t.prin();
		System.out.println(Test03.b);
	}
	
}
