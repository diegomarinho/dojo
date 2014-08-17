package com.diego.dojo.entity.domain;

import java.util.Date;

/**
 * @author Diego
 * @since 15/08/2014
 */
public abstract class BSPojo extends BSPojoSimples<Long> {


	private Long versao;

	private Boolean ativo;

	private Date dataInclusao;

	private Date dataAlteracao;

	private Date dataExclusao;

	public BSPojo() {
		super();
		setAtivo(true);
	}

	/**
	 * Retorna o status do objeto em termos logicos em relacao ao banco de dados. Caso o objeto seja removido logicamente do sistema ele
	 * retornara falso como resultado
	 * 
	 * @return Status logico do objeto
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	public Date getDataAlteracao() {
		return dataAlteracao;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}


	/**
	 * Retorna o valor da versao do objeto
	 * 
	 * @return Versao do objeto
	 */
	public Long getVersao() {
		return versao;
	}

	/**
	 * Método hashcode que considera o id como identidade do objeto.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = 1;
		if (getId() == null) {
			result = super.hashCode();
		} else {
			final int prime = 31;
			result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		}
		return result;
	}

	/**
	 * Define o valor do status logico do objeto
	 * 
	 * @param ativo
	 */
	public void setAtivo(final Boolean ativo) {
		this.ativo = ativo;
	}

	public void setDataAlteracao(final Date dataAlteracao) {
		this.dataAlteracao = dataAlteracao;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public void setDataInclusao(final Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	/**
	 * Define o novo valor para versao
	 * 
	 * @param versao Novo valor para versao
	 */
	public void setVersao(final Long versao) {
		this.versao = versao;
	}

}
