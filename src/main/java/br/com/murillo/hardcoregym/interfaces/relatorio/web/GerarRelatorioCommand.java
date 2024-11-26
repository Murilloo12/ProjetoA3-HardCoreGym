package br.com.murillo.hardcoregym.interfaces.relatorio.web;

import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaPilha;
import br.com.murillo.hardcoregym.application.service.AlunoService;
import br.com.murillo.hardcoregym.application.util.ValidationException;
import br.com.murillo.hardcoregym.domain.acesso.Acesso;

public class GerarRelatorioCommand implements RelatorioCommand {

	private final AlunoService alunoService;
	private final FacesContext facesContext;
	private final String matricula;
	private final LocalDate dataInicial;
	private final LocalDate dataFinal;

	private MinhaLista<Acesso> acessos;

	private static final MinhaPilha<MinhaLista<Acesso>> historicoRelatorios = new MinhaPilha<>();

	public GerarRelatorioCommand(AlunoService alunoService, FacesContext facesContext, String matricula,
			LocalDate dataInicial, LocalDate dataFinal) {
		this.alunoService = alunoService;
		this.facesContext = facesContext;
		this.matricula = matricula;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
	}

	@Override
	public void execute() {
		try {
			acessos = alunoService.listAcessosAlunos(matricula, dataInicial, dataFinal);
			historicoRelatorios.push(acessos);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public MinhaLista<Acesso> getUltimoRelatorio() {
		return historicoRelatorios.peek();
	}

	public MinhaLista<Acesso> desfazerRelatorio() {
		return historicoRelatorios.pop();
	}

	private MinhaLista<Acesso> converterParaMinhaLista(MinhaLista<Acesso> lista) {
		MinhaLista<Acesso> minhaLista = new MinhaLista<>();
		for (Acesso acesso : lista) {
			minhaLista.add(acesso);
		}
		return minhaLista;
	}

	public MinhaLista<Acesso> getAcessos() {
		return acessos;
	}
}
