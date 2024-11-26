package br.com.murillo.hardcoregym.interfaces.shared.web;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.application.service.DataService;
import br.com.murillo.hardcoregym.domain.aluno.Aluno.Sexo;
import br.com.murillo.hardcoregym.domain.aluno.Aluno.Situacao;
import br.com.murillo.hardcoregym.domain.aluno.Estado;

@Named
@ApplicationScoped
public class DataBean implements Serializable {
	
	@EJB
	private DataService dataService;

	public Sexo[] getSexos() {
		return dataService.getSexos();
	}
	
	public Situacao[] getSituacoes() {
		return dataService.getSituacoes();
	}
	
	public MinhaLista<Estado> getEstados() {
		return converterParaMinhaLista(dataService.listEstados());
	}
	
	private MinhaLista<Estado> converterParaMinhaLista(MinhaLista<Estado> lista) {
		MinhaLista<Estado> minhaLista = new MinhaLista<>();
		for (Estado item : lista) {
			minhaLista.add(item);
		}
		return minhaLista;
	}
	
	public String formatTelefone(Integer ddd, Integer numero) {
		if (ddd == null || numero == null) {
			return "";
		}
		return "(" + ddd + ")" + numero;
	}
}
