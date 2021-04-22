package br.com.alura.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

//Esta classe SUBSTITUI a classe AgendamentoEmailServlet
//Para usar essa classe é necessário criar a classe AgendamentoEmailApplication e extender a classe Application

@Path("emails")
public class AgendamentoEmailController {
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;
	
	@GET
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listar() {
		
		List<AgendamentoEmail> listaDeAgendamentos = agendamentoEmailServico.listar();
		
		return Response.ok(listaDeAgendamentos).build();
	}
	
	@GET
	@Path("/naoagendados")
	@Produces(value = MediaType.APPLICATION_JSON)
	public Response listarPorNaoAgendado() {
		List<AgendamentoEmail> listaDeNaoAgendados = agendamentoEmailServico.listarNaoAgendado();
		
		return Response.ok(listaDeNaoAgendados).build();
	}
	
	@POST
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response inserir(AgendamentoEmail agendamentoEmail) {
		agendamentoEmailServico.inserir(agendamentoEmail);
		return Response.status(201).build();
	}
	
	@PUT
	@Path("/alterar/{id}")
	@Consumes(value = MediaType.APPLICATION_JSON)
	public Response alterar(@PathParam("id") String id, AgendamentoEmail agendamentoEmail) {
		int qtdLinhasAlteradas = agendamentoEmailServico.alterar(id, agendamentoEmail);
		System.out.println("Qtd de linhas alteradas: " + qtdLinhasAlteradas);
		return Response.ok().build();
	}
	
	
	
	@DELETE
	@Path("/deletar/{id}")
	public Response deletar (@PathParam("id") String id) {
		agendamentoEmailServico.deletar(id);
		return Response.ok().build();
		
	}
	
}
