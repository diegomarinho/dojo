package com.diego.dojo.entity.domain;

import java.io.Serializable;

/**
 * @author Diego
 * @since 15/08/2014
 */
public abstract class BSPojoSimples<T extends Serializable> {

	public BSPojoSimples() {
		super();
	}

	/**
	 * Método para comparar os objetos BSPojo.
	 * 
	 * @param obj Objeto que deseja comparar
	 * @return <code>true</code> se os objetos forem iguais
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean equals = false;
		if ((obj != null) && (obj instanceof BSPojoSimples)) {
			if ((((BSPojoSimples<?>) obj).getId() != null) && (getId() != null)) {
				equals = ((BSPojoSimples<?>) obj).getId().equals(getId());
			} else {
				equals = obj == this;
			}
		}
		return equals;
	}

	/**
	 * Retorna o ID do objeto
	 * 
	 * @return ID do objeto
	 */
	public abstract T getId();

	/**
	 * Metodo hashcode que considera o id como identidade do objeto.
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
	 * Define o novo valor para o ID
	 * 
	 * @param id Novo valor de ID
	 */
	public abstract void setId(final T id);

}
