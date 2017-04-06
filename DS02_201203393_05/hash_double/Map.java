package hash_double;

public interface Map<Key, Value>{
	public abstract Value get(Key key);
	public abstract Value put(Key key, Value value);
	public abstract Value remove(Key key);
	public abstract int size();
}
