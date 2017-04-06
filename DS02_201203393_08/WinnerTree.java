package winnertree;

public class WinnerTree {
	private int[] _tree;
	private int[][] _source;
	private int[] _element;
	private int[] _elementLength;
	private int[] _elementTargetIndex;
	private int _leafNodes;
	
	public WinnerTree(int[][] source)
	{
		this._leafNodes = source.length;
		this._tree = new int[this._leafNodes*2];
		this._source = source;
		this._element = new int[this._source.length];
		this._elementLength = new int[this._element.length];
		this._elementTargetIndex = new int[this._element.length];
		for(int i = 0; i < this._source.length; i++)
		{
			this._elementLength[i] = this._source[i].length;
			this._elementTargetIndex[i] = 0;
		}
		
		this.initialize();
	}
	private void initialize()
	{
		for(int i = 0; i < this._source.length; i++)
			this._element[i] = this._source[i][0];
		
		int elementCount = this._leafNodes-1;
		for(int i = this._tree.length-1; i >= this._leafNodes; i--)
			this._tree[i] = elementCount--;
		
		for(int i = this._tree.length-1; i > 1; i -= 2)
		{
			int minIndex = getMinSubtreeIndex(i-1, i);
			this._tree[i/2] = minIndex;
		}
	}
	
	public int remove()
	{
		int returnValue = this._element[this._tree[1]];
		
		int returnIndex = 0;
		for(int i = 0; i < this._leafNodes; i++)
		{
			if(this._element[i] == returnValue)
			{
				returnIndex = i;
				break;
			}
		}
		
		this._elementTargetIndex[returnIndex]++;
		if(this._elementTargetIndex[returnIndex] >= this._elementLength[returnIndex])
			this._element[returnIndex] = Integer.MAX_VALUE;
		else
			this._element[returnIndex] = this._source[returnIndex][this._elementTargetIndex[returnIndex]];
		
		for(int i = this._tree.length-1; i > 1; i -= 2)
		{
			int minIndex = getMinSubtreeIndex(i-1, i);
			this._tree[i/2] = minIndex;
		}
		
		return returnValue;
	}
	
	private int getMinSubtreeIndex(int leftIndex, int rightIndex)
	{
		if(this._element[this._tree[leftIndex]] < this._element[this._tree[rightIndex]])
			return this._tree[leftIndex];
		else
			return this._tree[rightIndex];
	}
}
