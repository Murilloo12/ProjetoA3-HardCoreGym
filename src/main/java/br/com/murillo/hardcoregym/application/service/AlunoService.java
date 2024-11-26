package br.com.murillo.hardcoregym.application.service;

import java.time.LocalDate;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ValidationException;

import br.com.murillo.hardcoregym.application.dynamic.structures.MinhaLista;
import br.com.murillo.hardcoregym.application.util.StringUtils;
import br.com.murillo.hardcoregym.application.util.Validation;
import br.com.murillo.hardcoregym.domain.acesso.Acesso;
import br.com.murillo.hardcoregym.domain.aluno.Aluno;
import br.com.murillo.hardcoregym.domain.aluno.Aluno.Situacao;
import br.com.murillo.hardcoregym.domain.aluno.AlunoRepository;

@Stateless
public class AlunoService {
	
	@EJB
	private AlunoRepository alunoRepository;

	public void createOrUpdate (Aluno aluno) {
		if(StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		}else {
			update(aluno);
		}
			
		}
	
	
	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		
		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		
		
		aluno.gerarMatricula(maxMatricula);
		alunoRepository.store(aluno);
	}
	
	public void delete(String matricula) {
		alunoRepository.delete(matricula);
	}
	private void update(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());
		alunoRepository.update(aluno);
		
	}
		
	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}
	
	 public MinhaLista<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone) {
	        if (StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
	            throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido");
	        }
	        return alunoRepository.listAlunos(matricula, nome, rg, telefone);
	    }

	    public MinhaLista<Aluno> listSituacoesAlunos(Situacao situacao) {
	        Validation.assertNotEmpty(situacao);
	        return alunoRepository.listSituacoesAlunos(situacao);
	    }

	    public MinhaLista<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal) {
	        if (StringUtils.isEmpty(matricula) && dataInicial == null && dataFinal == null) {
	            throw new ValidationException("Pelo menos um critério de pesquisa deve ser fornecido");
	        }
	        return alunoRepository.listAcessosAlunos(matricula, dataInicial, dataFinal);
	    }
}