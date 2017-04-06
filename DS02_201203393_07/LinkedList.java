package performance;

public class LinkedList implements MeasurementInterface<String>{
	private Node<Entry<String, Integer>> _head;
	private int _size;
	
	public static int performanceCounter = 0;
	
	public LinkedList()
	{
		this._size = 0;
		this._head = null;
	}
	
	public boolean isEmpty()
	{
		return (this._size == 0); 
	}
	public int size()
	{
		return this._size;
	}
	public void clear()
	{
		this._head = null;
		this._size = 0;
	}
	public int getSize()
	{
		return this._size;
	}
	public boolean addElement(Entry<String, Integer> data)
	{
		boolean nodeUpdated = false;
		Node<Entry<String, Integer>> searchNode = this._head;
		Node<Entry<String, Integer>> previousNode = null;
		while (searchNode != null) {
			performanceCounter++;
			if (searchNode.getData().getKey().equals(data.getKey())) {
				searchNode.getData().setValue(searchNode.getData().getValue() + 1);
				nodeUpdated = true;
				break;
			}
			if (searchNode.getData().getKey().compareTo(data.getKey()) > 0)
				break;

			previousNode = searchNode;
			searchNode = searchNode.getNext();
		}

		if (nodeUpdated == false) {
			Node<Entry<String, Integer>> newNode = new Node<Entry<String, Integer>>(data);
			if(searchNode == this._head)
			{
				this._head = newNode;
			}
			else
			{
				newNode.setNext(searchNode);
				previousNode.setNext(newNode);
			}
		}
		this._size++;
		return true;
	}
	
	public Entry<String, Integer> removeElement(Entry<String, Integer> data)
	{
		if(this.isEmpty())
			return null;
		Node<Entry<String, Integer>> searchNode = this._head;
		Node<Entry<String, Integer>> previousNode = null;
		boolean doesFound = false;
		Entry<String, Integer> removedElement = null;
		while(searchNode.getNext() != null)
		{
			if(searchNode.getData().equals(data))
			{
				removedElement = searchNode.getData();
				doesFound = true;
				break;
			}
			else
			{
				previousNode = searchNode;
				searchNode = searchNode.getNext();
			}
		}
		if(doesFound)
		{
			previousNode.setNext(searchNode.getNext());
			this._size--;
		}
		return removedElement;
	}
	public void printData()
	{
		Node<Entry<String, Integer>> searchNode = this._head;
		while(searchNode != null)
		{
			System.out.println("Word : " + searchNode.getData().getKey() 
					+ " // Found : " + searchNode.getData().getValue() + " times");
					
			searchNode = searchNode.getNext();
		}
	}
	@Override
	public void putData(String data) {
		addElement(new Entry<String, Integer>(data, 1));
	}
	@Override
	public int performanceCounter() {
		// TODO Auto-generated method stub
		return performanceCounter;
	}
}
