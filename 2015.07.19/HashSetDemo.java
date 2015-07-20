/*HashSetDemo---Leibniz.Hu 2015.07.19
* Try HashSet and LinkedHashSet and its iterator.
@author Leibniz.Hu
@version 1.0
*/
import java.util.*;

class HashSetDemo{
	public static void main(String[] args){
		System.out.println("Ordered by LinedHashSet");
		LinkedHashSet<Person> lhsTest = new LinkedHashSet<Person>(); //Ordered LinkedHashSet
		lhsTest.add(new Person("Leibniz", 21));
		lhsTest.add(new Person("Mayday", 27));
		lhsTest.add(new Person("Jucy", 25));
		lhsTest.add(new Person("Cudy", 23));
		lhsTest.add(new Person("Cudy", 23)); //The same as the first element.
		
		for(Iterator<Person> it = lhsTest.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
		
		System.out.println("\nUn-ordered by HashSet");
		HashSet<Person> hsTest = new HashSet<Person>(); //Un-ordered HashSet
		hsTest.add(new Person("Leibniz", 21));
		hsTest.add(new Person("Mayday", 27));
		hsTest.add(new Person("Jucy", 25));
		hsTest.add(new Person("Cudy", 23));
		hsTest.add(new Person("Cudy", 23)); //The same as the first element.
		
		for(Iterator<Person> it = hsTest.iterator(); it.hasNext(); ) {
			System.out.println(it.next());
		}
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
	
	public int hashCode() {
		return this.name.hashCode() + age * 27;
	}
	
	public boolean equals(Object obj) {
		Person p = (Person)obj;
		return name.equals(p.getName()) && age == p.getAge();
	}
}
