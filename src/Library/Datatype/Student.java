package Library.Datatype;

import java.util.*;

/**
 * <h1>Library.Datatype.Student</h1>
 * extends {@link Person}
 * Defines a student who attends the school.
 * @see Person
 * @author Zac Peterson
 * @version 1.0
 * @since May 2020
 */
public class Student extends Person{
    private String dateOfBirth;
    private double gpa;
    private String registrationDate;

    private ArrayList<String> requestedCourses = new ArrayList<String>();

    private ArrayList<Course> courses = new ArrayList<Course>();

    public Student(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, String dateOfBirth, double gpa,
                   String registrationDate){
        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.dateOfBirth = dateOfBirth;
        this.gpa = gpa;
        this.registrationDate = registrationDate;
        setID();
    }

    /**
     * Accessor Method
     * @return dateOfBirth
     */
    public String getDateOfBirth() { return dateOfBirth; }

    /**
     * Mutator Method
     * @param dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    /**
     * Accessor Method
     * @return gpa
     */
    public double getGpa() { return gpa; }

    /**
     * Mutator Method
     * @param gpa
     */
    public void setGpa(double gpa) { this.gpa = gpa; }

    /**
     * Accessor Method
     * @return registrationDate
     */
    public String getRegistrationDate() { return registrationDate; }

    /**
     * Mutator Method
     * @param registrationDate
     */
    public void setRegistrationDate(String registrationDate) { this.registrationDate = registrationDate; }

    /**
     * Overridden Mutator Method using a student specific algorithm to generate a unique ID
     * @see IDGen
     */
    @Override
    public void setID() {
        id = new IDGen().newStudent();
    }

    public ArrayList<String> getRequestedCourses() {
        return requestedCourses;
    }

    public void setRequestedCourses(ArrayList<String> requestedCourses) { this.requestedCourses = requestedCourses; }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }


}