package Library.Datatype;

/**
 * <h1>Library.Datatype.Faculty</h1>
 * extends {@link Person}
 * Defines a faculty member of the school
 * @see Person
 * @author Zac Peterson
 * @version 1.0
 * @since May 2020
 */

public class Faculty extends Person {
    private String hireDate;
    private boolean tenured;
    private int courses;
    private boolean canTeachBio;
    private boolean canTeachCS;

    public Faculty(String firstName, String middleName, String lastName,
                   String email, String phone, String address, String city,
                   String state, String zip, int courses, String hireDate,
                   boolean tenured, boolean canTeachBio, boolean canTeachCS) {

        super(firstName, middleName, lastName, email, phone, address, city, state, zip);
        this.courses = courses;
        this.hireDate = hireDate;
        this.tenured = tenured;
        this.canTeachCS = canTeachCS;
        this.canTeachBio = canTeachBio;
        setID();
    }

    public String getHireDate() { return hireDate; }
    public void setHireDate(String hireDate) { this.hireDate = hireDate; }
    public boolean isTenured() { return tenured; }
    public void setTenured(boolean tenured) { this.tenured = tenured; }

    /**
     * Overridden Mutator method using a faculty specific algorithm to generate a unique ID
     * @see IDGen
     */
    @Override
    public void setID() { id = new IDGen().newFaculty(); }

    /**
     * Mutator Function
     * @param courses
     */
    public void setCourses(int courses) { this.courses = courses; }

    /**
     * Accessor Function
     * @return courses
     */
    public int getCourses() { return courses; }

    /**
     * Mutator Function
     * @param canTeachBio
     */
    public void setCanTeachBio(boolean canTeachBio) { this.canTeachBio = canTeachBio; }

    /**
     * Accessor Function
     * @return canTeachBio
     */
    public boolean getCanTeachBio() { return canTeachBio; }

    /**
     * Mutator Function
     * @param canTeachCS
     */
    public void setCanTeachCS(boolean canTeachCS) { this.canTeachCS = canTeachCS; }

    /**
     * Accessor Function
     * @return canTeachCS
     */
    public boolean getCanTeachCS() { return canTeachCS; }

    /**
     * Method to increment courses publicly
     */
    public void incrementCourses() { courses++; }
}