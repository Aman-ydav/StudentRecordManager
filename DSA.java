
import java.util.*;

public class DSA {

    public static HashMap<Integer, Student> map = new HashMap<>();

    public static void addStudent(Student s) {
        map.put(s.getRollNo(), s);
    }

    public static void viewAllStudents() {
        if (map.isEmpty()) {
            System.out.println("No student found");
            return;
        }
        for (Student s : map.values()) {
            System.out.println(s);
        }
    }

    public static Student searchByRoll(int roll) {
        return map.get(roll);
    }

    public static boolean deleteByRoll(int roll) {
        return map.remove(roll) != null;
    }

    public static List<Student> getStudentsSortedByName() {
        List<Student> list = new ArrayList<>(map.values());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Student s1 = list.get(i);
                Student s2 = list.get(j);
                if (s1.getName().compareTo(s2.getName()) > 0) {
                    list.set(i, s2);
                    list.set(j, s1);
                }
            }
        }
        return list;
    }

    public static List<Student> getStudentsSortedByMarks() {
        List<Student> list = new ArrayList<>(map.values());
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Student s1 = list.get(i);
                Student s2 = list.get(j);
                if (s1.getMarks() < s2.getMarks()) {
                    list.set(i, s2);
                    list.set(j, s1);
                }
            }
        }
        return list;
    }

    public static List<Student> getAllStudents() {
        return new ArrayList<>(map.values());
    }

}
