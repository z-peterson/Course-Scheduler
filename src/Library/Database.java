package Library;

import java.util.*;
import java.io.*;
import Library.Datatype.*;


public class Database {

    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Faculty> faculties = new ArrayList<Faculty>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    /**
     * Database default constructor, calls file read function and displays statistcs based off of information gathered
     * from files.
     */
    public Database() {
        studentsUtil();
        facultyUtil();
        courseUtil();

        System.out.println("Database Loaded from files:");
        System.out.println("Total Students: " + students.size());
        System.out.println("Total Faculty: " + faculties.size());
        System.out.println("Total Courses: " + courses.size() + "\n");

    }

    /**
     * Accessor method
     * @return students
     */
    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     * Mutator method
     * @param students
     */

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    /**
     * Accessor method
     * @return faculties
     */
    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }

    /**
     * Mutator method
     * @param faculties
     */
    public void setFaculties(ArrayList<Faculty> faculties) {
        this.faculties = faculties;
    }

    /**
     * Accessor method
     * @return courses
     */
    public ArrayList<Course> getCourses() {
        return courses;
    }

    /**
     * Mutator method
     * @param courses
     */
    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    /**
     *Method to read students from file input.
     */
    public void studentsUtil() {
        try {
            File file = new File("src\\Library\\Input\\student.txt");
            Scanner input = new Scanner(file);

            String[] s;
            while (input.hasNextLine()) {
                s = input.nextLine().split(", ");
                students.add(new Student(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], Double.parseDouble(s[10]), s[11]));
            }

            input.close();

            File request = new File("src\\Library\\Input\\requestedcourses.txt");
            input = new Scanner(request);
            int i = 0;

            ArrayList<String> r;
            String[] q;
            while (input.hasNextLine()) {
                q = input.nextLine().split(", ");
                r = new ArrayList<String>(Arrays.asList(q));
                students.get(i++).setRequestedCourses(r);
            }

        } catch (Exception e) {
            System.out.println("Unable to read students files!");
        }
    }

    /**
     * Method to read faculty from file input.
     */
    public void facultyUtil() {
        try {
            File file = new File("src\\Library\\Input\\faculty.txt");
            Scanner input = new Scanner(file);

            String[] s;
            while (input.hasNextLine()) {
                s = input.nextLine().split(", ");
                faculties.add(new Faculty(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], Integer.parseInt(s[9]), s[10],
                        Boolean.parseBoolean(s[11]), Boolean.parseBoolean(s[12]), Boolean.parseBoolean(s[12])));
            }
        } catch (Exception e) {
            System.out.println("Unable to read faculty file!");
        }
    }

    /**
     * Method to read parent courses from file input.
     */
    public void courseUtil() {
        try {
            File file = new File("src\\Library\\Input\\courses.txt");
            Scanner input = new Scanner(file);

            String[] s;
            while (input.hasNextLine()) {
                s = input.nextLine().split(", ");
                courses.add(new Course(Integer.parseInt(s[0]), Integer.parseInt(s[1]), s[2], Integer.parseInt(s[3]),
                        Integer.parseInt(s[4]), Integer.parseInt(s[5])));
            }


        } catch (Exception e) {
            System.out.println("Unable to read courses file!");
        }
    }

}
