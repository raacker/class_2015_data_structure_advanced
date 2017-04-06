package hash_double;

public class Country {
	private String _name;
	private String _language;
	private int _people;
	private int _area;
	
	public Country(String name, String language, int people, int area)
	{
		this._name = name;
		this._language = language;
		this._people = people;
		this._area = area;
	}
	
	public String getName(){return this._name;}
	public void setName(String name){this._name = name;};
	public String getLanguage(){return this._language;}
	public void setLanguage(String language){this._language = language;};
	public int getPeople(){return this._people;}
	public void setPeople(int people){this._people = people;};
	public int getArea(){return this._area;}
	public void setArea(int area){this._area = area;};
}
