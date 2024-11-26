package br.com.murillo.hardcoregym.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.murillo.hardcoregym.domain.aluno.Estado;
import br.com.murillo.hardcoregym.domain.aluno.EstadoRepository;
import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.domain.aluno.Aluno.Sexo;
import br.com.murillo.hardcoregym.domain.aluno.Aluno.Situacao;

@Stateless
public class DataService {
	
	@EJB
	private EstadoRepository estadoRepository;
	
	public Sexo[] getSexos() {
		return Sexo.values();
	}
	
	public Situacao[] getSituacoes() {
		return Situacao.values();
	}
	
	public MinhaLista<Estado> listEstados() {
		return converterParaMinhaLista(estadoRepository.listEstados());
	}

	// Método auxiliar para converter List para MinhaLista
	private MinhaLista<Estado> converterParaMinhaLista(List<Estado> lista) {
		MinhaLista<Estado> minhaLista = new MinhaLista<>();
		for (Estado item : lista) {
			minhaLista.add(item);
		}
		return minhaLista;
	}
}
