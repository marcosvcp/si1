package models;

import java.util.ArrayList;
import java.util.List;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.google.common.base.Objects;

public class Task extends Model implements Comparable<Task> {

	public Long id;

	public static Finder<Long, Task> find = new Finder(Long.class, Task.class);

	@Required
	private String label;

	@Required
	private int priority;

	@Required
	private String description;

	public Task() {
	}

	public Task(String label, String description, int priority) {
		this.label = label;
		this.priority = priority;
		this.description = description;
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

	public static List<Task> all() {
		return find.all();
	}

	public static void create(Task task) {
		task.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
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