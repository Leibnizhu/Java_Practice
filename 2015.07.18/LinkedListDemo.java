/*LinkedListDemo---Leibniz.Hu 2015.07.18
* Sort numbers in a string.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0
*/

import java.util.*;

class LinkedListDemo {
	public static void main(String[] args) {
		LinkedList llTest = new LinkedList();
		for(int i = 0; i < 5; i++) {
			llTest.offerLast(new Person(("Cudy" + i), (20 + i)));
		}
		System.out.println(llTest);

		for(ListIterator listItr = llTest.listIterator(); listItr.hasNext(); ) {
			System.out.println(listItr.next());
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
		return name + " : " + age;
	}
}
