package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import br.com.alura.entidade.AgendamentoEmail;

@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext //Anotação para a interface entitymanager
	private EntityManager entityManager;
	
	
	//Com o servidor de aplicação, não é necessário tratar o entitymanager, O JBOSS será o responsável.
//	public AgendamentoEmailDAO() {
//		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendamentoEmailDs");
//		this.entityManager = emf.createEntityManager();
//	}
	
	public List<AgendamentoEmail> listar(){
		//entityManager.getTransaction().begin(); //Não é necessário com o JBOSS 
		List<AgendamentoEmail> resultado = entityManager.createQuery
				(
				"SELECT ae FROM AgendamentoEmail ae", AgendamentoEmail.class
				)
				.getResultList();
		//entityManager.getTransaction().commit(); //JBOSS será o responsável
		//entityManager.close(); //Jboss será o responsável
		
		return resultado;
		
	}
	
	public void inserir( AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}
	
}
