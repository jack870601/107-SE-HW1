

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


abstract public class DataBase {
	public static String UPDATE_RESULT="Update finish";
	public static String DELETE_RESULT="Delete finish";
	abstract public void addAnEntry(String name, String number);
	abstract public String searchPhoneNumber(String name);
	abstract public String updateData(String name,String number);
	abstract public String deleteData(String name);
}	


class Text extends DataBase{

	private String fileName;
	public Text() {
		fileName="Data.txt";
		File file = new File(fileName);
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Text(String name) {
		fileName=name;
		File file=new File(fileName);
		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
		
	@Override
	public void addAnEntry(String name, String number) {
		FileWriter fw;
		try {
		fw = new FileWriter(fileName,true);
		fw.write(name + " " + number + System.getProperty("line.separator"));
		fw.flush();
		fw.close();	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String searchPhoneNumber(String name) {
		FileReader fr;
		try {
			fr = new FileReader(fileName);
			//@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);

			while(br.ready())
			{
				String data_readLine = br.readLine();
				String[] data = data_readLine.split(" ");
				if(data[0].equals(name))
					return data[1];
			}
			//br.close();
			fr.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String updateData(String name, String number) {
		String wait_to_write="";
		String result=null;
		FileReader fr;
		try {
			fr = new FileReader(fileName);
			//@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(fr);
			while(br.ready())
			{
				String data_readLine = br.readLine();
				String[] data = data_readLine.split(" ");
				// System.out.println("data[0] = " + data[0]);
				// System.out.println("data[1] = " + data[1]);
				if(data[0].equals(name))				
				{
					data[1]=number;
					result=UPDATE_RESULT;
				}
				wait_to_write+= (data[0]+" "+data[1]+"\r\n");
			}
			//br.close();
			fr.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(wait_to_write);
			fw.flush();
			fw.close();	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String deleteData(String name) {
		String wait_to_write="";
		FileReader fr;
		String result=null;
		try {
			fr = new FileReader(fileName);
			//@SuppressWarnings("resource")
			BufferedReader br =new BufferedReader(fr);
			while(br.ready())
			{
				String data_readLine = br.readLine();
				String[] data = data_readLine.split(" ");
				if(data[0].equals(name))
				{
					result=DELETE_RESULT;
				}else {
					wait_to_write+= (data[0]+" "+data[1]+"\r\n");
				}
			}
			//br.close();
			fr.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FileWriter fw;
		try {
			fw = new FileWriter(fileName);
			fw.write(wait_to_write);
			fw.close();	
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
}






