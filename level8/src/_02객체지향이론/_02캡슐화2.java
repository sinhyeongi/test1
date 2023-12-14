package _02객체지향이론;

import lombok.Getter;

@Getter
class Product{
	private String data;
	private String name;
	private int price;
	public Product(String data, String name, int price) {
		this.data = data;
		this.name = name;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Product [data=" + data + ", name=" + name + ", price=" + price + "]";
	}

	public void setPrice(int price) {
		if(price < 0 ) {
			System.out.println("0보다 큰 값만 가능");
			return;
		}
		this.price = price;
	}
	
}
public class _02캡슐화2 {

	public static void main(String[] args) {
		Product pr = new Product("data","name",1212);
		System.out.println(pr);
		
	}

}
