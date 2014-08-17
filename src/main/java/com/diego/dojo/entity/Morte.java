package com.diego.dojo.entity;

import java.util.Date;

/**
 * @author Diego
 * @since 15/08/2014
 */
public class Morte extends Pojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8376017089115377046L;
	private final Jogador matador;
	private final Jogador morto;
	private final Arma arma;
	private final Date data;

	public Morte(Jogador matador, Jogador morto, Arma arma, Date data) {
		this.matador = matador;
		this.morto = morto;
		this.arma = arma;
		this.data = data;
	}

	public Jogador getMatador() {
		return matador;
	}

	public Jogador getMorto() {
		return morto;
	}

	public Arma getArma() {
		return arma;
	}

	public Date getData() {
		return data;
	}
	
}
