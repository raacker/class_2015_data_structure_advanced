package ds02_1;

public class Node {

	private int _data;
	private Node _next;

	public Node() {
		this._data = 0;
		this._next = null;
	}

	public Node(int data) {
		this._data = data;
		this._next = null;
	}

	public int getData() {
		return this._data;
	}

	public void setData(int data) {
		this._data = data;
	}

	public Node getNext() {
		return this._next;
	}

	public void setNext(Node next) {
		this._next = next;
	}

}