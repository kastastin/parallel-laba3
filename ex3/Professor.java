package ex3;

import java.util.List;

public class Professor implements Runnable {
    private final String name;
    private final List<String> groups;
    private final int weeks;
    private final Ebook ebook;

    public Professor(String name, List<String> groups, Ebook journal, int weeks) {
        this.name = name;
        this.groups = groups;
        this.weeks = weeks;
        this.ebook = journal;
    }

    @Override
    public void run() {
        for (int i = 0; i < weeks; i++) {
            for (String group : groups) {
                for (Integer stud : this.ebook.groups.get(group).groupList.keySet()) {
                    Double mark = (double)(Math.round(100 * Math.random() * 100)) / 100;
                    ebook.postMark(group, stud, mark + " ( " + this.name + " )");
                }
            }
        }
    }
}