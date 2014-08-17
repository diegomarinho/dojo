package com.diego.dojo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.diego.dojo.entity.Arma;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Morte;
import com.diego.dojo.entity.Partida;

public class TestCalendar {

	@Test
	public void testValidarDatasSucesso() {
		
		Jogador matador = new Jogador("Fulano");
		Jogador morto = new Jogador("Sicrano");
		Arma arma = new Arma("M16");
		Morte morte1 = new Morte(matador, morto, arma, new Date());
		Morte morte2 = new Morte(morto, matador, arma, new Date());
		Partida partida = new Partida(new Date(), "11348965");
		partida.getMortes().add(morte1);
		partida.getMortes().add(morte2);
		
		CalendarUtil calendar = new CalendarUtil();
		Assert.assertTrue("Datas da partida NÃO estão entre 1 minuto.", calendar.exibirPartidaEmUmMinuto(partida.getMortes().get(0).getData(), partida.getMortes().get(1).getData()));
	}
	
	@Test
	public void testValidarDatasFalha() {
		
		Jogador matador = new Jogador("Fulano");
		Jogador morto = new Jogador("Sicrano");
		Arma arma = new Arma("M16");
		Morte morte1 = new Morte(matador, morto, arma, new Date());
		@SuppressWarnings("deprecation")
		Morte morte2 = new Morte(morto, matador, arma, new Date(new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
		Partida partida = new Partida(new Date(), "11348965");
		partida.getMortes().add(morte1);
		partida.getMortes().add(morte2);
		
		CalendarUtil calendar = new CalendarUtil();
		Assert.assertFalse("Datas da partida estão entre 1 minuto.", calendar.exibirPartidaEmUmMinuto(partida.getMortes().get(0).getData(), partida.getMortes().get(1).getData()));
	}
}
