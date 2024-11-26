package br.com.murillo.hardcoregym.domain.interfaces;

import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.domain.aluno.Estado;

public interface EstadoInterface {
	
	public MinhaLista<Estado> listEstados();

}