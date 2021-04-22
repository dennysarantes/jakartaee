package br.com.alura.job;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.transaction.UserTransaction;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Singleton //Use ao invés de stateless, pois o singleton
//garante uma única instância dessa classe e não um pool e possa ocorrer envio repetido de email.
//@TransactionManagement(TransactionManagementType.CONTAINER) => por padrão é container não precisa anotar
public class AgendamentoEmailJob {
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;

	
	@Inject
	@JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
	private JMSContext jmsContext;
	
	@Resource(mappedName = "java:/jms/queue/EmailQueue") //Caminho JNDI criado no JBOSS (ver passo a passo no arquivo de instrução)
	private Queue queue;
	
	@Schedule(hour = "*", minute = "*", second = "*/10") //Sintaxe para executar de 10 em 10 segundos
	//@TransactionAttribute(TransactionAttributeType.REQUIRED) => O transection required já é default
	public void enviarEmail() {
		List<AgendamentoEmail> listarNaoAgendado = agendamentoEmailServico.listarNaoAgendado();
		
		for (AgendamentoEmail agendamentoEmail : listarNaoAgendado) {
			jmsContext.createProducer().send(queue, agendamentoEmail);
			agendamentoEmailServico.alterarEnviarEmail(agendamentoEmail);
		}
	}
	
}
