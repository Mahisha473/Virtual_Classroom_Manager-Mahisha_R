package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Classroom;
public class Classroom {
	 private String name;
	    private Set<String> studentIds = new HashSet<>();
	    private List<Assignment> assignments = new ArrayList<>();

	    public Classroom(String name) {
	        this.name = name;
	    }
	    public String getName() {
	        return name;
	    }
	    public boolean addStudent(String studentId) {
	        return studentIds.add(studentId);
	    }
	    public Set<String> getStudents() {
	        return studentIds;
	    }
	    public void addAssignment(Assignment assignment) {
	        assignments.add(assignment);
	    }
	    public List<Assignment> getAssignments() {
	        return assignments;
	    }

}
