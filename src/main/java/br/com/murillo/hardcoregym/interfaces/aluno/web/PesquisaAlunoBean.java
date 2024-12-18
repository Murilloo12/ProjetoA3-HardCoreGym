package br.com.murillo.hardcoregym.interfaces.aluno.web;

import java.io.Serializable;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.RequestParameterMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.application.service.AlunoService;
import br.com.murillo.hardcoregym.domain.aluno.Aluno;

@Named
@SessionScoped
public class PesquisaAlunoBean implements Serializable {
	
	@EJB
	private AlunoService alunoService;
	
	@Inject
	private FacesContext facesContext;
	
	@Inject
	@RequestParameterMap
	private Map<String, String> requestParamsMap;
	
	private String matricula;
	private String nome;
	private Integer rg;
	private Integer telefone;
	
	private MinhaLista<Aluno> alunos;
	
	public void check() {
		String clear = requestParamsMap.get("clear");
		
		if (clear != null && Boolean.valueOf(clear)) {
			matricula = null;
			nome = null;
			rg = null;
			telefone = null;
			alunos = null;
		}
	}
	
	public String pesquisar() {
		try {
			alunos = converterParaMinhaLista(alunoService.listAlunos(matricula, nome, rg, telefone));
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}
	
	public String excluir(String matricula) {
		alunoService.delete(matricula);
		return pesquisar();
	}
	
	private MinhaLista<Aluno> converterParaMinhaLista(MinhaLista<Aluno> lista) {
		MinhaLista<Aluno> minhaLista = new MinhaLista<>();
		for (Aluno item : lista) {
			minhaLista.add(item);
		}
		return minhaLista;
	}
	
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getRg() {
		return rg;
	}
	public void setRg(Integer rg) {
		this.rg = rg;
	}
	public Integer getTelefone() {
		return telefone;
	}
	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}
	
	public MinhaLista<Aluno> getAlunos() {
		return alunos;
	}
}
