package Students;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Student implements Comparable<Student> {
    private int id;
    private String name;
    private double cgpa;

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCGPA() {
        return cgpa;
    }

    @Override
    public int compareTo(Student other) {
        if (this.cgpa != other.cgpa) {
            return Double.compare(other.cgpa, this.cgpa); // Higher CGPA first
        } else if (!this.name.equals(other.name)) {
            return this.name.compareTo(other.name); // Alphabetical order
        } else {
            return Integer.compare(this.id, other.id); // Smaller ID first
        }
    }
}

class Priorities {
    public List<Student> getStudents(List<String> events) {
        PriorityQueue<Student> priorityQueue = new PriorityQueue<>();
        for (String event : events) {
            String[] parts = event.split(" ");
            if (parts[0].equals("ENTER")) {
                String name = parts[1];
                double cgpa = Double.parseDouble(parts[2]);
                int id = Integer.parseInt(parts[3]);
                Student student = new Student(id, name, cgpa);
                priorityQueue.offer(student);
            } else if (parts[0].equals("SERVED")) {
                priorityQueue.poll();
            }
        }

        List<Student> remainingStudents = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            remainingStudents.add(priorityQueue.poll());
        }
        return remainingStudents;
    }
}

public class Solution {
    public static void main(String[] args) {
        Priorities priorities = new Priorities();
        List<String> events = new ArrayList<>();
        // Add your input events here
        events.add("ENTER John 3.75 50");
        events.add("ENTER Mark 3.8 24");
        events.add("ENTER Shafaet 3.7 35");
        events.add("SERVED");
        events.add("SERVED");
        events.add("ENTER Samiha 3.85 36");
        events.add("SERVED");
        events.add("ENTER Ashley 3.9 42");
        events.add("ENTER Maria 3.6 46");
        events.add("ENTER Anik 3.95 49");
        events.add("ENTER Dan 3.95 50");
        events.add("SERVED");

        List<Student> result = priorities.getStudents(events);
        if (result.isEmpty()) {
            System.out.println("EMPTY");
        } else {
            for (Student student : result) {
                System.out.println(student.getName());
            }
        }
    }
}

