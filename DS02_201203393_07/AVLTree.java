package performance;

public class AVLTree implements MeasurementInterface<String>{
	private int _height;
	private Entry<String, Integer> _entry;
	private AVLTree _left, _right;
	public static final AVLTree NIL = new AVLTree();
	
	public static int performanceCounter = 0;
	
	public AVLTree(Entry<String, Integer> entry)
	{
		this._entry = entry;
		this._left = this._right = NIL;
	}
	
	public boolean add(Entry<String, Integer> entry)
	{
		int oldSize = size();
		growTree(entry);
		return size() > oldSize;
	}
	
	public int getHeight()
	{
		return this._height;
	}
	public AVLTree growTree(Entry<String, Integer> entry) {
		if (this == NIL)
		{
			performanceCounter++;
			return new AVLTree(entry);
		}
		if(this._entry.getKey().equals(entry.getKey()))
		{
			this._entry.setValue(this._entry.getValue()+1);
			performanceCounter++;
			return this;
		}
		if (this._entry.compareTo(entry) < 0)
			this._left = this._left.growTree(entry);
		else
			this._right = this._right.growTree(entry);
		
		this.rebalance();
		this._height = 1 + Math.max(this._left._height, this._right._height);

		performanceCounter++;
		return this;
	}
	public AVLTree decrease(Entry<String, Integer> entry)
	{
		if(this == NIL)
			return NIL;
		
		if(this._entry.getKey().equals(entry.getKey()))
			return deleteMostOne(this);

		if(this._entry.compareTo(entry) > 0)
			this._left = this._left.decrease(entry);
		else
			this._right = this._right.decrease(entry);

		if(this._left == NIL && this._right == NIL)
			return NIL;
		
		
		this.rebalance();
		this._height = 1 + Math.max(this._left._height, this._right._height);
		return this;
	}
	
	private AVLTree deleteMostOne(AVLTree root)
	{
		AVLTree searchNode = root._right;
		AVLTree  searchNodeRoot = root;
		
		while(searchNode._left != NIL)
		{
			searchNodeRoot = searchNode;
			searchNode = searchNode._left;
		}
		
		if(searchNode != root._right)
		{
			searchNodeRoot._left = searchNode._right;
			searchNode._right = root._right;
		}
		
		searchNode._left = root._left;
		root._entry = searchNode._entry;
		this.rebalance();
		searchNode._height = 1 + Math.max(searchNode._left._height, searchNode._right._height);
		return searchNode;
	}

	public int size() {
		if (this == NIL)
			return 0;
		return 1 + this._left.size() + this._right.size();
	}

	public String toString() {
		if (this == NIL)
			return "";
		return this._left + "Word : " + this._entry.getKey() 
			+ " // Found : " + this._entry.getValue() + " times\n" + this._right;
	}
	
	
	private AVLTree()
	{
		this._left = this._right = this;
		this._height = -1;
	}
	
	private AVLTree(Entry<String, Integer> entry, AVLTree left, AVLTree right)
	{
		this._entry = entry;
		this._left = left;
		this._right = right;
		this._height = 1 + Math.max(left._height, right._height);
	}
	
	private void rebalance()
	{
		if(this._right._height > this._left._height + 1)
		{
			if(this._right._left._height > this._right._right._height)
				this._right.rotateRight();
			this.rotateLeft();
		}
		else if(this._left._height > this._right._height + 1)
		{
			if(this._left._right._height > this._left._left._height)
				this._left.rotateLeft();
			this.rotateRight();
		}
	}
	
	private void rotateLeft()
	{
		this._left = new AVLTree(this._entry, this._left, this._right._left);
		this._entry = this._right._entry;
		this._right = this._right._right;
	}
	private void rotateRight()
	{
		this._right = new AVLTree(this._entry, this._left._right, this._right);
		this._entry = this._left._entry;
		this._left = this._left._left;
	}

	@Override
	public void putData(String data) {
		growTree(new Entry<String, Integer>(data, 1));
	}

	@Override
	public void printData() {
		System.out.println(this.toString());
	}

	@Override
	public int performanceCounter() {
		// TODO Auto-generated method stub
		return performanceCounter;
	}
}