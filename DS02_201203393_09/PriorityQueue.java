package huffman;

public class PriorityQueue<T extends Comparable<T>>{
	private static final int DEFAULT_CAPACITY = 100;
	private static final int ROOT = 1;
	private int _maxSize;
	private int _size;
	private T[] _heap;
	
	@SuppressWarnings("unchecked")
	public PriorityQueue(int maxSize)
	{
		this._heap = (T[]) new Comparable[maxSize+1];
		this._maxSize = maxSize;
		this._size = 0;
	}
	public PriorityQueue()
	{
		this(PriorityQueue.DEFAULT_CAPACITY);
	}
	public boolean isEmpty()
	{
		return (this._size == 0);
	}
	public boolean isFull()
	{
		return (this._size == this._maxSize);
	}
	public int size()
	{
		return this._size;
	}
	
	public T max()
	{
		if(this.isEmpty())
			return null;
		else
			return this._heap[PriorityQueue.ROOT];
	}
	
	public boolean add(T anElement)
	{
		if(this.isFull()){
			return false;
		}
		else
		{
			this._size++;
			int i = this._size;
			while((i > PriorityQueue.ROOT) && (anElement.compareTo(this._heap[i/2]) > 0))
			{
				this._heap[i] = this._heap[i/2];
				i /= 2;
			}
			this._heap[i] = anElement;
			return true;
		}
	}
	
	public T removeMax()
	{
		if(this.isEmpty())
			return null;
		
		T returnElement = this._heap[PriorityQueue.ROOT];
		this._size--;
		if(this._size > 0)
		{
			T lastElement = this._heap[this._size+1];
			int parent = PriorityQueue.ROOT;
			int biggerChild;
			while((parent*2) <= this._size)
			{
				biggerChild = parent*2;
				if((biggerChild < this._size) 
						&& (this._heap[biggerChild].compareTo(this._heap[biggerChild+1]) < 1 ))
				{
					biggerChild++;
				}
				if(lastElement.compareTo(this._heap[biggerChild]) >= 0)
				{
					break;
				}
				this._heap[parent] = this._heap[biggerChild];
				parent = biggerChild;
			}
			this._heap[parent] = lastElement;
		}
		
		return returnElement;
	}
}
