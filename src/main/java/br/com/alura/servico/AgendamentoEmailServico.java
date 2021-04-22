package br.com.alura.servico;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.alura.dao.AgendamentoEmailDAO;
import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailServico {
	
	private static final Logger LOGGER = Logger.getLogger(AgendamentoEmailServico.class.getName());
	
	@Inject
	private AgendamentoEmailDAO dao;
	
	public List<AgendamentoEmail> listar(){
		//AgendamentoEmailDAO dao = new AgendamentoEmailDAO(); -> O Jboss injeta a classe DAO
		return dao.listar();
	}
	
	
	public List<AgendamentoEmail> listarNaoAgendado(){
		return dao.listarNaoAgendado();
	}
	
	public void inserir(AgendamentoEmail agendamentoEmail) {
		agendamentoEmail.setAgendado(false);
		dao.inserir(agendamentoEmail);
	}

	
	public int alterar(String id, AgendamentoEmail agendamentoEmail) {
		return dao.alterar(id, agendamentoEmail);
	}


	public void deletar(String id) {
		dao.deletar(id);
	}
	
	public void enviar(AgendamentoEmail agendamentoEmail) {

		try {
			Thread.sleep(5000);
			LOGGER.info("O email do(a) usuario(a) " + agendamentoEmail.getEmail() + " foi enviado.");
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}

	//@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public void alterarEnviarEmail(AgendamentoEmail agendamentoEmail) {
		//if(agendamentoEmail.getEmail().equals("dennys.arantes@gmail.com")) {
			//throw new RuntimeException("Não foi possível fazer a alteração.");
		//}else {
			agendamentoEmail.setAgendado(true);
			dao.alterarEnviarEmail(agendamentoEmail);
		//}
		
		
	
	}
	
}
