package br.com.murillo.hardcoregym.domain.aluno;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Telefone implements Serializable {

	@Column(name = "CELULAR_DDD" , nullable = false , length = 2)
	private Integer dddCelular;
	@Column(name = "CELULAR_NUMERO" , nullable = false , length = 9)
	private Integer numeroCelular;
	
	public Integer getDddCelular() {
		return dddCelular;
	}
	public void setDddCelular(Integer dddCelular) {
		this.dddCelular = dddCelular;
	}
	public Integer getNumeroCelular() {
		return numeroCelular;
	}
	public void setNumeroCelular(Integer numeroCelular) {
		this.numeroCelular = numeroCelular;
	}
	@Override
	public String toString() {
		return "Telefone [dddCelular=" + dddCelular + ", numeroCelular=" + numeroCelular + "]";
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(dddCelular, numeroCelular);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		return Objects.equals(dddCelular, other.dddCelular) && Objects.equals(numeroCelular, other.numeroCelular);
	}
	
	
	
	
}
