package controllers;

import models.Task;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Controladora das ações de uma {@link Task}.
 */
public class Application extends Controller {
	static Form<Task> taskForm = Form.form(Task.class);

	/**
	 * Redireciona para a página inicial da aplicação
	 */
	public static Result index() {
		return redirect(routes.Application.tasks());
	}

	/**
	 * Retorna a página inicial da aplicação
	 */
	public static Result tasks() {
		return ok(views.html.index.render(Task.all(), taskForm));
	}

	/**
	 * Cria uma nova tarefa seguindo a estrutura do {@code taskForm}.
	 */
	public static Result newTask() {
		Form<Task> filledForm = taskForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index.render(Task.all(), filledForm));
		} else {
			Task.create(filledForm.get());
			return redirect(routes.Application.tasks());
		}
	}

	/**
	 * @param id
	 *            O id da tarefa.
	 * 
	 *            Atualiza a tarefa modificando seu estado.
	 */
	public static Result updateTask(Long id) {
		Task taskToChange = Task.find.byId(id);
		taskToChange.setFeita(!taskToChange.isFeita());
		taskToChange.update();
		return redirect(routes.Application.tasks());
	}

	/**
	 * Deleta a tarefa do banco de Dados.
	 */
	public static Result deleteTask(Long id) {
		Task.delete(id);
		return redirect(routes.Application.tasks());
	}
}