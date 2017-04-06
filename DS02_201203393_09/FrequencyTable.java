package huffman;

public class FrequencyTable {

	private int[] _table;
	private final int diff = 'A';
	
	public FrequencyTable()
	{
		this._table = new int[26];
	}
	
	public int[] getTable()
	{
		return this._table;
	}
	
	public void addFrequency(char alphabet)
	{
		this._table[alphabet-diff]++;
	}
}
