package _02객체지향이론;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class Test05{
	private int a;
	private int b;
	private String c;
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	public String getC() {
		return c;
	}
	public void setC(String c) {
		this.c = c;
	}
	@Override
	public String toString() {
		return "Test05 [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
	
}
public class _09클래스정보 {
	public static void main(String[] args) {
		Test05 t = new Test05();
		System.out.println(t.getClass().getName());
		System.out.println(t.getClass().getSimpleName());
		System.out.println(t.getClass().getPackageName());
		
		try {
			Class<?> myClass = Class.forName(t.getClass().getName());
			for(Method m : myClass.getDeclaredMethods()) {
				System.out.println(m.getName());
			}
			System.out.println("========");
			for(Field f : myClass.getDeclaredFields()) {
				System.out.println(f.getName());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
