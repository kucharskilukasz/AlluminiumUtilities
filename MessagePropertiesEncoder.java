import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MessagePropertiesEncoder {
	private static HashMap<String, String> polishLettersUnicode = new HashMap<String, String>();
	
	private static void prepareMap(){
		polishLettersUnicode.put("�", "\\u0104");
		polishLettersUnicode.put("�", "\\u0105");
		polishLettersUnicode.put("�", "\\u0106");
		polishLettersUnicode.put("�", "\\u0107");
		polishLettersUnicode.put("�", "\\u0118");
		polishLettersUnicode.put("�", "\\u0119");
		polishLettersUnicode.put("�", "\\u0141");
		polishLettersUnicode.put("�", "\\u0142");
		polishLettersUnicode.put("�", "\\u0143");
		polishLettersUnicode.put("�", "\\u0144");
		polishLettersUnicode.put("�", "\\u00D3");
		polishLettersUnicode.put("�", "\\u00F3");
		polishLettersUnicode.put("�", "\\u015A");
		polishLettersUnicode.put("�", "\\u015B");
		polishLettersUnicode.put("�", "\\u0179");
		polishLettersUnicode.put("�", "\\u017A");
		polishLettersUnicode.put("�", "\\u017B");
		polishLettersUnicode.put("�", "\\u017C");
	}
	
	private static String encodeSentence(String sentence){
		Set<String> keySet= polishLettersUnicode.keySet();
		for(String key: keySet){
			sentence = sentence.replaceAll(key, "\\"+polishLettersUnicode.get(key));
		}
		return sentence;
	}
	
	public static void replacePolishLettersInFile(String path) throws IOException{
		File file1 = new File(path);
		File file2 = new File(path+"tmp");
		String sentence;
		BufferedReader reader = new BufferedReader(new FileReader(file1));
		BufferedWriter writer = new BufferedWriter(new FileWriter(file2));
		
		while((sentence = reader.readLine()) != null){
			System.out.println(sentence);
			writer.write(MessagePropertiesEncoder.encodeSentence(sentence));
			writer.newLine();
		}
		reader.close();
		writer.close();
	}
	
	public static void main(String[] args) throws IOException{
		MessagePropertiesEncoder.prepareMap();
		MessagePropertiesEncoder.replacePolishLettersInFile(args[0]);
	}
}
