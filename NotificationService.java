package observer;
import observer.NotificationService;
public class NotificationService implements SubmissionObserver {
	 public void onAssignmentScheduled(Assignment assignment, Classroom classroom) {
	        System.out.println("[Observer] Assignment \"" + assignment.getDetails() +
	            "\" scheduled for class " + classroom.getName());
	 }
}

