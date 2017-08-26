import java.io.*;
import java.util.*;

public class UserManager {
	public ArrayList<User> users = new ArrayList<User>();
	public void addUser(User user){
		users.add(user);
	}
	public User getUserByIndex(int index){
		return users.get(index);
	}
	public User getUserById(int id){
		for(int i = 0; i<users.size(); i++){
			if(users.get(i).getID()==id){
				return users.get(i);
			}
		}
		return null; //return null if that user cannot be found
	}
	public ArrayList<User> getUserByName(String query){ //search users by name, return an arrayList of matching results
		ArrayList<User> results = new ArrayList<User>();
		for(int i = 0; i<users.size(); i++){
			if(users.get(i).getName().contains(query)){
				results.add(users.get(i));
			}
		}
		return results;
	}
}
