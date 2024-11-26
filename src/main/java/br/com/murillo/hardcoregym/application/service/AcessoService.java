package br.com.murillo.hardcoregym.application.service;

import javax.ejb.EJB;

import javax.ejb.Stateless;
import javax.validation.ValidationException;

import br.com.murillo.hardcoregym.application.util.StringUtils;
import br.com.murillo.hardcoregym.domain.acesso.Acesso;
import br.com.murillo.hardcoregym.domain.acesso.AcessoRepository;
import br.com.murillo.hardcoregym.domain.acesso.TipoAcesso;
import br.com.murillo.hardcoregym.domain.aluno.Aluno;
import br.com.murillo.hardcoregym.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private AlunoRepository alunoRepository;
	
	public TipoAcesso registrarAcesso(String matricula , Integer rg) {
		if(StringUtils.isEmpty(matricula)&& rg == null) {
			throw new ValidationException("E preciso fornecer a matricula ou rg do aluno");
		}
		Aluno aluno;
		if(StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByRG(rg);
		}else {
			aluno = alunoRepository.findByMatricula(matricula);
		}
		
		if(aluno == null) {
			throw new ValidationException("O aluno nao foi encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		
		TipoAcesso tipoAcesso;
		
		if(ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso = new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);
		}else {
			tipoAcesso =ultimoAcesso.registrarAcesso();
		}
		
		return tipoAcesso;
		
	}
}