package br.com.alura.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

import br.com.alura.entidade.AgendamentoEmail;

//@TransactionManagement(TransactionManagementType.BEAN) => Utilizado caso queira fazer o controle de transação no banco
@Stateless
public class AgendamentoEmailDAO {

	@PersistenceContext //Anotação para a interface entitymanager
	private EntityManager entityManager;
	
//Se houver necessidade de controlar a transação - não deixar na mão do Servidor de Aplicação
//	@Inject
//	private UserTransaction userTransaction;
	
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
	
	public List<AgendamentoEmail> listarNaoAgendado(){
		List<AgendamentoEmail> resultado = entityManager.createQuery
				(
				"SELECT ae FROM AgendamentoEmail ae WHERE ae.agendado <> true", AgendamentoEmail.class
				)
				.getResultList();
		
		return resultado;
	}
	
	
	
	public void inserir( AgendamentoEmail agendamentoEmail) {
		entityManager.persist(agendamentoEmail);
	}

	public int alterar(String id, AgendamentoEmail agendamentoEmail) {
		
		Query query = entityManager.createQuery
		(
		"UPDATE AgendamentoEmail ae SET ae.email = :pEmail, ae.assunto = :pAssunto, ae.mensagem = :pMensagem"
		+ " WHERE ae.id = :pId"
		);
		query.setParameter("pEmail", agendamentoEmail.getEmail());
		query.setParameter("pAssunto", agendamentoEmail.getAssunto());
		query.setParameter("pMensagem", agendamentoEmail.getMensagem());
		query.setParameter("pId",  Long.parseLong(id));
		
		return query.executeUpdate();
	}

	public void deletar(String id) {
		entityManager.createQuery("DELETE FROM AgendamentoEmail ae WHERE ae.id = :pId")
				.setParameter("pId",  Long.parseLong(id))
				.executeUpdate();
	}
	
public void alterarEnviarEmail(AgendamentoEmail agendamentoEmail) {

//********Trecho de código necessário quando quer assumir o controle transacional ************
//		try {
//			userTransaction.begin();
//			entityManager.merge(agendamentoEmail);
//			userTransaction.commit();
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
		
		entityManager.merge(agendamentoEmail);
	}
}
