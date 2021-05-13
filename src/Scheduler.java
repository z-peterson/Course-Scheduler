import Library.Datatype.*;
import Library.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;

public class Scheduler {

    final int MAX_COURSES = 2;

    private Database db = new Database();
    private ArrayList<Course> unscheduled = new ArrayList<Course>();
    private ArrayList<Course> schedule = new ArrayList<Course>();

    /**
     * Method to construct a new schedule
     * @return schedule
     */
    public ArrayList<Course> makeSchedule() {

        for(int i = 0; i < db.getCourses().size(); ++i) {
            for(int j = 0; j < db.getCourses().get(i).getNumSessions(); ++j)
                newCourse(db.getCourses().get(i));
        }

        System.out.println("New Schedule Created:");
        System.out.println("Total Sessions Scheduled: " + schedule.size());
        System.out.println("Total Sessions Unscheduled: " + unscheduled.size());

        return schedule;
    }

    /**
     * Method to add a new course to the schedule, or alternatively add to the Unscheduled courses list
     * @param parent Parent course to base new course session from
     */
    public void newCourse(Course parent)  {
        Course current = new Course();

        current.setDepartment(parent.getDepartment());
        current.setDescription(parent.getDescription());
        current.setMaxStudentCount(parent.getMaxStudentCount());
        current.setMinStudentCount(parent.getMinStudentCount());
        current.setCode(parent.getCode());

        current.setSessionID(new IDGen().newCourse());

        if (!setFaculty(current)) {
            current.setTeacher(new Faculty("NA", "NA", "NA", "NA", "NA", "NA"
            , "NA", "NA", "NA", 0, "NA", false, false, false));
            unscheduled.add(current);
            return;
        }

        if (!setStudents(current)) {
            unscheduled.add(current);
            return;
        }

        schedule.add(current);
    }


    /**
     * Method to setStudents for a new course, will return a boolean depending on if there are a valid number of students
     * for the course provided to it.
     * @param current
     * @return returns a boolean
     */
    public boolean setStudents(Course current) {
        ArrayList<Student> students = new ArrayList<Student>();

        int studentCount = 0;


        for (int j = 0; j < db.getStudents().size(); ++j) {
            if(studentCount >= current.getMaxStudentCount())
                break;

            if (checkStudents(current, db.getStudents().get(j))) {
                students.add(db.getStudents().get(j));
                db.getStudents().get(j).getCourses().add(current);
                ++studentCount;
            }
        }


        if(studentCount >= current.getMinStudentCount()) {
            current.setStudents(students);
        } else {
            return false;
        }

        return true;
    }

    /**
     * Method to check the validity of a student in a potential new course. Will return a boolean dependent on if the
     * student can be added to the course, i.e. they are already in one or they have not requested that course
     * @param current
     * @param human
     * @return validCourse
     */
    public boolean checkStudents(Course current, Student human) {
        boolean validCourse = false;

        for(int i = 0; i < human.getRequestedCourses().size(); ++i) {
            if(human.getRequestedCourses().get(i).equalsIgnoreCase(current.getDescription())) {
                validCourse = true;
            }
        }

        for(int i = 0; i < schedule.size(); ++i) {
            for(int j = 0; j < human.getRequestedCourses().size(); ++j) {
                for(int k = 0; k < schedule.get(i).getStudents().size(); ++k) {
                    if (schedule.get(i).getDescription().equalsIgnoreCase(human.getRequestedCourses().get(j)) &&
                    schedule.get(i).getStudents().get(k).getID() == human.getID() &&
                    schedule.get(i).getDescription().equalsIgnoreCase(current.getDescription()))
                        validCourse = false;
                }
            }
        }

        for(int i = 0; i < current.getStudents().size(); ++i) {
            if(current.getStudents().get(i).getID() == human.getID())
                validCourse = false;
        }

        return validCourse;
    }

    /**
     * Method to set the assigned faculty for a course. Returns a boolean based on whether or not there is a valid
     * faculty member available for the course.
     * @param current
     * @return boolean value
     */
    public boolean setFaculty(Course current) {

        for(int i = 0; i < db.getFaculties().size(); ++i) {
            if(current.getDepartment() == 0 && db.getFaculties().get(i).getCanTeachBio() ||
                    current.getDepartment() == 1 && db.getFaculties().get(i).getCanTeachCS()) {
                if(db.getFaculties().get(i).getCourses() < MAX_COURSES) {
                    current.setTeacher(db.getFaculties().get(i));
                    db.getFaculties().get(i).incrementCourses();
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Method to write output for the newly created schedule and unscheduled courses list. Accesses database to look for
     * students with or without a schedule. Prints how many students are without a schedule to console.
     * @throws IOException
     */
    public void writeFiles() throws IOException{
        File file = new File("src\\Library\\Output\\ScheduledCourseSessions.txt");

        FileWriter output = new FileWriter(file, false);
        for(int i = 0; i < schedule.size(); ++i) {
            output.write("Course #" + schedule.get(i).getSessionID() + "\n");
            output.write(schedule.get(i).getDescription() + "\n");
            output.write("Teacher: ");
            output.write(schedule.get(i).getTeacher().getLastName() + " " + schedule.get(i).getTeacher().getID() + "\n");


            for(int j = 0; j < schedule.get(i).getStudents().size(); ++j) {
                output.write(schedule.get(i).getStudents().get(j).getFirstName() + " " +
                        schedule.get(i).getStudents().get(j).getMiddleName() + " " +
                        schedule.get(i).getStudents().get(j).getLastName() + " " +
                        schedule.get(i).getStudents().get(j).getID() + "\n");
            }
            output.write("\n\n");
        }

        output.flush();
        output.close();

        file = new File("src\\Library\\Output\\UnscheduledCourseSessions.txt");
        output = new FileWriter(file, false);
        for(int i = 0; i < unscheduled.size(); ++i) {
            output.write("Course #" + unscheduled.get(i).getSessionID() + "\n");
            output.write(unscheduled.get(i).getDescription() + "\n");
            output.write(unscheduled.get(i).getTeacher().getLastName() + " " + unscheduled.get(i).getTeacher().getID() + "\n");
            output.write("Required " + unscheduled.get(i).getMinStudentCount() + " Students\n");
            /*for(int j = 0; j < unscheduled.get(i).getStudents().size(); ++j) {
                output.write(unscheduled.get(i).getStudents().get(j).getFirstName() + " " +
                        unscheduled.get(i).getStudents().get(j).getMiddleName() + " " +
                        unscheduled.get(i).getStudents().get(j).getLastName() + " " +
                        unscheduled.get(i).getStudents().get(j).getID() + "\n");
            }*/
            output.write("\n");


        }
        output.flush();
        output.close();

        file = new File("src\\Library\\Output\\FacultyList.txt");
        output = new FileWriter(file, false);

        for(int i = 0; i < db.getFaculties().size(); ++i) {
            output.write(db.getFaculties().get(i).getFirstName() + " " + db.getFaculties().get(i).getMiddleName() + " "
                    + db.getFaculties().get(i).getLastName() + "\n");

            for(int j = 0; j < schedule.size(); ++j) {
                if(schedule.get(j).getTeacher().getID() == db.getFaculties().get(i).getID()) {
                    output.write(schedule.get(j).getSessionID() + " " + schedule.get(j).getStudents().size() + " Students\n");
                }
            }
            output.write("\n");
        }
        output.flush();
        output.close();

        file = new File("src\\Library\\Output\\ScheduledStudents.txt");
        output = new FileWriter(file, false);
        for(int i = 0; i < db.getStudents().size(); ++i) {
            if(db.getStudents().get(i).getCourses().size() > 0) {
                output.write("Student " + db.getStudents().get(i).getID() + "\n");
                output.write(db.getStudents().get(i).getFirstName() + " " + db.getStudents().get(i).getMiddleName()
                        + " " + db.getStudents().get(i).getLastName() + "\n");
                for(int j = 0; j < db.getStudents().get(i).getCourses().size(); ++j) {
                    if(db.getStudents().get(i).getCourses().get(j).getStudents().size() != 0 ) {
                        output.write(db.getStudents().get(i).getCourses().get(j).getSessionID() + " " +
                                db.getStudents().get(i).getCourses().get(j).getDescription() + " " +
                                db.getStudents().get(i).getCourses().get(j).getStudents().size() + " Students\n");
                    }
                }
                output.write("\n");
            }
        }
        output.flush();
        output.close();

        int unscheduledStudents = 0;

        file = new File("src\\Library\\Output\\UnscheduledStudents.txt");
        output = new FileWriter(file, false);
        for(int i = 0; i < db.getStudents().size(); ++i) {
            if(db.getStudents().get(i).getCourses().size() == 0) {
                output.write("Student " + db.getStudents().get(i).getID() + "\n");
                output.write(db.getStudents().get(i).getFirstName() + " " + db.getStudents().get(i).getMiddleName()
                        + " " + db.getStudents().get(i).getLastName() + "\n");
                output.write("\n");
                unscheduledStudents++;
            }
        }

        System.out.println("Total Unscheduled Students: " + unscheduledStudents);

        output.flush();
        output.close();
    }

    public ArrayList<Course> getUnscheduled() { return unscheduled; }

}
