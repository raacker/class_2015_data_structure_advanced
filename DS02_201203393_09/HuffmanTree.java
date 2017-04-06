package huffman;

public class HuffmanTree implements Comparable<HuffmanTree>{
	private HuffmanTree _leftChild;
	private HuffmanTree _rightChild;
	private int _frequency;
	private char _alphabet;
	
	public HuffmanTree(int frequency, char alphabet)
	{
		this._leftChild = null;
		this._rightChild = null;
		this._frequency = frequency;
		this._alphabet = alphabet;
	}
	public HuffmanTree(int frequency)
	{
		this(frequency,' ');
	}
	public HuffmanTree()
	{
		this(0,' ');
	}

	public HuffmanTree getLeftChild()
	{
		return this._leftChild;
	}
	public void setLeftChild(HuffmanTree tree)
	{
		this._leftChild = tree;
	}
	public HuffmanTree getRightChild()
	{
		return this._rightChild;
	}
	public void setRightChild(HuffmanTree tree)
	{
		this._rightChild = tree;
	}
	public int getFrequency()
	{
		return this._frequency;
	}
	public void setFrequency(int frequency)
	{
		this._frequency = frequency;
	}
	public char getAlphabet()
	{
		return this._alphabet;
	}
	@Override
	public int compareTo(HuffmanTree obj) {
		// TODO Auto-generated method stub
		if(this._frequency > obj._frequency)
			return -1;
		else if(this._frequency < obj._frequency)
			return 1;
		else
			return 0;
	}
	
	public String toString()
	{
		return this._leftChild + " (" +this._alphabet + ", " +this._frequency+ ") " + this._rightChild;
	}
}
