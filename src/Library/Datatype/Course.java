package Library.Datatype;

import java.util.*;

/**
 * <h1>Library.Datatype.Course</h1>
 * Defines a course which may be used as a parent or an individual session of a course
 * @author Zac Peterson
 * @version 1.0
 * @since May 2020
 */

public class Course {
    private int department;
    private String description;

    private int code;
    private int minStudentCount;
    private int maxStudentCount;
    private int sessionID;
    private Faculty teacher;


    private int numSessions;
    private ArrayList<Student> students = new ArrayList<Student>();

    /**
     * Default constructor
     */
    public Course() {}

    /**
     * Copy constructor for a Course class. Makes a new course from data passed to it.
     * @param department
     * @param code
     * @param description
     * @param minStudents
     * @param maxStudents
     * @param numSessions
     */
    public Course(int department, int code, String description, int minStudents, int maxStudents, int numSessions) {
        this.department = department;
        this.code = code;
        this.description = description;
        this.minStudentCount = minStudents;
        this.maxStudentCount = maxStudents;
        this.numSessions = numSessions;
    }

    /**
     * Accessor Method
     * @return department
     */
    public int getDepartment() { return department; }

    /**
     * Mutator Method
     * @param department
     */
    public void setDepartment(int department) { this.department = department; }

    /**
     * Accessor Method
     * @return code
     */
    public int getCode() { return code; }

    /**
     * Mutator Method
     * @param code
     */
    public void setCode(int code) { this.code = code; }

    /**
     * Accessor Method
     * @return description
     */
    public String getDescription() { return description; }

    /**
     * Mutator Method
     * @param description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Accessor Method
     * @return minStudentCount
     */
    public int getMinStudentCount() { return minStudentCount; }

    /**
     * Mutator Method
     * @param minStudentCount
     */
    public void setMinStudentCount(int minStudentCount) { this.minStudentCount = minStudentCount; }

    /**
     * Accessor Method
     * @return maxStudentCount
     */
    public int getMaxStudentCount() { return maxStudentCount; }

    /**
     * Mutator Method
     * @param maxStudentCount
     */
    public void setMaxStudentCount(int maxStudentCount) { this.maxStudentCount = maxStudentCount; }

    /**
     * Accessor Method
     * @return teacher
     */
    public Faculty getTeacher() { return teacher; }

    /**
     * Mutator Method
     * @param teacher
     */
    public void setTeacher(Faculty teacher) { this.teacher = teacher; }

    /**
     * Accessor Method
     * @return students
     */
    public ArrayList<Student> getStudents() { return students; }

    /**
     * Mutator Method
     * @param students
     */

    public void setStudents(ArrayList<Student> students) { this.students = students; }

    /**
     * Accessor Method
     * @return sessionID
     */
    public int getSessionID() { return sessionID; }

    /**
     * Mutator method
     * @param sessionID
     */
    public void setSessionID(int sessionID) { this.sessionID = sessionID; }

    /**
     * Accessor Method
     * @return numSessions
     */
    public int getNumSessions() { return numSessions; }

    /**
     * Mutator method
     * @param numSessions
     */
    public void setNumSessions(int numSessions) { this.numSessions = numSessions; }

}


