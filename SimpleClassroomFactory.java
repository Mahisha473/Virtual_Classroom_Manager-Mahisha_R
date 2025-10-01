package factory;
import factory.SimpleClassroomFactory;

public class SimpleClassroomFactory {
	public Classroom createClassroom(String name) {
        return new Classroom(name);
    }
}
