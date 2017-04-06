package performance;

public interface MeasurementInterface<T> {
	public abstract void printData();
	public abstract void putData(T data);
	public abstract int size();
	public abstract int performanceCounter();
}
