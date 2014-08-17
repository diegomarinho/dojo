package com.diego.dojo;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.diego.dojo.business.EventoMB;
import com.diego.dojo.entity.Arma;
import com.diego.dojo.entity.CategoryEnum;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Morte;
import com.diego.dojo.entity.Partida;
import com.diego.dojo.entity.Pontuacao;

public class TestPainelResultado {

	@Test
	public void testExibirArmaPreferidaSucesso() {
		PainelResultado painelResultado = new PainelResultado();
		
		Pontuacao vencedor = new Pontuacao(new Jogador("Diego"));
		Arma armaPreferida = new Arma("M16");
		vencedor.adicionarArma(armaPreferida);
		vencedor.adicionarArma(armaPreferida);
		String armaPreferida2 = painelResultado.exibirArmaPreferida(vencedor, armaPreferida);
		
		Assert.assertEquals("Arma diferente." , CategoryEnum.ARMAPREFERIDA.getDescricao() + armaPreferida.getNome(), armaPreferida2);
	}
	
	@Test
	public void testExibirArmaPreferidaObjetoDiferente() {
		PainelResultado painelResultado = new PainelResultado();
		
		Pontuacao vencedor = new Pontuacao(new Jogador("Diego"));
		Arma armaPreferida = new Arma("M16");
		vencedor.adicionarArma(armaPreferida);
		vencedor.adicionarArma(armaPreferida);
		String armaPreferida2 = painelResultado.exibirArmaPreferida(vencedor, new Arma("UZZI"));
		
		Assert.assertNotSame("Arma identicas." , CategoryEnum.ARMAPREFERIDA.getDescricao() + armaPreferida.getNome(), armaPreferida2);
	}
	
	@Test
	public void testValidarPainelResultadoAwardJogadorSicranoStreakJogadorFulano() {
		Jogador matador = new Jogador("Fulano");
		Jogador morto = new Jogador("Sicrano");
		Arma arma = new Arma("M16");
		Morte morte = new Morte(matador, morto, arma, new Date());
		Morte morte2 = new Morte(morto, matador, arma, new Date());

		Partida partida = new Partida(new Date(), "11348965");
		partida.setFim(new Date());
		partida.getJogadorMatouMaisDeCincoVezesUmMinuto().add(morte2.getMatador());
		
		EventoMB servico = new EventoMB();
		servico.adicionarMorte(partida, morte2);
		servico.adicionarMorte(partida, morte2);
		servico.adicionarMorte(partida, morte2);
		servico.adicionarMorte(partida, morte);
		servico.adicionarMorte(partida, morte);
		

		PainelResultado painelResultado = new PainelResultado();
		painelResultado.exibirPainelResultado(partida);
	}
	
	@Test
	public void testValidarPainelResultadoAwardStreakAwardJogadorFulano() {
		Jogador matador = new Jogador("Fulano");
		Jogador morto = new Jogador("Sicrano");
		Arma arma = new Arma("M16");
		Morte morte = new Morte(matador, morto, arma, new Date());
		
		Partida partida = new Partida(new Date(), "11348965");
		partida.setFim(new Date());
		partida.getJogadorMatouMaisDeCincoVezesUmMinuto().add(morte.getMatador());
		
		EventoMB servico = new EventoMB();
		servico.adicionarMorte(partida, morte);
		servico.adicionarMorte(partida, morte);
		servico.adicionarMorte(partida, morte);
		servico.adicionarMorte(partida, morte);
		servico.adicionarMorte(partida, morte);
		
		
		PainelResultado painelResultado = new PainelResultado();
		painelResultado.exibirPainelResultado(partida);
	}
}
