
import java.util.Iterator;

public class LinkedList<T> implements Iterable {

	Node<T> first;
	Node<T> head;
	int elementCount;
	
	//constructor that creates an empty LinkedList
	public LinkedList(){
		first = null;
		head = null;
		elementCount = 0;
	}
	
	//takes a generic object and adds it to the end of the list
	public void add(T newData){
		Node<T> add = new Node<T>(newData);
		
		if (head == null){
			first = add;
		}else{
			head.setNext(add);
		}
		head = add;
		elementCount++;
	}
	
	//method that takes generic object a parameter and checks if the object can be found in the linked list
	public T contains(T data){
		Node<T> walker = first;
		
		while(walker.getNext() != null){
			if (walker.getData().equals(data)){
				return data;
			}
			walker = walker.getNext();
		}
		if (walker.getData().equals(data)){
			return data;
		}
		return null;
	}
	
	public Node<T> getFirst(){
		return first;
	}
	
	//method that can the Node<T> at a certain index
	public Node<T> getIndex(int indexWanted){
		Node<T> walker = first;
		int counter = 0;
		while (walker != null){
			if (indexWanted == counter){
				return walker;
			}
			counter ++;
			walker = walker.getNext();
		}
		return null;
	}
		
	
	public String toString(){
		String message = "";
		Node<T> walker = first;
		for (int i=0; i<elementCount; i++){
			message += walker.getData().toString();
			walker = walker.getNext();
		}
		message += "\n";
		return message;
	}
	
	public int getElementCount(){
		return elementCount;
	}

	
	//Methods for interface Iterable

	//method that return object of type ListIterator
	public ListIterator iterator(){
		ListIterator iterator = new ListIterator();
		return iterator;
	}

	//inner class ListIterator
	class ListIterator implements Iterator{
		Node<T> current; // keeps track of current position being traversed
		
		//constructor that initializes current to the start of the list
		public ListIterator(){
			current = first;
		}
				
		//return true if the iteration has more elemets
		public boolean hasNext(){
			if (head.getNext() != null){
				return true;
			}
			return false;
		}
		
		// return next element in the iteration, and sets current to the next element
		public T next(){
			T next = current.getNext().getData();
			current = current.getNext();
			return next;
		}
		
		//removes last element, then does back to position it was at before
		public void remove(){
			Node<T> temp = current;
			// finds the last entry
			while (current.getNext() != null){
				current = current.getNext();
			}
			current = null; // deletes the last entry 
			current = temp; //puts current back to where it was before
		}
		
	}//END OF LISTITERATOR

	public int length() {
		return elementCount;
	}
	
} // END OF LINKEDLIST
