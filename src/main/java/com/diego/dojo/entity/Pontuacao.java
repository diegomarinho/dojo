package com.diego.dojo.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Diego Almeida
 * @since 15/08/2014
 */
public class Pontuacao extends Pojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5930453650999232168L;
	private final Jogador jogador;
	private Map<Arma, Integer> armas;
	private int morreu;
	private int matou;
	private int assassinatosSeguidos;

	public Pontuacao(final Jogador jogador) {
		armas = new HashMap<Arma, Integer>();
		this.morreu = 0;
		this.matou = 0;
		this.jogador = jogador;
		this.assassinatosSeguidos = 0;
	}

	public void adicionarMorte() {
		this.morreu++;
		this.assassinatosSeguidos = 0;
	}

	public void adicionarAssassinato() {
		this.matou++;
		this.assassinatosSeguidos++;
	}

	public void adicionarArma(final Arma arma) {
		if (this.armas.containsKey(arma)) {
			Integer qtd = armas.get(arma);
			armas.put(arma, ++qtd);
		} else {
			armas.put(arma, 1);
		}
	}

	public Jogador getJogador() {
		return jogador;
	}

	public Map<Arma, Integer> getArmas() {
		return armas;
	}

	public int getMorreu() {
		return morreu;
	}


	public int getMatou() {
		return matou;
	}

	public int getAssassinatosSeguidos() {
		return assassinatosSeguidos;
	}

}
