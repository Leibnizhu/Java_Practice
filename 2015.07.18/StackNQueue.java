/*StackNQueue---Leibniz.Hu 2015.07.18
* Achieve stack and queue by LinkedList.
* This execise is from video cource.
@author Leibniz.Hu
@version 1.0
*/

import java.util.*;

class StackNQueue {
	public static void main(String[] args) {
		//Present stack.
		myStack stkTest = new myStack();
		for(int i = 0; i < 10; i++) {
			stkTest.push(new String("cudy" + i));
		}
		System.out.println("Stack popping out:");
		for(int i = 0; i < 10; i++) {
			System.out.println(stkTest.pop());
		}

		//Present queue.
		myQueue qTest = new myQueue();
		for(int i = 0; i < 10; i++) {
			qTest.push(new String("cudy" + i));
		}
		System.out.println("\nQueue popping out:");
		for(int i = 0; i < 10; i++) {
			System.out.println(qTest.pop());
		}
	}
}

class myStack {
	private LinkedList llTest; //Use to store stack.

	myStack() {
		llTest = new LinkedList();
	}

	public void push(Object obj) {
		llTest.addLast(obj); //Add elements at the end of stack;
	}

	public Object pop() {
		return llTest.removeLast(); //When popping, pop from the end of the queue.
	}
}

class myQueue {
	private LinkedList llTest; //Use to store queue.

	myQueue() {
		llTest = new LinkedList();
	}

	public void push(Object obj) {
		llTest.addLast(obj); //Add elements at the end of queue;
	}

	public Object pop() {
		return llTest.removeFirst(); //When popping, pop from the beginning of the queue.
	}
}
