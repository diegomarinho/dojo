package com.diego.dojo.business;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.diego.dojo.business.EventoMB;
import com.diego.dojo.entity.Arma;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Morte;
import com.diego.dojo.entity.Partida;
import com.diego.dojo.entity.Pontuacao;

/**
 * @author Diego
 * @since 18/08/2014
 */
public class TestEventoMB {
	
	@Test
	public void testNovaPartida() {
		EventoMB servico = new EventoMB();
		Date data = new Date();
		Partida partida = servico.criarPartida(data, "New match 11348965 has started");

		Assert.assertEquals(data, partida.getInicio());
		Assert.assertEquals("11348965", partida.getNome());
	}

	@Test
	public void testPontuacaoNovoJogador() {

		EventoMB servico = new EventoMB();
		Jogador jogador = new Jogador("Diego");
		Pontuacao pontuacao = servico.retornarPontuacao(new HashSet<Pontuacao>(), jogador);

		Assert.assertEquals(0, pontuacao.getMatou());
		Assert.assertEquals(0, pontuacao.getMorreu());
		Assert.assertEquals(jogador, pontuacao.getJogador());
		Assert.assertEquals(0, pontuacao.getArmas().size());
	}

	@Test
	public void testPontuacaoJogadorExistente() {
		EventoMB servico = new EventoMB();
		Jogador jogador = new Jogador("Diego");
		Set<Pontuacao> pontuacoes = new HashSet<Pontuacao>();
		Pontuacao pontuacao = new Pontuacao(jogador);
		pontuacao.adicionarAssassinato();
		pontuacao.adicionarAssassinato();
		pontuacao.adicionarMorte();
		pontuacao.adicionarArma(new Arma("AR15"));
		pontuacoes.add(pontuacao);

		Pontuacao pontuacaoRetornada = servico.retornarPontuacao(pontuacoes, jogador);

		Assert.assertEquals(2, pontuacaoRetornada.getMatou());
		Assert.assertEquals(1, pontuacaoRetornada.getMorreu());
		Assert.assertEquals(jogador, pontuacaoRetornada.getJogador());
		Assert.assertEquals(1, pontuacaoRetornada.getArmas().size());
	}
	
	@Test
	public void testAdicionarCincoMortes() {
		EventoMB servico = new EventoMB();
		Jogador matador = new Jogador("Diego");
		Jogador morto = new Jogador("Jonas");
		Arma arma = new Arma("Glock");
		Partida partida = new Partida(new Date(), "Partida1");

		Morte morte = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte);
		Morte morte2 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte2);
		Morte morte3 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte3);
		Morte morte4 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte4);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 1);
		Morte morte5 = new Morte(matador, morto, arma, c.getTime());
		servico.adicionarMorte(partida, morte5);
		Morte morte6 = new Morte(matador, morto, arma, c.getTime());
		servico.adicionarMorte(partida, morte6);
		Morte morte7 = new Morte(matador, morto, arma, c.getTime());
		servico.adicionarMorte(partida, morte7);
		Morte morte8 = new Morte(matador, morto, arma, c.getTime());
		servico.adicionarMorte(partida, morte8);
		Morte morte9 = new Morte(matador, morto, arma, c.getTime());
		servico.adicionarMorte(partida, morte9);

		Assert.assertTrue(partida.getJogadorMatouMaisDeCincoVezesUmMinuto().contains(matador));
	}

	@Test
	public void testMorteJogador() {
		EventoMB servico = new EventoMB();
		Jogador matador = new Jogador("Diego");
		Jogador morto = new Jogador("Jonas");
		Arma arma = new Arma("Glock");
		Morte morte = new Morte(matador, morto, arma, new Date());
		Partida partida = new Partida(new Date(), "Partida2");
		servico.adicionarMorte(partida, morte);

		Pontuacao pontuacaoMatador = servico.retornarPontuacao(partida.getPontuacoes(), matador);
		Assert.assertEquals(1, pontuacaoMatador.getMatou());
		Assert.assertEquals(0, pontuacaoMatador.getMorreu());
		Assert.assertEquals(matador, pontuacaoMatador.getJogador());
		Assert.assertEquals(1, pontuacaoMatador.getArmas().get(arma).intValue());

		Pontuacao pontuacaoMorto = servico.retornarPontuacao(partida.getPontuacoes(), morto);
		Assert.assertEquals(0, pontuacaoMorto.getMatou());
		Assert.assertEquals(1, pontuacaoMorto.getMorreu());
		Assert.assertEquals(morto, pontuacaoMorto.getJogador());
		Assert.assertEquals(0, pontuacaoMorto.getArmas().size());
	}

	@Test
	public void testAdicionarCincoMortesAbaixoUmMinuto() {
		EventoMB servico = new EventoMB();
		Jogador matador = new Jogador("Diego");
		Jogador morto = new Jogador("Jonas");
		Arma arma = new Arma("Glock");
		Partida partida = new Partida(new Date(), "Partida3");

		Morte morte = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte);
		Morte morte2 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte2);
		Morte morte3 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte3);
		Morte morte4 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte4);
		Morte morte5 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte5);

		Assert.assertTrue(partida.getJogadorMatouMaisDeCincoVezesUmMinuto().contains(matador));
	}

	@Test
	public void testAdicionarCincoMortesAcimaUmMinuto() {
		EventoMB servico = new EventoMB();
		Jogador matador = new Jogador("Diego");
		Jogador morto = new Jogador("Jonas");
		Arma arma = new Arma("Glock");
		Partida partida = new Partida(new Date(), "Partida4");

		Morte morte = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte);
		Morte morte2 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte2);
		Morte morte3 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte3);
		Morte morte4 = new Morte(matador, morto, arma, new Date());
		servico.adicionarMorte(partida, morte4);
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MINUTE, 1);
		Morte morte5 = new Morte(matador, morto, arma, c.getTime());
		servico.adicionarMorte(partida, morte5);

		Assert.assertFalse(partida.getJogadorMatouMaisDeCincoVezesUmMinuto().contains(matador));
	}

}
