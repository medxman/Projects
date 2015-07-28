
public class Node<T> {
	// this class is a generic class for the Node for the LinkedList
	
	Node<T> next;
	T data;
	
	//constructor for Node<T> that takes in one parameter for data
	public Node(T data){
		this.data = data;
	}
	
	//constructor for Node<T> that takes in parameter for data and next
	public Node(T data, Node<T> next){
		this.data = data;
		this.next = next;
	}
	
	public boolean hasNext(){
		if (next == null){
			return false;
		}
		return true;
	}
	
	//setter method for next
	public void setNext(Node<T> next){
		this.next = next;
	}
	
	//getter method for next
	public Node<T> getNext(){
		return next;
	}
	
	//getter for data
	public T getData(){
		return data;
	}
	
	//String method for data
	public String toString(){
		return data.toString();
	}
} //End of Node<T>
