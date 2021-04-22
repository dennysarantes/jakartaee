package br.com.alura.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

//@WebServlet("emails")
//public class AgendamentoEmailServlet extends HttpServlet {
	public class AgendamentoEmailServlet {
//	private static final long serialVersionUID = 1L;
//	
//	@Inject
//	private AgendamentoEmailServico servico;
//	
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		PrintWriter pw = resp.getWriter();
//		servico.listar().forEach(resultado -> pw.print("Os emails disponíveis são:" + resultado.getEmail()));
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		BufferedReader reader = req.getReader();
//		//Estrutura de leitura da linha, nesse exemplo: email, assunto, mensagem (Ou seja para testar, usar o POST com o body passando essa linha)
//		String[] dadosParaSalvar = reader.readLine().split(",");
//		System.out.println("String recebida: " + reader.readLine());
//		AgendamentoEmail agendamentoEmail = new AgendamentoEmail();
//		agendamentoEmail.setEmail(dadosParaSalvar[0]);
//		agendamentoEmail.setAssunto(dadosParaSalvar[1]);
//		agendamentoEmail.setMensagem(dadosParaSalvar[2]);
//		System.out.println(dadosParaSalvar[2]);
//		servico.inserir(agendamentoEmail);
//		
//	}
	
}
