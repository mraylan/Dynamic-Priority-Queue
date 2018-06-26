package clientWaitingRoom;

import java.util.ArrayList;
import java.util.List;

import clientWaitingRoom.Statistic.LinkedList;

public class SubjectList {
	List<TypeSubject> subjects;
	LinkedListStrings subject;
	
	int size;
	
	//constructor
	public SubjectList(){
		this.subjects = new ArrayList<TypeSubject>();
		this.subject = new LinkedListStrings();
		
		size = 0;
	}
	
	//getter
	public double getPriority(int i){
    	return subjects.get(i).getDuration() + subjects.get(i).getUrgency();
    }
	public double getTime(int i){
    	return subjects.get(i).getDuration()*15;
    }
	public int getType(int i){
    	return subjects.get(i).getType();
    }
	public String getDescription(int i) {
		return subjects.get(i).getDescription();
	}
	public void getItem(int i){
		System.out.format("%11s %30s %30s %30s %15s %15s",
				subjects.get(i).getType(), subjects.get(i).getTitle(), subjects.get(i).getDescription(), subjects.get(i).getProvidence(), subjects.get(i).getDuration()*15, subjects.get(i).getUrgency()*10);
        System.out.println();
	}
	public int getSize(){
    	return subjects.size();
    }
	
	//methods
	public void addSubject(TypeSubject item){
    	this.subjects.add( item );
		//this.subjects.add( new TypeSubject("", "", 1) );
    }
	public int size(){
    	return subjects.size();
    }
}
