package observer;
import observer.SubmissionObserver;
public interface SubmissionObserver {
	void onAssignmentScheduled(Assignment assignment, Classroom classroom);
    void onAssignmentSubmitted(Assignment assignment, String studentId, Classroom classroom);
}
