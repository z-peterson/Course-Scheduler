public class Main {
    public static void main(String[] args) {

        Scheduler schedule = new Scheduler();

        schedule.makeSchedule();

        try {
            schedule.writeFiles();
        } catch (Exception e) {
            System.out.println("Unable to write files");
        }

    }

}
