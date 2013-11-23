package models;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Objects;

public class Task implements Comparable<Task> {

	private Long id;
	private String label;
	private int priority;
	private String description;

	public Task(String label, String description, int priority) {
		this.label = label;
		this.priority = priority;
		this.description = description;
	}

	public List<Task> all() {
		return new ArrayList<Task>();
	}

	public static void create(Task task) {
	}

	public static void delete(Long id) {
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int compareTo(Task otherTask) {
		return this.priority - otherTask.priority;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Task)) {
			return false;
		}
		Task otherTask = (Task) obj;
		return Objects.equal(otherTask.getLabel(), this.getLabel());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getLabel());
	}
}