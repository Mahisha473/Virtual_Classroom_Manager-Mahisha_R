package app;
import java.util.*;

import manager.VirtualClassroomManager;
import observer.NotificationService;

public class VirtualClassroomApp {
	public static void main(String[] args) {
        VirtualClassroomManager manager = VirtualClassroomManager.getInstance();
        manager.addObserver(new NotificationService());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Virtual Classroom Manager started. Type commands (type 'exit' to quit).");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting Virtual Classroom Manager.");
                break;
            }
            String[] tokens = input.split(" ");
            String command = tokens[0];
            try {
                if (command.equalsIgnoreCase("add_classroom")) {
                    if (tokens.length == 2) manager.addClassroom(tokens[1]);
                    else System.out.println("Usage: add_classroom <className>");
                } else if (command.equalsIgnoreCase("remove_classroom")) {
                    if (tokens.length == 2) manager.removeClassroom(tokens[1]);
                    else System.out.println("Usage: remove_classroom <className>");
                } else if (command.equalsIgnoreCase("list_classrooms")) {
                    manager.listClassrooms();
                } else if (command.equalsIgnoreCase("add_student")) {
                    if (tokens.length == 3) manager.addStudent(tokens[1], tokens[2]);
                    else System.out.println("Usage: add_student <studentId> <className>");
                } else if (command.equalsIgnoreCase("list_students")) {
                    if (tokens.length == 2) manager.listStudents(tokens[1]);
                    else System.out.println("Usage: list_students <className>");
                } else if (command.equalsIgnoreCase("schedule_assignment")) {
                    if (tokens.length >= 3) {
                        // Join all remaining parts as assignment details
                        String details = input.substring(input.indexOf(tokens[2]));
                        manager.scheduleAssignment(tokens[1], details);
                    } else {
                        System.out.println("Usage: schedule_assignment <className> <assignmentDetails>");
                    }
                } else if (command.equalsIgnoreCase("submit_assignment")) {
                    if (tokens.length >= 4) {
                        String studentId = tokens[1];
                        String className = tokens[2];
                        String details = input.substring(input.indexOf(tokens[3]));
                        manager.submitAssignment(studentId, className, details);
                    } else {
                        System.out.println("Usage: submit_assignment <studentId> <className> <assignmentDetails>");
                    }
                } else {
                    System.out.println("Unknown command.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }
}

