package slangWord;

import java.util.ArrayList;
import java.util.Scanner;

import fileContainer.file;

public class slangWordFunction {
	public static void main(String[] args) {
		file file=new file();
		String fileName="slang.txt";
		Scanner scanner=new Scanner(System.in);
		ArrayList<slangWordDefinition> slang= new ArrayList<slangWordDefinition>();
		int n=0;
		int choice=0;
		String line="";
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
					
			}	
			
			
			System.out.println("Click 1 to continue, if not click 0");
			n=Integer.parseInt(scanner.nextLine());
			
		}while(n!=0);
		
		//goi ham docfile
		file.readFile(slang, fileName);
	}
}
