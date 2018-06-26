/*Name: Marcos Raylan Sousa Matos
 Class: ReadFromFile
 Due Date: 10/05/2018
 Date Submitted: 14/06/2018
 */

//package clientWaitingRoom;

package clientWaitingRoom;

import java.io.*;
import java.util.*;

import clientWaitingRoom.*;

public class ReadFromFile{
	
	public String filePath;
	public List<ClientInfo> info = new ArrayList<ClientInfo>();
	
	public ReadFromFile(String file){
		this.filePath = file;
	}
	
	public void addPatient(ClientInfo info){
		this.info.add(info);
	}
	
	public List<ClientInfo> readFile(){
		
		int count = 0;
		
		try{
			//open the file
			FileReader file = new FileReader(this.filePath);
			BufferedReader buff = new BufferedReader(file);
			
			boolean eof = false;
			
			while(!eof){
				//read one line at a time
				String line = buff.readLine();
				
				if(line == null){
					eof = true;
				}
				else{
					count++;
					List<Integer> set = new ArrayList<Integer>();
					
					String[] tuple = line.split(";");
					for(int i = 4; i < tuple.length ; i++)
						set.add( Integer.parseInt(tuple[i]) );
					
					buildPatientsInfo(tuple[0], tuple[1], Integer.parseInt(tuple[2]), Integer.parseInt(tuple[3]), set);									
				}				
			}// end while
			
			buff.close();
			
		}catch(IOException e){
			//display error message when text file cannot be opened
			System.out.println("Error-- " + e.toString());	
		}
				
		return info;
	}//end readFile
	
	public void buildPatientsInfo(String name, String cpf, int arrivalTime, int departureTime, List<Integer> subjects){
		
		ClientInfo info = new ClientInfo();
		
		info.setName(name);
		info.setNumber(cpf);
		info.setArrivalTime(arrivalTime);
		info.setDepartureTime(departureTime);
		//info.setSubjects(subjects);
		
		addPatient(info);
			
	}//end buildPatientsInfo
			
}//end ReadFromFile

