package avltree;

public class AVLTree {
	private int _key, _height;
	private AVLTree _left, _right;
	public static final AVLTree NIL = new AVLTree();
	
	public AVLTree(int key)
	{
		this._key = key;
		this._left = this._right = NIL;
	}
	
	public boolean add(int key)
	{
		int oldSize = size();
		growTree(key);
		return size() > oldSize;
	}
	
	public AVLTree growTree(int key) {
		if (this == NIL)
			return new AVLTree(key);
		if (this._key == key)
			return this;
		if (this._key > key)
			this._left = this._left.growTree(key);
		else
			this._right = this._right.growTree(key);
		
		this.rebalance();
		this._height = 1 + Math.max(this._left._height, this._right._height);
		return this;
	}
	public AVLTree decrease(int key)
	{
		if(this == NIL)
			return NIL;
		
		if(this._key == key)
			return deleteMostOne(this);

		if(this._key > key)
			this._left = this._left.decrease(key);
		else
			this._right = this._right.decrease(key);

		if(this._left == NIL && this._right == NIL)
			return NIL;
		
		
		this.rebalance();
		this._height = 1 + Math.max(this._left._height, this._right._height);
		return this;
	}
	
	private AVLTree deleteMostOne(AVLTree root)
	{
		AVLTree searchNode = root._right;
		AVLTree searchNodeRoot = root;
		
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
		root._key = searchNode._key;
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
		return this._right + " " + this._key + " " + this._left;
	}
	
	
	private AVLTree()
	{
		this._left = this._right = this;
		this._height = -1;
	}
	
	private AVLTree(int key, AVLTree left, AVLTree right)
	{
		this._key = key;
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
		this._left = new AVLTree(this._key, this._left, this._right._left);
		this._key = this._right._key;
		this._right = this._right._right;
	}
	private void rotateRight()
	{
		this._right = new AVLTree(this._key, this._left._right, this._right);
		this._key = this._left._key;
		this._left = this._left._left;
	}
	
}