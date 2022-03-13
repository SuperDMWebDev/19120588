package slangWord;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

import fileContainer.file;

public class slangWordFunction {
	public static void SearchWord(String word, ArrayList<slangWordDefinition> slang)
	{
		Boolean exist=false;
		for(int i=0;i<slang.size();i++)
		{
	
			// so sanh neu 2 chuoi giong nhau thi tra ve
			if(slang.get(i).slangWord.compareTo(word)==0)
			{
				System.out.println("The meaning of slang word is "+slang.get(i).definition );
				exist=true;
				break;
			}
		}
		if(exist==false)
		{
			System.out.println("Can not find any meaning for that slang word");
		}
	}
	public static TreeSet<String> SearchSlang(String line, ArrayList<slangWordDefinition> slang)
	{
		TreeSet<String>a = new TreeSet<String>();
		Boolean exist=false;
		for(int i=0;i<slang.size();i++)
		{
			String b= slang.get(i).definition;
			if(b.indexOf(line)!=-1)
			{	
				ArrayList<String>c = slang.get(i).keyExist;
				for(int j=0;j<c.size();j++)
				{
					if(c.get(j).indexOf(line) != -1)
					{
						a.add(slang.get(i).slangWord);
						
						exist=true;
					}
				}
				
			}
			
		}
		if(exist==false)
		{
			System.out.println("Khong co slang word nao trong tu dien phu hop");
		}
		else
		{
			System.out.println("Cac slang word tim duoc la: ");
			for(String word:a)
			{
				System.out.println(word);
			}
		}
		return a;
	}
	public static void addSlangWord(String key, String definition, ArrayList<slangWordDefinition> slang)
	{
		Boolean exist=false;
		for(int i=0;i<slang.size();i++)
		{
			if(slang.get(i).slangWord.compareTo(key)==0)
			{
				exist=true;
				System.out.println("This slang word already existed");
				System.out.println("Type 1 if you want to override it or type 2 to create a new one");
				Scanner scanner= new Scanner(System.in);
				int n=Integer.parseInt(scanner.nextLine());
				if(n==1)
				{
					// day dung de edit key 
				}
				else {
						
						try {
							String line=key+"`"+definition;
							FileWriter fw = new FileWriter("slang.txt",true);
							fw.write(line+"\n");
							fw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				}
				break;
			}
		}
		if(exist==false)
		{
			try {
				String line=key+"`"+definition;
				FileWriter fw = new FileWriter("slang.txt",true);
				fw.write(line+"\n");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		file file=new file();
		String fileName="slang.txt";
		Scanner scanner=new Scanner(System.in);
		ArrayList<slangWordDefinition> slang= new ArrayList<slangWordDefinition>();
		int n=0;
		int choice=0;
		String line="";
		//goi ham docfile
		file.readFile(slang, fileName);
		do {
			System.out.println("Please choose what you want to do: ");
			System.out.println("1.Find the meaning by slang word..");
			System.out.println("2.Find the slang by definition..");
			System.out.println("3.Show your history..");
			System.out.println("4.Add a new slang..");
			System.out.println("5.Edit a slang..");
			System.out.println("6.Delete a slang..");
			System.out.println("7.Reset the list slang..");
			System.out.println("8.Give a random slang..");
			System.out.println("9.Riddle 1..");
			System.out.println("10.Riddle 2..");
			choice=Integer.parseInt(scanner.nextLine());
			switch(choice) {
				case 1:
					System.out.println("Please input slang word you want to find meaning");
					line=scanner.nextLine();
					file.writeFileHistory(line);
					SearchWord(line,slang);
					break;
				case 2:
					System.out.println("Please enter the definition you want to find");
					line=scanner.nextLine();
					TreeSet<String> searchLang=SearchSlang(line,slang);
					if(searchLang.size()!=0)
					{
						for(String temp:searchLang)
						{
							file.writeFileHistory(temp);
						}
					}
					break;
				case 3:
					System.out.println("Danh sach cac slang word da tim kiem ");
					file.printHistory();
					break;
				case 4:
					System.out.println("Please enter the slang word you want to add");
					String key=scanner.nextLine();
					System.out.println("Please enter the definition of the slang word you just typed");
					String definition= scanner.nextLine();
					addSlangWord(key,definition,slang);
					
					break;
				case 5:
					
			}	
			System.out.println("Click 1 to continue, if not click 0");
			n=Integer.parseInt(scanner.nextLine());
			
		}while(n!=0);
		
		
	}
}
