package topological;

public class Stack<T> {
	private Node _head;
	private int _size;
	
	public Stack()
	{
		this._size = 0;
		this._head = null;
	}
	public boolean isEmpty()
	{
		return (this._size == 0);
	}
	public boolean hasNextValue()
	{
		return !isEmpty();
	}
	public T pop()
	{
		if(isEmpty())
			return null;
		T removeData = this._head.getData();
		this._head = this._head.getNext();
		this._size--;
		return removeData;
	}
	public void push(T data)
	{
		Node newData = new Node(data);
		if(this._head != null)
			newData.setNext(this._head);
		this._head = newData;
		this._size++;
	}
	public T peek()
	{
		if(isEmpty())
			return null;
		return this._head.getData();
	}
	
	
	private class Node
	{
		private T _data;
		private Node _next;
		
		Node(T data, Node next)
		{
			this._data = data;
			this._next = next;
		}
		Node(T data)
		{
			this(data,null);
		}
		
		public T getData()
		{
			return this._data;
		}
		public Node getNext()
		{
			return this._next;
		}
		public void setNext(Node next)
		{
			this._next = next;
		}
	}
}
