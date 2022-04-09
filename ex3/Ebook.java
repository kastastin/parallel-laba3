package ex3;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Ebook {
    public HashMap<String, Group> groups = new HashMap<>();

    public Ebook() {
        Group it = new Group(4, "IT");
        this.groups.put(it.getGroupName(), it);
        Group ip = new Group(5, "IP");
        this.groups.put(ip.getGroupName(), ip);
        Group ik = new Group(3, "IK");
        this.groups.put(ik.getGroupName(), ik);
    }

    public void postMark(String groupName, Integer studentName, String mark) {
        synchronized (this.groups.get(groupName).groupList.get(studentName)) {
            this.groups.get(groupName).groupList.get(studentName).add(mark);
        }
    }

    public void displayMarks() {
        for (String group : groups.keySet().stream().sorted().collect(Collectors.toList())) {
            System.out.printf("\tGroup: %s\n\n", group);
            for (Integer stud : groups.get(group).groupList.keySet().stream().sorted().collect(Collectors.toList())) {
                System.out.printf("Student %3s %2s", stud, "-");
                for (String mark : groups.get(group).groupList.get(stud)) {
                    System.out.printf("  %10s", mark);
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}