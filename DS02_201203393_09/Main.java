package huffman;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	public static void main(String args[]) throws IOException
	{
		HuffmanCodeGenerator huff = new HuffmanCodeGenerator(args[0]);
		HuffmanCode code = huff.generate();
		
		String encoded = code.encodeData(args[0]);
		FileOutputStream out = new FileOutputStream("C:\\Users\\Haven\\Desktop\\huffman\\outputEncoded.txt");
		out.write(encoded.getBytes());
		out.close();
		
		String decoded = code.decodeData(args[1]);
		FileOutputStream out2 = new FileOutputStream("C:\\Users\\Haven\\Desktop\\huffman\\outputDecoded.txt");
		out2.write(decoded.getBytes());
		out2.close();
		
	}
}
