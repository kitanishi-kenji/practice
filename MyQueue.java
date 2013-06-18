import java.util.Stack;

public class MyQueue {
	
		private Stack<Integer> out = new Stack<Integer>();
		private Stack<Integer> in = new Stack<Integer>();
		
		public synchronized void push(Integer value){
			in.push(value);
		}
		
		public synchronized Integer pop(){
			if(out.isEmpty() && in.isEmpty()){
				return null;
			} else {
				if(out.isEmpty()){
					while(!in.isEmpty()){
						out.push(in.pop());
					}
				}
				return out.pop();
			}
		}
		
		public synchronized boolean isEmpty(){
			return (length() == 0);
		}
		
		public synchronized int length(){
			return in.size() + out.size();
		}
		
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyQueue queue = new MyQueue();
		System.out.println("Length:" + queue.length() + ", Emepty:" + queue.isEmpty());
		System.out.println("queue > " + queue.pop());		
		queue.push(1);
		System.out.println("1 > queue");
		queue.push(2);
		System.out.println("2 > queue");
		queue.push(3);
		System.out.println("3 > queue");
		System.out.println("queue > " + queue.pop());
		queue.push(4);
		System.out.println("4 > queue");
		queue.push(5);
		System.out.println("5 > queue");
		System.out.println("Length:" + queue.length() + ", Emepty:" + queue.isEmpty());
		System.out.println("queue > " + queue.pop());
		System.out.println("queue > " + queue.pop());
		System.out.println("queue > " + queue.pop());		
		System.out.println("queue > " + queue.pop());		
		System.out.println("Length:" + queue.length() + ", Emepty:" + queue.isEmpty());
	}

}
