//CHECKSTYLE:OFF - Pela arquitetura o único delegate do sistema.
package com.diego.dojo.business;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.diego.dojo.entity.CategoryEnum;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Morte;
import com.diego.dojo.entity.Partida;
import com.diego.dojo.entity.Pontuacao;
import com.diego.dojo.util.CalendarUtil;

/**
 * @author Diego
 * @since 15/08/2014
 */
public class EventoMB {	// NOPMD - ExcessiveClassLength - Delegate.

	private CalendarUtil calendarUtil;

	public EventoMB() {
		calendarUtil = new CalendarUtil();
	}

	/**
	 * Cria uma nova partida.
	 * 
	 * @param inicio
	 * @param log
	 * @return
	 */
	public Partida criarPartida(Date inicio, String log) {
		log = log.substring(CategoryEnum.INICIO_PARTIDA.getDescricao().length());
		final String nome = log.substring(0, log.indexOf(" "));

		return new Partida(inicio, nome);
	}

	/**
	 * Adiciona uma nova morte.
	 * 
	 * @param partida
	 * @param morte
	 */
	public void adicionarMorte(final Partida partida, final Morte morte) {
		partida.getMortes().add(morte);

		Pontuacao pontuacaoMatador = retornarPontuacao(partida.getPontuacoes(), morte.getMatador());
		pontuacaoMatador.adicionarAssassinato();
		pontuacaoMatador.adicionarArma(morte.getArma());

		Pontuacao pontuacaoMorto = retornarPontuacao(partida.getPontuacoes(), morte.getMorto());
		pontuacaoMorto.adicionarMorte();

		validarQtdeDeMortesEmUmMinuto(partida, morte);
	}
	
	/**
	 * Se ja existir uma pontuação para esse jogador retorna ela, caso contrario
	 * cria uma nova e retorna.
	 */
	protected Pontuacao retornarPontuacao(Set<Pontuacao> pontuacoes, Jogador jogador) {
		
		// valida se ja existe a pontuação
		for (Pontuacao pontuacao : pontuacoes) {
			if (pontuacao.getJogador().equals(jogador)) {
				return pontuacao;
			}
		}

		final Pontuacao pontuacao = new Pontuacao(jogador);
		pontuacoes.add(pontuacao);
		return pontuacao;
	}

	/**
	 * Validar se um jogador matou cinco vezes dentro de 1 minuto.
	 * 
	 * @param partida
	 * @param morte
	 */
	private void validarQtdeDeMortesEmUmMinuto(final Partida partida, final Morte morte) {
		
		int countMortes = 0;
		List<Morte> mortes = partida.getMortes();

		for (Morte morteAtual : mortes) {
			
			if (morteAtual.getMatador().equals(morte.getMatador())) {
				if (calendarUtil.exibirPartidaEmUmMinuto(morteAtual.getData(), morte.getData())) {
					countMortes++;
				} else {
					countMortes = 0;
				}
			}
		}
		
		if (countMortes >= 5) {
			partida.getJogadorMatouMaisDeCincoVezesUmMinuto().add(morte.getMatador());
		}
	}

}
