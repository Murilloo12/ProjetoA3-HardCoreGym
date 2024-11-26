package br.com.murillo.hardcoregym.domain.interfaces;

import java.time.LocalDate;

import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.domain.acesso.Acesso;
import br.com.murillo.hardcoregym.domain.aluno.Aluno;
import br.com.murillo.hardcoregym.domain.aluno.Aluno.Situacao;

public interface AlunoInterface {
    
    public Aluno findByRG(Integer rg);
    
    public void delete(String matricula);
    
    public String getMaxMatriculaAno();
    
    public MinhaLista<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone);
    
    public MinhaLista<Aluno> listSituacoesAlunos(Situacao situacao);
    
    public MinhaLista<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal);
}
