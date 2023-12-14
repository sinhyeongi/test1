package 콜랙션백터_이론;

class MyVector{
	private int[] arr;
	private int size;
	private int capcity;
	MyVector(){
		arr = new int[10];
		capcity = 10;
	}
	private void NewArr()  {
		int[] copy =arr;
		capcity += 10;
		arr = new int[capcity];
		for(int i = 0 ; i < copy.length;i++)
			arr[i] = copy[i];
	}
	void add(int idx,int value) throws ArrayIndexOutOfBoundsException{
		if(CheckIndx(idx)) throw new ArrayIndexOutOfBoundsException();
		if(size >= capcity) {
			NewArr();
		}
		int[] c = arr;
		arr = new int[c.length+1];
		int cidx = 0;
		for(int i = 0; i < arr.length;i++) {
			if(idx == i) {
				arr[i] = value;
				continue;
			}
			arr[i] = c[cidx++];
		}
		size++;
	}
	void add(int value){
		if(size >= capcity) {
			NewArr();
		}
		arr[size++] = value;
	}
	private boolean CheckIndx(int idx) {
		if(idx < 0 || idx >= size)return true;
		return false;
	}
	void remove(int idx)throws ArrayIndexOutOfBoundsException {
		if(CheckIndx(idx)) throw new ArrayIndexOutOfBoundsException();
		int[] arr2 = arr;
		arr = new int[capcity];
		int arridx = 0;
		for(int i = 0 ; i < size; i++) {
			if(i != idx) {
				arr[arridx++] = arr2[i];
			}
		}
		size--;
	}
	
	int Capcity() {
		return capcity;
	}
	int size() {
		return size;
	}
	void set(int idx, int value)throws ArrayIndexOutOfBoundsException {
		if(CheckIndx(idx)) throw new ArrayIndexOutOfBoundsException();
		arr[idx] = value;
	}
	int get(int idx)throws ArrayIndexOutOfBoundsException {
		if(CheckIndx(idx)) throw new ArrayIndexOutOfBoundsException();
		return arr[idx];
	}
	void Clear() {
		arr = new int[capcity];
		size = 0;
	}
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i < size; i++) {
			s += arr[i]+",";
		}
		if(!s.isBlank())
			s = s.substring(0,s.length()-1);
		s = "["+s+"]";
		return s;
	}
	
}
public class _03백터클래스구현 {

	public static void main(String[] args) {
		MyVector v1 = new MyVector();
		System.out.println(v1);
		System.out.println(v1.size());
		System.out.println(v1.Capcity());
		
		for(int i = 0 ; i < 10; i++)
			v1.add((i+1)*10);
		
		
		System.out.println(v1.size());
		System.out.println(v1.Capcity());
		System.out.println(v1.get(1));
		
		v1.set(1,1000);
		System.out.println(v1);
		
		v1.add(1,20);
		System.out.println(v1);
		
		v1.remove(3);
		System.out.println(v1);
		System.out.println(v1.size());
		System.out.println(v1.Capcity());
		
		v1.Clear();
		System.out.println(v1);
		System.out.println(v1.size());
		System.out.println(v1.Capcity());
		
	}

}
