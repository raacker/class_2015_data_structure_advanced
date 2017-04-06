package performance;

public class BinarySearchTree implements MeasurementInterface<String>{
	private int _height;
	private Entry<String, Integer> _entry;
	private BinarySearchTree _left, _right;
	public static final BinarySearchTree NIL = new BinarySearchTree();
	
	public static int performanceCounter = 0;
	public BinarySearchTree(Entry<String, Integer> entry)
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
	
	public BinarySearchTree growTree(Entry<String, Integer> entry) {
		if (this == NIL)
		{
			performanceCounter++;
			return new BinarySearchTree(entry);
		}
		if (this._entry.getKey().equals(entry.getKey()))
		{
			performanceCounter++;
			this._entry.setValue(this._entry.getValue()+1);
			return this;
		}
		if (this._entry.compareTo(entry) < 0)
			this._left = this._left.growTree(entry);
		else
			this._right = this._right.growTree(entry);
		
		this._height = 1 + Math.max(this._left._height, this._right._height);
		performanceCounter++;
		return this;
	}
	public BinarySearchTree decrease(Entry<String, Integer> entry)
	{
		if(this == NIL)
		{
			performanceCounter++;
			return NIL;
		}
		
		if(this._entry.equals(entry.getKey()))
		{
			performanceCounter++;
			return deleteMostOne(this);
		}

		if(this._entry.compareTo(entry) > 0)
			this._left = this._left.decrease(entry);
		else
			this._right = this._right.decrease(entry);

		if(this._left == NIL && this._right == NIL)
			return NIL;
		
		this._height = 1 + Math.max(this._left._height, this._right._height);

		performanceCounter++;
		return this;
	}
	
	private BinarySearchTree deleteMostOne(BinarySearchTree root)
	{
		BinarySearchTree searchNode = root._right;
		BinarySearchTree  searchNodeRoot = root;
		
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
	
	
	private BinarySearchTree()
	{
		this._left = this._right = this;
		this._height = -1;
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