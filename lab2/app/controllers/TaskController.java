package controllers;

import play.i18n.Messages;
import play.mvc.Controller;

public class TaskController extends Controller {

	public static String nomeDaAplicacao() {
		return Messages.get("listaDeAfazeres");
	}

	public static String nomeDaTarefa() {
		return Messages.get("nomeDaTarefa");
	}

	public static String descricaoDaTarefa() {
		return Messages.get("descricaoTarefa");
	}
	
	public static String tasks() {
		return Messages.get("tarefas");
	}
	
	public static String addNovaTarefa(){
		return Messages.get("adicionarNovaTarefa");
	}

}
