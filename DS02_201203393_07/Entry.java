package performance;

public class Entry<Key extends Comparable<String>, Value> implements Comparable<Entry<Key, Value>>{
	private Key _key;
	private Value _value;
	public Entry(Key key, Value value)
	{
		this._key = key;
		this._value = value;
	}
	public Entry(Key key)
	{
		this(key, null);
	}
	public Key getKey(){return this._key;}
	public void setKey(Key key){this._key = key;}
	public Value getValue(){return this._value;}
	public void setValue(Value value){this._value = value;}
	@Override
	public int compareTo(Entry<Key, Value> args) {
		// TODO Auto-generated method stub
		if(this._key.compareTo((String)args.getKey()) > 0)
			return -1;
		else if(this._key.compareTo((String)args.getKey()) < 0)
			return 1;
		else
			return 0;
	}
	
	public String toString()
	{
		return "Key : "+this._key+" "+"Value : "+this._value+'\n';
	}
}