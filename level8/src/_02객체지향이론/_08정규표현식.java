package _02객체지향이론;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _08정규표현식 {

	public static void main(String[] args) {
		String acc = "1111-1111-1111";
		String accPattern = "^[^\\s]{4}-[\\d,^\\s]{4}-[\\d,^\\s]{4}$";
		Pattern p = Pattern.compile(accPattern);
		Matcher m = p.matcher(acc);
		
		System.out.println(m.matches() ? "올바른 계좌번호":"틀린 계좌번호");
	}

}
