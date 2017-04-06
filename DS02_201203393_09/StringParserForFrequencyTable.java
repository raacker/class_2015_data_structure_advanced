package huffman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class StringParserForFrequencyTable {

	private FrequencyTable _ftable;
	public StringParserForFrequencyTable()
	{
		this._ftable = new FrequencyTable();
	}
	
	public void parseString(String file) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line != null) {
				StringTokenizer parser = new StringTokenizer(line, " ,:;-.?!\"");
				while (parser.hasMoreTokens()) {
					String word = parser.nextToken().toUpperCase();
					this.addWordToTable(word);
				}
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public FrequencyTable getFTable()
	{
		return this._ftable;
	}
	private void addWordToTable(String word)
	{
		for(int i = 0; i < word.length(); i++)
		{
			this._ftable.addFrequency(word.charAt(i));
		}
	}
}
