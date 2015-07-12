/*ShapeAreaDemo---Leibniz.Hu 2015.07.12.
* Create a shape interface, make rectangle and circle class to implement this interface
* And use exception handle for illegal inout param.
* This execise is from video cource 4.21.
@author Leibniz.Hu
@version 1.0.0
*/

//Exception for negative inout param(s).
class NegativeParamException extends RuntimeException{
	NegativeParamException(){
		super();
	}
	
	NegativeParamException(String msgExp){
		super(msgExp);
	}
}

//interface for shapes that can calculate area;
//included a method for return Area.
interface AreaExistShape{
	public double getArea();
}

//class of rectangles
class rectangle implements AreaExistShape{
	private double width;
	private double length;
	
	rectangle(double width, double length){
		this.width = width;
		this.length = length;
	}
	
	public double getArea(){ //NegativeParamException extends from RuntimeException so that no need to declare throwing a exception.
		if(width <= 0 || length <= 0){
			throw new NegativeParamException("Error Parameter. Negative width or length.");
		}
		return width * length;
	}
}

//class of circles
class circle implements AreaExistShape{
	private double radius;
	public static final double PI = 3.1415926;
	
	circle(double radius){
		this.radius = radius;
	}
	
	public double getArea(){ //NegativeParamException extends from RuntimeException so that no need to declare throwing a exception.
		if(radius <= 0){
			throw new NegativeParamException("Error Parameter. Negative radius.");
		}
		return PI * radius * radius;
	}
}

class ShapeAreaDemo{
	public static void main(String[] args){
		//simple test cases
		//rectangle rect1 = new rectangle(-3.234 , 674.234);
		//rectangle rect1 = new rectangle(3.234 , 0);
		rectangle rect1 = new rectangle(3.234 , 674.234);
		System.out.println("Rectangle Area = " + rect1.getArea());
		
		//simple test cases
		//circle cir1 = new circle(-45.32423);
		circle cir1 = new circle(45.32423);
		System.out.println("Circle Area = " + cir1.getArea());
	}
}