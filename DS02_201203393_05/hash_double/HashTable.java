package hash_double;

public class HashTable<Key, Value> implements Map<Key, Value>{
	private Entry<Key, Value>[] _entries;
	private int _size, _used;
	private float _loadFactor;
	private final Entry<Key, Value> _NIL = new Entry<Key, Value>(null, null);
	private int _collision;
	private String[] _testLog;
	private int _testSize;
	
	@SuppressWarnings("unchecked")
	public HashTable(int capacity, float loadFactor)
	{
		this._entries = new Entry[capacity];
		this._loadFactor = loadFactor;
		this._testLog = new String[capacity];
	}
	public HashTable(int capacity)
	{
		this(capacity, 0.75F);
	}
	public HashTable()
	{
		this(101);
	}
	public int getCollision()
	{
		return this._collision;
	}
	public String[] getTestLog()
	{
		return this._testLog;
	}
	@Override
	public Value get(Key key) {
		int hashValue = hash(key);
		for(int loop = 0; loop < this._entries.length; loop++)
		{
			int index = nextProbe(hashValue, loop);
			Entry<Key, Value> tempEntry = this._entries[index];
			if(tempEntry == null) break;
			if(tempEntry == this._NIL) continue;
			if(tempEntry.getKey().equals(key))
				return tempEntry.getValue();
		}
		return null;	//couldn't find key
	}

	@Override
	public Value put(Key key, Value value) {
		if(this._used > this._loadFactor * this._entries.length) this.rehash();
		StringBuffer buf = new StringBuffer(key + " :: ");
		int hashValue = hash(key);
		for(int loop = 0; loop < this._entries.length; loop++)
		{
			int index = nextProbe(hashValue,loop);
			buf.append(index);
			Entry<Key, Value> tempEntry = this._entries[index];
			if(tempEntry == null)
			{
				this._entries[index] = new Entry<Key, Value>(key,value);
				this._size++;
				this._used++;
				this._testLog[this._testSize++] = buf.toString();
				return null;
			}
			if(tempEntry == this._NIL) continue;
			if(tempEntry.getKey().equals(key))
			{
				Value oldValue = tempEntry.getValue();
				this._entries[index].setValue(value);
				this._testLog[this._testSize++] = buf.toString();
				return oldValue;
			}
			buf.append(" -> ");
			this._collision++;
		}
		return null;
	}

	@Override
	public Value remove(Key key) {
		int hashValue = hash(key);
		for(int loop = 0; loop < this._entries.length; loop++)
		{
			int index = nextProbe(hashValue, loop);
			Entry<Key, Value> tempEntry = this._entries[index];
			if(tempEntry == null) break;
			if(tempEntry == this._NIL) continue;
			if(tempEntry.getKey().equals(key))
			{
				Value oldValue = this._entries[index].getValue();
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
	
	private int hash(Key key)
	{
		if(key == null) throw new IllegalArgumentException();
		return (key.hashCode() & 0x7FFFFFFF) % this._entries.length;
	}
	
	private int nextProbe(int hashValue, int index)
	{
		return (hashValue + index) % this._entries.length;
	}
	@SuppressWarnings("unchecked")
	private void rehash()
	{
		Entry<Key, Value>[] oldEntries = this._entries;
		this._entries = (Entry[]) new Object[2 * oldEntries.length];
		for(int rehasingCounter = 0; rehasingCounter < oldEntries.length; rehasingCounter++)
		{
			Entry<Key, Value> tempEntry = oldEntries[rehasingCounter];
			if(tempEntry == null || tempEntry == this._NIL) continue;
			int hashValue = hash(tempEntry.getKey());
			for(int loop = 0; loop < this._entries.length; loop++)
			{
				int index = nextProbe(hashValue, loop);
				if(this._entries[index] == null)
				{
					this._entries[index] = tempEntry;
					break;					
				}
			}
		}
		this._used = this._size;
	}
	
	
	
	
	
}

