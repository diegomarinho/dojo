package com.diego.dojo.entity;

import java.io.Serializable;

import com.diego.dojo.entity.domain.BSPojo;

/**
 * Entidade generica POJO.
 * 
 * @author Diego
 * @since 15/08/2014
 */
public abstract class Pojo extends BSPojo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3817390436108589717L;
	private Long id;

	public Pojo() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

}
