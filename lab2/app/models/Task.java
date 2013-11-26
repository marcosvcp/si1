package models;

import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

import com.google.common.base.Objects;

@Entity
public class Task extends Model implements Comparable<Task> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	public static Finder<Long, Task> find = new Finder<Long, Task>(Long.class,
			Task.class);

	@Required(message = "O nome da tarefa é obrigatório")
	private String nome;

	@Required(message = "A prioridade da tarefa é obrigatória")
	private int prioridade;

	@Required(message = "A descrição da tarefa é obrigatória")
	private String descricao;

	private boolean feita;

	public Task() {
	}

	public Task(String nome, String descricao, int prioridade) {
		this.nome = nome;
		this.prioridade = prioridade;
		this.descricao = descricao;
		this.feita = false;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static List<Task> all() {
		List<Task> allTasks = find.all();
		Collections.sort(allTasks);
		return allTasks;
	}

	public static void create(Task task) {
		task.save();
	}

	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Task otherTask) {
		return this.prioridade - otherTask.prioridade;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Task)) {
			return false;
		}
		Task otherTask = (Task) obj;
		return Objects.equal(otherTask.getNome(), this.getNome());
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getNome());
	}

	public boolean isFeita() {
		return feita;
	}

	public void setFeita(boolean feita) {
		this.feita = feita;
	}
}