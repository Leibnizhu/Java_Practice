/*ConstructorDemo---Leibniz.Hu 2015.07.05
* Execises from <Thinking in Java> (TIJ in short)
* Page 83 Execise 5.3 & 5.4 & 5.5
* define a Dog class, try default constructor, override-constructor, override-method.
@author Leibniz.Hu
@version 1.0.0
*/

public class ConstructorDemo {
	public static void main (String[] args) {
		Dog dog1 = new Dog();
		Dog dog2 = new Dog("Maydaymaoo");
		dog1.bark("wow");
		dog1.bark((int)6.432);
		dog2.bark(3);
	}
}

class Dog {
	String name = "Cudy";
	
	//non-param constructor.
	Dog() {
		System.out.println("Initialize with default constructor. Dog's name is : " + name);
	}
	
	//override constructor with a String param.
	Dog(String name) {
		this.name = name;
		System.out.println("Initialize with override-constructor. Dog's name is : " + this.name);
	}
	
	//barking with String param
	void bark(String barking) {
		System.out.println("Dog " + name + " is barking as " + barking);
	}
	
	//howling with integer param
	void bark(int howling) {
		System.out.println("Dog " + name + " is howling " + howling + " time(s)!");
	}
}

