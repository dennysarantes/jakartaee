package br.com.alura.job;

import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.alura.entidade.AgendamentoEmail;
import br.com.alura.servico.AgendamentoEmailServico;

@Singleton //Use ao invés de stateless, pois o singleton
//garante uma única instância dessa classe e não um pool e possa ocorrer envio repetido de email.
public class AgendamentoEmailJob {
	
	@Inject
	private AgendamentoEmailServico agendamentoEmailServico;
	
	@Schedule(hour = "*", minute = "*", second = "*/10") //Sintaxe para executar de 10 em 10 segundos
	public void enviarEmail() {
		List<AgendamentoEmail> listarNaoAgendado = agendamentoEmailServico.listarNaoAgendado();
		
		for (AgendamentoEmail agendamentoEmail : listarNaoAgendado) {
			agendamentoEmailServico.enviar(agendamentoEmail);
			agendamentoEmailServico.alterarEnviarEmail(agendamentoEmail);
		}
	}
	
}
