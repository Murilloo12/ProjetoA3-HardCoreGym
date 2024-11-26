package br.com.murillo.hardcoregym.domain.interfaces;

import br.com.murillo.hardcoregym.domain.acesso.Acesso;
import br.com.murillo.hardcoregym.domain.aluno.Aluno;

public interface AcessoInterface {
	
	public Acesso findUltimoAcesso(Aluno aluno);
	
	public void store(Acesso acesso);

}
