package factory;
import factory.SimpleAssignmentFactory;
public class SimpleAssignmentFactory {
	 public Assignment createAssignment(String details) {
	        return new Assignment(details);
	    }
}
