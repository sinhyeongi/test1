package 콜랙션백터_이론;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class _02Vector클래스 {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(1,2,3));
		Collections.sort(list,Collections.reverseOrder());
		System.out.println(list.toString());
		Collections.sort(list);
		System.out.println(list.toString());
	}

}
//add remove idx,value