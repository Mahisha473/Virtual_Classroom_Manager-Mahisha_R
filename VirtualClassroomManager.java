package manager;
import manager.*;


import model.Classroom;
import model.Assignment;
  

public class VirtualClassroomManager {
	
	private static VirtualClassroomManager instance;
    private Map<String, Classroom> classes = new HashMap<>();
    private List<SubmissionObserver> observers = new ArrayList<>();
    private static final Logger logger = Logger.getLogger(VirtualClassroomManager.class.getName());

    private VirtualClassroomManager() { }
    public static synchronized VirtualClassroomManager getInstance() {
        if (instance == null) {
            instance = new VirtualClassroomManager();
        }
        return instance;
    }
    public void addObserver(SubmissionObserver obs) {
        observers.add(obs);
    }
    private void notifySchedule(Assignment a, Classroom c) {
        for (SubmissionObserver obs : observers) {
            obs.onAssignmentScheduled(a, c);
        }
    }
    private void notifySubmit(Assignment a, String studentId, Classroom c) {
        for (SubmissionObserver obs : observers) {
            obs.onAssignmentSubmitted(a, studentId, c);
        }
    }

    // Add a new classroom
    public void addClassroom(String className) {
        if (className == null || className.isEmpty()) {
            System.out.println("Error: Classroom name cannot be empty.");
            return;
        }
        if (classes.containsKey(className)) {
            System.out.println("Error: Classroom already exists.");
            return;
        }
        Classroom c = new SimpleClassroomFactory().createClassroom(className);
        classes.put(className, c);
        System.out.println("Classroom " + className + " has been created.");
        logger.info("Created classroom " + className);
    }

    // Remove an existing classroom
    public void removeClassroom(String className) {
        if (!classes.containsKey(className)) {
            System.out.println("Error: Classroom not found.");
            return;
        }
        classes.remove(className);
        System.out.println("Classroom " + className + " has been removed.");
        logger.info("Removed classroom " + className);
    }

    // List all classrooms
    public void listClassrooms() {
        if (classes.isEmpty()) {
            System.out.println("No classrooms available.");
            return;
        }
        System.out.println("Classrooms:");
        for (String name : classes.keySet()) {
            System.out.println("- " + name);
        }
    }

    // Enroll a student in a classroom
    public void addStudent(String studentId, String className) {
        if (studentId == null || studentId.isEmpty() || className == null || className.isEmpty()) {
            System.out.println("Error: Student ID and class name are required.");
            return;
        }
        Classroom c = classes.get(className);
        if (c == null) {
            System.out.println("Error: Classroom " + className + " does not exist.");
            return;
        }
        if (c.getStudents().contains(studentId)) {
            System.out.println("Error: Student already enrolled.");
            return;
        }
        c.addStudent(studentId);
        System.out.println("Student " + studentId + " has been enrolled in " + className + ".");
        logger.info("Student " + studentId + " added to " + className);
    }

    // List students in a classroom
    public void listStudents(String className) {
        Classroom c = classes.get(className);
        if (c == null) {
            System.out.println("Error: Classroom not found.");
            return;
        }
        if (c.getStudents().isEmpty()) {
            System.out.println("No students in class " + className);
            return;
        }
        System.out.println("Students in " + className + ":");
        for (String sid : c.getStudents()) {
            System.out.println("- " + sid);
        }
    }

    // Schedule a new assignment
    public void scheduleAssignment(String className, String details) {
        Classroom c = classes.get(className);
        if (c == null) {
            System.out.println("Error: Classroom not found.");
            return;
        }
        Assignment a = new SimpleAssignmentFactory().createAssignment(details);
        c.addAssignment(a);
        System.out.println("Assignment for " + className + " has been scheduled.");
        logger.info("Scheduled assignment \"" + details + "\" for " + className);
        notifySchedule(a, c);
    }

    // Student submits an assignment
    public void submitAssignment(String studentId, String className, String details) {
        Classroom c = classes.get(className);
        if (c == null) {
            System.out.println("Error: Classroom not found.");
            return;
        }
        if (!c.getStudents().contains(studentId)) {
            System.out.println("Error: Student " + studentId + " is not enrolled in " + className);
            return;
        }
        // Find the matching assignment (simple string match)
        Assignment match = null;
        for (Assignment a : c.getAssignments()) {
            if (a.getDetails().equals(details)) {
                match = a;
                break;
            }
        }
        if (match == null) {
            System.out.println("Error: Assignment not found for class " + className);
            return;
        }
        System.out.println("Assignment submitted by Student " + studentId + " in " + className + ".");
        logger.info("Student " + studentId + " submitted assignment \"" + details + "\" in " + className);
        notifySubmit(match, studentId, c);
    }
}


