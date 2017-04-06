package huffman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class HuffmanCode {
	public static final int ALPHABET_LENGTH = 26;
	public static final int DIFF = 'A';
	private String[] _code;
	private HuffmanTree _root;
	private StringBuffer _encoded;
	private StringBuffer _decoded;
	
	public HuffmanCode(String[] code, HuffmanTree root)
	{
		this._code = code;
		this._encoded = new StringBuffer();
		this._decoded = new StringBuffer();
		this._root = root;
	}
	
	public String encodeData(String file)
	{
		this.encode(file);
		return this._encoded.toString();
	}
	public String decodeData(String file)
	{
		this.decode(file);
		return this._decoded.toString();
	}
	
	private void encode(String file)
	{
		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String line = in.readLine();
			while (line != null) {
				StringTokenizer parser = new StringTokenizer(line, " ,:;-.?!\"");
				while (parser.hasMoreTokens()) {
					String word = parser.nextToken().toUpperCase();
					String[] stream = getStream(word);
					for(int i = 0; i < stream.length; i++)
					{
						this._encoded.append(stream[i]);
					}
				}
				this._encoded.append("\n");
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	
	private void decode(String binaryFile)
	{
		try {
			BufferedReader in = new BufferedReader(new FileReader(binaryFile));
			String line = in.readLine();
			while (line != null) {
				HuffmanTree searchNode = this._root;
				for(int i = 0; i < line.length(); i++)
				{
					if(searchNode.getAlphabet() != ' ')
					{
						this._decoded.append(searchNode.getAlphabet());
						searchNode = this._root;
						i--;
						continue;
					}
					if(line.charAt(i) == '0')
					{
						searchNode = searchNode.getLeftChild();
						continue;
					}
					if(line.charAt(i) == '1')
					{
						searchNode = searchNode.getRightChild();
						continue;
					}
				}
				this._decoded.append(searchNode.getAlphabet());
				this._decoded.append("\n");
				line = in.readLine();
			}
			in.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	private String[] getStream(String word)
	{
		String[] streamArray = new String[word.length()];
		for(int i = 0; i < word.length(); i++)
		{
			streamArray[i] = this._code[word.charAt(i)-DIFF];
		}
		
		return streamArray;
	}
}
