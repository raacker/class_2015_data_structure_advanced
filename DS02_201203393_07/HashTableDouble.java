package performance;

public class HashTableDouble implements Map<String, Integer>, MeasurementInterface<String>{
	private Entry<String, Integer>[] _entries;
	private int _size, _used;
	private float _loadFactor;
	private final Entry<String, Integer> _NIL = new Entry<String, Integer>(null, null);
	private int _collision;
	private String[] _foundList;
	private int _foundCounter;
	
	public static int performanceCounter = 0;
	@SuppressWarnings("unchecked")
	public HashTableDouble(int capacity, float loadFactor)
	{
		this._entries = new Entry[capacity];
		this._loadFactor = loadFactor;
		this._foundList = new String[capacity*2];
		this._foundCounter = 0;
	}
	public HashTableDouble(int capacity)
	{
		this(capacity, 0.75F);
	}
	public HashTableDouble()
	{
		this(101);
	}
	public int getCollision()
	{
		return this._collision;
	}
	@Override
	public Integer get(String key) {
		int hashValue = hash(key);
		int doubleHashValue = doubleHash(key);
		for(int loop = 0; loop < this._entries.length; loop++)
		{
			int index = nextProbe(hashValue, doubleHashValue, loop);
			Entry<String, Integer> tempEntry = this._entries[index];
			if(tempEntry == null) break;
			if(tempEntry == this._NIL) continue;
			if(tempEntry.getKey().equals(key))
				return tempEntry.getValue();
		}
		return null;	//couldn't find key
	}

	@Override
	public Integer put(String key, Integer value) {
		if(this._used > this._loadFactor * this._entries.length) this.rehash();
		int hashValue = hash(key);
		int doubleHashValue = doubleHash(key);
		for(int loop = 0; loop < this._entries.length; loop++)
		{
			performanceCounter++;
			int index = nextProbe(hashValue, doubleHashValue, loop);
			Entry<String, Integer> tempEntry = this._entries[index];
			if(tempEntry == null)
			{
				this._entries[index] = new Entry<String, Integer>(key,value);
				this._foundList[this._foundCounter++] = key;
				this._size++;
				this._used++;
				return null;
			}
			if(tempEntry == this._NIL) continue;
			if(tempEntry.getKey().equals(key))
			{
				Integer oldValue = tempEntry.getValue();
				this._entries[index].setValue(1 + oldValue);
				return oldValue;
			}
			this._collision++;
		}
		return null;
	}
	@Override
	public Integer remove(String key) {
		int hashValue = hash(key);
		int doubleHashValue = doubleHash(key);
		for(int loop = 0; loop < this._entries.length; loop++)
		{
			int index = nextProbe(hashValue, doubleHashValue, loop);
			Entry<String, Integer> tempEntry = this._entries[index];
			if(tempEntry == null) break;
			if(tempEntry == this._NIL) continue;
			if(tempEntry.getKey().equals(key))
			{
				Integer oldValue = this._entries[index].getValue();
				this._entries[index] = this._NIL;
				this._size --;
				return oldValue;
			}
		}
		return null;
	}

	@Override
	public int size() {
		return this._size;
	}
	
	
	private int hash(String key)
	{
		if(key == null) throw new IllegalArgumentException();
		return (key.hashCode() & 0x7FFFFFFF) % this._entries.length;
	}
	private int doubleHash(String key)
	{
		if(key == null) throw new IllegalArgumentException();
		return 1 + (key.hashCode() & 0x7FFFFFFF) % (this._entries.length-1);
	}
	private int nextProbe(int hashValue, int doubleHashValue, int index)
	{
		return (hashValue + doubleHashValue + index) % this._entries.length;
	}
	
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		Entry<String, Integer>[] oldEntries = this._entries;
		this._entries = new Entry[2 * oldEntries.length];
		for(int rehasingCounter = 0; rehasingCounter < oldEntries.length; rehasingCounter++)
		{
			Entry<String, Integer> tempEntry = oldEntries[rehasingCounter];
			if(tempEntry == null || tempEntry == this._NIL) continue;
			int hashValue = hash(tempEntry.getKey());
			int doubleHashValue = doubleHash(tempEntry.getKey());
			for(int loop = 0; loop < this._entries.length; loop++)
			{
				performanceCounter++;
				int index = nextProbe(hashValue, doubleHashValue, loop);
				if(this._entries[index] == null)
				{
					this._entries[index] = tempEntry;
					break;					
				}
			}
		}
		this._used = this._size;
	}
	@Override
	public void putData(String data) {
		// TODO Auto-generated method stub
		put(data, 1);
	}
	@Override
	public void printData() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this._foundCounter; i++)
		{
			String target = this._foundList[i];
			int times = this.get(target);
			System.out.println("Word : "+ target + " // Found : " + times + " times");
		}
	}
	@Override
	public int performanceCounter() {
		// TODO Auto-generated method stub
		return performanceCounter;
	}
}

