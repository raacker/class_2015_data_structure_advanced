package hash_quad;

public class Entry<Key, Value> {
	private Key _key;
	private Value _value;
	public Entry(Key key, Value value)
	{
		this._key = key;
		this._value = value;
	}
	public Key getKey(){return this._key;}
	public void setKey(Key key){this._key = key;}
	public Value getValue(){return this._value;}
	public void setValue(Value value){this._value = value;}
}