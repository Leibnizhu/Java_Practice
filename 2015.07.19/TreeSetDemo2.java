/*TreeSetDemo2---Leibniz.Hu 2015.07.19
* Try TreeSet and its iterator.
* implements Comparable to sort the elements.
@author Leibniz.Hu
@version 1.0
*/
import java.util.*;

class TreeSetDemo2{
	public static void main(String[] args){
		System.out.println("Name sorted by TreeSet, Use Comparable interface:");
		TreeSet<Person> tsTest1 = new TreeSet<Person>(new CmpPersonName());
		tsTest1.add(new Person("Leibniz", 21));
		tsTest1.add(new Person("Mayday", 27));
		tsTest1.add(new Person("Jucy", 25));
		tsTest1.add(new Person("Cudy", 23));
		tsTest1.add(new Person("Cudy", 23)); //The same as the first element.
		
		for(Iterator<Person> it = tsTest1.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		
		System.out.println("\nAge sorted by TreeSet, Use Comparable interface:");
		TreeSet<Person> tsTest2 = new TreeSet<Person>(new CmpPersonAge());
		tsTest2.add(new Person("Leibniz", 21));
		tsTest2.add(new Person("Mayday", 27));
		tsTest2.add(new Person("Jucy", 25));
		tsTest2.add(new Person("Cudy", 23));
		tsTest2.add(new Person("Cudy", 23)); //The same as the first element.
		
		for(Iterator<Person> it = tsTest2.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
	}
}

class CmpPersonName implements Comparator<Person> {
	public int compare(Person obj1, Person obj2) {
		//Person p1 = (Person)obj1;
		//Person p2 = (Person)obj2;
		
		int temp = obj1.getName().compareTo(obj2.getName());
		return temp == 0? obj1.getAge() - obj2.getAge(): temp;
	}
}

class CmpPersonAge implements Comparator<Person> {
	public int compare(Person obj1, Person obj2) {
		//Person p1 = (Person)obj1;
		//Person p2 = (Person)obj2;
		
		int temp = obj1.getAge() - obj2.getAge();
		return temp == 0? obj1.getName().compareTo(obj2.getName()): temp;
	}
}

class Person {
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
	
	/*
	public int compareTo(Object obj) {
		Person p = (Person)obj;
		int temp = name.compareTo(p.getName());
		return temp == 0? age - p.getAge(): temp;
	}
	
	public int hashCode() {
		return this.name.hashCode() + age * 27;
	}
	
	public boolean equals(Object obj) {
		Person p = (Person)obj;
		return name.equals(p.getName()) && age == p.getAge();
	}*/
}
