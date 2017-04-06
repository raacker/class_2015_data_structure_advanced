package performance;

public class ResultDataSet {
	private int _arithCounter;
	private long _timelaps;
	
	public ResultDataSet(int arithCounter,long timelaps)
	{
		this._arithCounter = arithCounter;
		this._timelaps = timelaps;
	}
	
	public void result(String structure)
	{
		System.out.println("\n<<"+structure +" result>>");
		System.out.println("Arithmetic Counter: "+this._arithCounter);
		System.out.println("Search Timelaps: "+this._timelaps);
	}
}
