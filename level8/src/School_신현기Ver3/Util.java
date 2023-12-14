package School_신현기Ver3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
	private static Scanner scanner;
	private String s = "src/"+this.getClass().getPackageName()+"/";
	
	private Util() {
		// TODO Auto-generated constructor stub
		scanner = new Scanner(System.in);
		
	}
	//인스턴스 
	private static Util Util = new Util();
	//싱글톤
	public static Util getInstance() {
		return Util;
	}
	public static int getInt(String s) {
		int i;
		try {
			System.out.print(s + "\n>>");
			i = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("숫자만 입력해주세요");
			scanner.nextLine();
			return -1;
		}
		return i;
	}
	public static String getString(String s) {
		String data ;
		System.out.print(s+"\n>>");
		data = scanner.next();
		scanner.nextLine();
		return data;
	}
	private void CheckFile(File f1, File f2) {
		
		try {
			if (f1.exists() == false)
				f1.createNewFile();
			if (f2.exists() == false)
				f2.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void SaveText(String data1, String data2) {
		File stuf = new File(s + "student.txt");
		File subf = new File(s + "subject.txt");
		CheckFile(stuf, subf);
		try(FileWriter fw = new FileWriter(stuf);
				FileWriter fw2 = new FileWriter(subf)){
			fw.write(data1);
			fw2.write(data2);
			System.out.println("파일 저장 성공");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<String> RoadText() {
		File stuf = new File(s + "student.txt");
		File subf = new File(s + "subject.txt");
		CheckFile(stuf, subf);
		ArrayList<String> list = new ArrayList<String>();
		try (BufferedReader stbrf = new BufferedReader(new FileReader(stuf));
				BufferedReader subrf = new BufferedReader(new FileReader(subf))) {
			int i;
			String t = "";
			while ((i = stbrf.read()) != -1) {
				t += (char)i;
			}
			list.add(t);
			t ="";
			while ((i = subrf.read()) != -1) {
				t += (char)i;
			}
			list.add(t);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
