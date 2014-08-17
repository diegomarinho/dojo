package com.diego.dojo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Entidade responsavel por quardar os atributos de uma partida no jogo.
 * 
 * @author Diego
 * @since 15/08/2014
 */
public class Partida extends Pojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2672563270349427668L;
	private final String nome;
	private List<Morte> mortes;
	private final Date inicio;
	private Date fim;
	private Set<Pontuacao> pontuacoes;
	private Set<Jogador> jogadorMatouMaisDeCincoVezesUmMinuto;

	public Partida(Date inicio, String nome) {
		mortes = new ArrayList<Morte>();
		pontuacoes = new HashSet<Pontuacao>();
		jogadorMatouMaisDeCincoVezesUmMinuto = new HashSet<Jogador>();
		this.inicio = inicio;
		this.nome = nome;
	}

	public Date getInicio() {
		return inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public List<Morte> getMortes() {
		return mortes;
	}

	public String getNome() {
		return nome;
	}

	public Set<Pontuacao> getPontuacoes() {
		return pontuacoes;
	}

	public Set<Jogador> getJogadorMatouMaisDeCincoVezesUmMinuto() {
		return jogadorMatouMaisDeCincoVezesUmMinuto;
	}

}
