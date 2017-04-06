package huffman;

public class HuffmanCodeGenerator {

	public static final int ALPHABET_LENGTH = 26;
	public static final int DIFF = 'A';
	private HuffmanTree _root;
	private HuffmanCode _code;
	private FrequencyTable _ftable;
	private PriorityQueue<HuffmanTree> _pq;
	private StringParserForFrequencyTable _parser = new StringParserForFrequencyTable();
	private int[] _table;
	private String[] _codes;
	
	public HuffmanCodeGenerator(String file)
	{
		this._pq = new PriorityQueue<HuffmanTree>(30);
		this._ftable = this._parser.getFTable();
		this._parser.parseString(file);
		this._table = _ftable.getTable();
		this._codes = new String[ALPHABET_LENGTH];
	}
	
	public HuffmanCode generate()
	{
		for(int i = 0; i < ALPHABET_LENGTH; i++)
		{
			this._pq.add(new HuffmanTree(this._table[i], (char)(i+DIFF)));
		}
		
		for(int i = 0; i < ALPHABET_LENGTH-1; i++)
		{
			HuffmanTree tree = new HuffmanTree();
			HuffmanTree leftTree = this._pq.removeMax();
			HuffmanTree rightTree = this._pq.removeMax();
			tree.setFrequency(leftTree.getFrequency() + rightTree.getFrequency());
			tree.setLeftChild(leftTree);
			tree.setRightChild(rightTree);
			this._pq.add(tree);
		}
		this._root = this._pq.removeMax();
		setCode(this._root, new StringBuilder());

		this._code = new HuffmanCode(this._codes, this._root);
		return this._code;
	}
	
	private void setCode(HuffmanTree tree, StringBuilder set)
	{
		if(tree.getAlphabet() != ' ')
		{
			this._codes[tree.getAlphabet()-DIFF] = set.toString();
			return;
		}

		StringBuilder leftSet = new StringBuilder(set.toString());
		leftSet.append(0);
		setCode(tree.getLeftChild(), leftSet);
		StringBuilder rightSet = new StringBuilder(set.toString());
		rightSet.append(1);
		setCode(tree.getRightChild(), rightSet);
	}
}
