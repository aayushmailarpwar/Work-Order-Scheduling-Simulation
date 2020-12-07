import java.io.*;
import java.util.*;

public abstract class WorkOrder implements Serializable
 {
	String header;
	String woId;
	String date;
	String description;
	boolean done;
	String type;
	
	public WorkOrder() 
	 {
		
		this.done = false;
	 }
	
	public void generateWoId()
	 {
		Random rand = new Random();
		int randworkorder = rand.nextInt(99999) + 10000;
		this.woId = Integer.toString(randworkorder);
		
	 }
	
	public String getWoId()
	 {
		return this.woId;
	 }
	
	public void setHeader(String name)
	{
		this.header = name;
	}
	
	public String getHeader()
	 {
		return this.woId;
	 }
	
	public void setDate(String inputdate)
	 {
		this.date = inputdate;
	 }
 }

