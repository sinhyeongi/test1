package 콜랙션백터_이론;

import java.util.Arrays;
import java.util.Vector;

public class _04벡터주의사항 {

	public static void main(String[] args) {
		int[] arr = { 1,1,3,2,2,3,3,4,4,5,5,4};
		
		Vector<Integer> list = new Vector<Integer>();
		for(int num : arr) {
			list.add(num);
		}
		
		for(int num : list) {
			System.out.print(num+ " ");
		}
		System.out.println();
		
		for(int i = 0 ; i < arr.length; i++) {
			if(arr[i] == 3) {
				for(int i2 = i; i2 < arr.length-1; i2++) {
					arr[i2] = arr[i2+1];
				}
				arr[arr.length - 1] = 0;
				i--;
			}
		}
//		int[] t = new int[arr.length - count];
//		int idx = 0;
//		for(int i = 0 ; i < arr.length; i++) {
//			if(arr[i] != 3)
//				t[idx++] = arr[i];
//		}
//		arr = t;
		
		System.out.println(Arrays.toString(arr));
		System.out.println(list);
	}

}
