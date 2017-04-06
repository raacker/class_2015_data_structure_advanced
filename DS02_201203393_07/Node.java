package performance;

public class Node <T>{
	private T _data;
	private Node<T> _next;
	
	public Node(T data, Node<T> next){
		this._data =data;
		this._next = next;
	}
	public Node(T data)
	{
		this(data, null);
	}
	public Node()
	{
		this(null, null);
	}
	
	public T getData()
	{
		return this._data;
	}
	public void setData(T data)
	{
		this._data = data;
	}
	public Node<T> getNext()
	{
		return this._next;
	}	
	public void setNext(Node<T> next)
	{
		this._next = next;
	}
}
