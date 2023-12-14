package _03체지향실습;

import java.util.ArrayList;
import java.util.Vector;


class Member {
	private int custno;				// 회원번호
	private String custname;		// 회원성명
	private String phone;			// 회원전화
	private String address;			// 통신사
	private String joindate;		// 가입일자
	private String grade;			// 고객등급
	private String city;			// 거주도시
	
	
	public String getCustname() {
		return custname;
	}


	public int getCustno() {
		return custno;
	}
	

	@Override
	public String toString() {
		return custno + "\t" + custname;
	}


	public Member(int custno, String custname, String phone, String address, String joindate, String grade, String city) {
		this.custno = custno;
		this.custname = custname;
		this.phone = phone;
		this.address = address;
		this.joindate = joindate;
		this.grade = grade;
		this.city = city;
	}
}

class Money{
	 private int custno;			// 회원번호
	 private int saleno; 			// 판매번호
	 private int pcost;				// 단가
	 private int amount;			// 수량
	 private int price;				// 가격(매출)
	 private String pcode;			// 상품코드
	 private String sdate;			// 판매일자
	 public Money() {
		 
	 }
	 
	 
	 public int getCustno() {
		return custno;
	}
	 

	public int getPcost() {
		return pcost;
	}


	public int getAmount() {
		return amount;
	}


	public Money(int custno, int saleno, int pcost, int amount, int price, String pcode, String sdate) {
		this.custno = custno;
		this.saleno = saleno;
		this.pcost = pcost;
		this.amount = amount;
		this.price = price;
		this.pcode = pcode;
		this.sdate = sdate;
	}
}

class Manager{
	private Vector<Member> memberList = new Vector<Member>();
	private Vector<Money> moneyList = new Vector<Money>(); 	
	
	public void init() {
		memberList.add(new Member(100001, "김행복", "010-1111-2222", "SK", "20151202", "A", "01"));
		memberList.add(new Member(100002, "이축복", "010-1111-3333", "SK", "20151206", "B", "01"));
		memberList.add(new Member(100003, "장믿음", "010-1111-4444", "SK", "20151001", "B", "30"));
		memberList.add(new Member(100004, "최사랑", "010-1111-5555", "SK", "20151113", "A", "30"));
		memberList.add(new Member(100005, "진평화", "010-1111-6666", "SK", "20151225", "B", "60"));
		memberList.add(new Member(100006, "차공단", "010-1111-7777", "SK", "20151211", "C", "60"));
		
		moneyList.add(new Money(100001, 20160001, 500, 5, 2500, "A001", "20160101"));
		moneyList.add(new Money(100001, 20160002, 1000, 4, 4000, "A002", "20160101"));
		moneyList.add(new Money(100001, 20160003, 500, 3, 1500, "A008", "20160101"));
		moneyList.add(new Money(100002, 20160004, 2000, 1, 2000, "A004", "20160102"));
		moneyList.add(new Money(100002, 20160005, 500, 1, 500, "A001", "20160103"));
		moneyList.add(new Money(100003, 20160006, 1500, 2, 3000, "A003", "20160103"));
		moneyList.add(new Money(100004, 20160007, 500, 2, 1000, "A001", "20160104"));
		moneyList.add(new Money(100004, 20160008, 300, 1, 300, "A005", "20160104"));
		moneyList.add(new Money(100004, 20160009, 600, 1, 600, "A006", "20160104"));
		moneyList.add(new Money(100004, 20160010, 3000, 1, 3000, "A007", "20160106"));
		
		ArrayList<data> list = new ArrayList<data>(); 
		for(int i = 0 ; i < memberList.size();i++) {
			int sum = 0;
			for(int i2 = 0 ; i2 < moneyList.size();i2++) {
				if(memberList.get(i).getCustno() == moneyList.get(i2).getCustno()&& moneyList.get(i2).getAmount() != 0) {
					sum += moneyList.get(i2).getPcost() * moneyList.get(i2).getAmount();
				}
			}
			if(sum != 0)
				list.add(new data(String.valueOf(memberList.get(i).getCustno()),memberList.get(i).getCustname(),sum));
		}
		for(int i = 0 ; i < list.size(); i++) {
			for(int i2 = i +1; i2 < list.size(); i2++)
				if(list.get(i).getPrice() < list.get(i2).getPrice()) {
					data n = list.get(i);
					list.set(i, list.get(i2));
					list.set(i2, n);
				}
		}
		for(data s : list) {
			System.out.println(s);
			System.out.println("---------------------");
		}
	}
	
}

class data{
	private String No;
	private String name;
	private int price;
	data(String No, String name, int price){
		this.No = No;
		this.name = name;
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return  No+"\t"+ name + "\t" + price;
	}
	
	
}
public class _01캡슐화실습 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Manager ma = new Manager();
		ma.init();
	}

}
