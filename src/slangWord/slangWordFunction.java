package slangWord;

import java.io.FileReader;
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
			if(slang.get(i).slangWord!=null)
			{
			// so sanh neu 2 chuoi giong nhau thi tra ve
			if(slang.get(i).slangWord.compareTo(word)==0)
			{
				System.out.println("The meaning of slang word is "+slang.get(i).definition );
				exist=true;
				break;
			}
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
					if(c.get(j).equals(line)==true)
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
	public static void printKeyList(ArrayList<slangWordDefinition> slang) {
		for(int i=0;i<slang.size();i++)
		{
			ArrayList<String> c=(ArrayList<String>)slang.get(i).keyExist.clone();
			for(int j=0;j<c.size();j++)
			{
				System.out.print(c.get(j)+" ");
			}
			System.out.println();
		}
		
	}
	public static void editSlangWord(String key, String newKey, String definition, ArrayList<slangWordDefinition>slang, int k)
	{
		// xoa di file txt
		Boolean exist=false;
		String fileName=System.getProperty("user.dir");
		fileName=fileName+"\\src\\fileContainer\\slang.txt";
		try {
			
			FileWriter fw = new FileWriter(fileName);
			fw.close();
			FileWriter fw1= new FileWriter(fileName,true);
			for(int i=0;i<slang.size();i++)
			{
						
					if(slang.get(i).slangWord.compareTo(key)==0)
					{
						slang.get(i).definition=definition;
						slang.get(i).slangWord=newKey;
						String line=newKey+"`"+definition;
						fw1.write(line+"\n");
						exist=true;
					}
					else {
						fw1.write(slang.get(i).slangWord+"`"+slang.get(i).definition+"\n");
					}
				
			}
			fw1.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		if(exist==true)
		{
			System.out.println("Successful");
			
		}
		else {
			System.out.println("Cant find slang word which you want to edit");
		}
		
		
	}

	public static void addSlangWord(String key, String definition, ArrayList<slangWordDefinition> slang)
	{
		Boolean exist=false;
		String fileName=System.getProperty("user.dir");
		fileName=fileName+"\\src\\fileContainer\\slang.txt";
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
					editSlangWord(key,key,definition,slang,i);
				}
				else {
						
						try {
							
							String line=key+"`"+definition;
							FileWriter fw = new FileWriter(fileName,true);
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
				
				FileWriter fw = new FileWriter(fileName,true);
				fw.write(line+"\n");
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void deleteSlang(String key, ArrayList<slangWordDefinition>slang)
	{
		Boolean exist=false;
		String fileName=System.getProperty("user.dir");
		fileName=fileName+"\\src\\fileContainer\\slang.txt";
		try {
			
		
		FileWriter fw= new FileWriter(fileName);
		fw.close();
		FileWriter fw1= new FileWriter(fileName,true);
		for(int i=0;i<slang.size();i++)
		{
			if(slang.get(i)!=null)
			{
				String slangWord=slang.get(i).slangWord;
				if(slangWord.compareTo(key)!=0)
				{
					fw1.write(slang.get(i).slangWord+"`"+slang.get(i).definition+"\n");
				}
				else {
					slang.remove(i);
					exist=true;
				}
			}
		}
		fw1.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		if(exist==true)
		{
			System.out.println("Successful");
			
		}
		else {
			System.out.println("Cant find slang word which you want to delete");

		}
		
	}
	public static ArrayList<slangWordDefinition> resetList()
	{
		String slangFile=System.getProperty("user.dir");
		slangFile=slangFile+"\\src\\fileContainer\\slang.txt";
		
		String originalFile=System.getProperty("user.dir");
		originalFile=originalFile+"\\src\\fileContainer\\original.txt";
		
		// ret file slangFile 
		file file= new file();
		ArrayList<slangWordDefinition> slang= new ArrayList<slangWordDefinition>();
		//doc du lieu vao slang
		file.readFile(slang, "original.txt");
		
		try {
			FileReader fin = new FileReader(originalFile);  
			 FileWriter fout = new FileWriter(slangFile);
			  int c;  
			 while ((c = fin.read()) != -1) {  
				   fout.write(c);  
			}  
			 fin.close();
			 fout.close();
			 
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		return slang;
	}
	public static int randomSlangWord(ArrayList<slangWordDefinition> slang)
	{
		int max=slang.size()-1;
		int min=0;
		int range=max-min+1;
		int rand=(int)(Math.random()*range)+min;
		return rand;
	}
	public static void RiddleWithSlangWord(ArrayList<slangWordDefinition> slang)
	{
		int slang1 = randomSlangWord(slang);
		System.out.println("Please choose the meaning of this "+slang.get(slang1).slangWord);
		int max=3;
		int min=0;
		int range=max-min+1;
		// rand se la con so giu dap an dung
		int rand=(int)(Math.random()*range)+min;
		for(int i=0;i<4;i++)
		{
			if(i==rand)
			{
				System.out.println(i+". "+slang.get(slang1).definition);
			}
			else {
				int k=randomSlangWord(slang);
				while(k==rand)
				{
					k=randomSlangWord(slang);
				}
				System.out.println(i+". "+slang.get(k).definition);
			}
		}
		Scanner scanner= new Scanner(System.in);
		int k=Integer.parseInt(scanner.nextLine());
		if(k==rand)
		{
			System.out.println("You choose the wrong answer");
			System.out.println("The correct answer is "+rand);
		}
		else
		{
			System.out.println("Your answer is true");
		}
		
		
	}
	public static void RiddleWithDefinition(ArrayList<slangWordDefinition> slang)
	{
		int slang1 = randomSlangWord(slang);
		System.out.println("Please choose the slang word which has its definition is "+slang.get(slang1).definition);
		int max=3;
		int min=0;
		int range=max-min+1;
		// rand se la con so giu dap an dung
		int rand=(int)(Math.random()*range)+min;
		for(int i=0;i<4;i++)
		{
			if(i==rand)
			{
				System.out.println(i+". "+slang.get(slang1).slangWord);
			}
			else {
				int k=randomSlangWord(slang);
				while(k==rand)
				{
					k=randomSlangWord(slang);
				}
				System.out.println(i+". "+slang.get(k).slangWord);
			}
		}
		Scanner scanner= new Scanner(System.in);
		int k=Integer.parseInt(scanner.nextLine());
		if(k==rand)
		{
			System.out.println("You choose the wrong answer");
			System.out.println("The correct answer is "+rand);
		}
		else
		{
			System.out.println("Your answer is true");
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
			System.out.println("________________________________________");
			System.out.println("Please choose what you want to do: ");
			System.out.println("1.Find the definition of slang word");
			System.out.println("2.Find all slangs word by its definition..");
			System.out.println("3.Show what you have searched before");
			System.out.println("4.Add a new slang word");
			System.out.println("5.Edit a slang word");
			System.out.println("6.Delete a slang word");
			System.out.println("7.Reset the initial slang word");
			System.out.println("8.Random a slangWord");
			System.out.println("9.Play riddle with slang word");
			System.out.println("10.Play riddle with definition");
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
					System.out.println("Please enter the slang word you want to edit");
					String key1=scanner.nextLine();
					System.out.println("Please enter the new slang word you want to replace");
					String newKey=scanner.nextLine();
					System.out.println("Please enter the definition of the slang word you just typed");
					String definition1= scanner.nextLine();
					editSlangWord(key1,newKey,definition1,slang,0);
					break;
				case 6:
					System.out.println("Please enter the slang word you want to delete");
					line=scanner.nextLine();
					System.out.println("Are you sure you want to delete(type y to continue, type n to stop");
					String key11=scanner.nextLine();
					Character key2=key11.charAt(0);
					if(key2=='y')
					{
						deleteSlang(line,slang);
					}
					break;
				case 7:
					slang=(ArrayList<slangWordDefinition>)resetList().clone();
					System.out.println("Reset successful");
					break;
				case 8:
					int slang1= randomSlangWord(slang);
					System.out.println("Slang word ngau nhien duoc tao ra la "+ slang.get(slang1).slangWord);
					break;
				case 9:
					RiddleWithSlangWord(slang);
					break;
				case 10:
					RiddleWithDefinition(slang);
					break;
				case 11:
					printKeyList(slang);
					break;
				default:
					System.out.println("You choose the wrong number");
					
					
			}	
			System.out.println("Click 1 to continue, if not click 0");
			n=Integer.parseInt(scanner.nextLine());
			
		}while(n!=0);
		
		
	}
}
