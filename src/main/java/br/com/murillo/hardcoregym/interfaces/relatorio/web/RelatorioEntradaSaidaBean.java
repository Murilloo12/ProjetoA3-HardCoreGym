package br.com.murillo.hardcoregym.interfaces.relatorio.web;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.application.service.AlunoService;
import br.com.murillo.hardcoregym.application.util.StringUtils;
import br.com.murillo.hardcoregym.domain.acesso.Acesso;

@Named
@RequestScoped
public class RelatorioEntradaSaidaBean {

    @EJB
    private AlunoService alunoService;

    @Inject
    private FacesContext facesContext;

    private String matricula;
    private LocalDate dataInicial;
    private LocalDate dataFinal;

    private MinhaLista<Acesso> acessos;

    private RelatorioCommand relatorioCommand;

    public void carregarAluno() {
        if (!StringUtils.isEmpty(matricula)) {
            relatorioCommand = new GerarRelatorioCommand(alunoService, facesContext, matricula, dataInicial, dataFinal);
            relatorioCommand.execute();
            acessos = converterParaMinhaLista(((GerarRelatorioCommand) relatorioCommand).getAcessos());
        }
    }

    public String gerarRelatorio() {
        try {
            acessos = converterParaMinhaLista(alunoService.listAcessosAlunos(matricula, dataInicial, dataFinal));
        } catch (ValidationException e) {
            facesContext.addMessage(null, new FacesMessage(e.getMessage()));
        }
        return null;
    }
    
    
    private MinhaLista<Acesso> converterParaMinhaLista(MinhaLista<Acesso> lista) {
        MinhaLista<Acesso> minhaLista = new MinhaLista<>();
        for (Acesso acesso : lista) {
            minhaLista.add(acesso);
        }
        return minhaLista;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public MinhaLista<Acesso> getAcessos() {
        return acessos;
    }
}
