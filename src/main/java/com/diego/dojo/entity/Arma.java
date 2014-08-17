package com.diego.dojo.entity;

/**
 * @author Diego
 * @since 15/08/2014
 */
public class Arma extends Pojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8811734585047607593L;
	private final String nome;

	public Arma(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
