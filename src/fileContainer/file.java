package fileContainer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import slangWord.slangWordDefinition;

public class file {
	
	
	public slangWordDefinition splitString(String line) {
		slangWordDefinition slang= new slangWordDefinition();
		int position=line.indexOf("`");
		if(position==-1)
		{
			// khong tim thay 
			System.out.println("Loi file");
			
		}
		else {
			String slangWord=line.substring(0,position);
			String definition=line.substring(position+1,line.length());
			slang.slangWord=slangWord;
			slang.definition=definition;
			String str[]=definition.split("[ |]");
			slang.keyExist=new ArrayList<String>(Arrays.asList(str));
			
			
		}
		
		
		return slang;
	}
	//ham doc file
	public void readFile(ArrayList<slangWordDefinition> slang,String directory)
	{
		String fileName=System.getProperty("user.dir");
		fileName=fileName+"\\src\\fileContainer\\slang.txt";
		if(fileName!=null)
		{
			// doc file slang.txt
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
				String line="";
				while((line=br.readLine())!=null)
				{
					slang.add(splitString(line));
				}
				br.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void writeFileHistory(String line)
	{
		try {
			FileWriter fw = new FileWriter("history.txt");
			fw.write(line+"\n");
			fw.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
}

