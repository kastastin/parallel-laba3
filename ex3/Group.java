package ex3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Group {
	public HashMap<Integer, List<String>> groupList = new HashMap<>();
	private String groupName;	

	public Group(int size, String name) {
		fillGroup(size);
		this.groupName = name;
	}
	
	public String getGroupName() { return this.groupName; }
	
	public void fillGroup(int size) {
		for (int i = 1; i <= size; i++)
            groupList.put(i, new ArrayList<>());
	}
}