/*TreeSetDemo1---Leibniz.Hu 2015.07.19
* Try TreeSet and its iterator.
* implements Comparable to sort the elements.
@author Leibniz.Hu
@version 1.0
*/
import java.util.*;

class TreeSetDemo1{
	public static void main(String[] args){
		System.out.println("Name sorted by TreeSet, Use Comparable interface:");
		TreeSet tsTest = new TreeSet(); //Ordered LinkedHashSet
		tsTest.add(new Person("Leibniz", 21));
		tsTest.add(new Person("Mayday", 27));
		tsTest.add(new Person("Jucy", 25));
		tsTest.add(new Person("Cudy", 23));
		tsTest.add(new Person("Cudy", 23)); //The same as the first element.
		
		for(Iterator it = tsTest.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
	}
}


class Person implements Comparable {
	private int age;
	private String name;
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getAge() {
		return age;
	}
	
	Person(String name, int age) {
		setName(name);
		setAge(age);
	}
	
	public String toString() {
		return name + "\t : " + age;
	}
	
	public int compareTo(Object obj) {
		Person p = (Person)obj;
		int temp = name.compareTo(p.getName());
		return temp == 0? age - p.getAge(): temp;
	}
	
	/*
	public int hashCode() {
		return this.name.hashCode() + age * 27;
	}
	
	public boolean equals(Object obj) {
		Person p = (Person)obj;
		return name.equals(p.getName()) && age == p.getAge();
	}*/
}
