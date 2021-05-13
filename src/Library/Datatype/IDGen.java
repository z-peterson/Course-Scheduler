package Library.Datatype;

import java.util.Random;

public class IDGen {

    Random rand = new Random();

    /**
     * Method to generate a random number for a student ID.
     * @return
     */
    public int newStudent(){
        return rand.nextInt(10000);
    }

    /**
     * Method to generate a random number for a faculty ID.
     * @return
     */
    public int newFaculty(){
        return rand.nextInt(20000);
    }

    /**
     * Method to generate a random number for a course session ID.
     * @return
     */
    public int newCourse(){ return rand.nextInt(30000); }
}
