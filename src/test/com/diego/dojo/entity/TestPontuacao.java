package com.diego.dojo.entity;

import org.junit.Assert;
import org.junit.Test;

import com.diego.dojo.entity.Arma;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Pontuacao;

/**
 * @author Diego
 * @since 18/08/2014
 */
public class TestPontuacao {

	@Test
	public void testArmaCombinada() {
		Pontuacao pontuacao = new Pontuacao(new Jogador("Diego"));
		Arma arma = new Arma("Glock");
		pontuacao.adicionarArma(arma);
		pontuacao.adicionarArma(arma);
		Assert.assertEquals(2, pontuacao.getArmas().get(arma).intValue());
	}
	
	@Test
	public void testArmaSucesso() {
		Pontuacao pontuacao = new Pontuacao(new Jogador("Diego"));
		Arma arma = new Arma("Glock");
		pontuacao.adicionarArma(arma);
		Assert.assertEquals(1, pontuacao.getArmas().get(arma).intValue());
	}

	@Test
	public void testArmaListagem() {
		Pontuacao pontuacao = new Pontuacao(new Jogador("Diego"));
		Arma arma1 = new Arma("M16");
		Arma arma2 = new Arma("Glock");
		Arma arma3 = new Arma("AWP");
		pontuacao.adicionarArma(arma1);
		pontuacao.adicionarArma(arma1);
		pontuacao.adicionarArma(arma2);
		pontuacao.adicionarArma(arma3);
		pontuacao.adicionarArma(arma1);
		pontuacao.adicionarArma(arma1);
		pontuacao.adicionarArma(arma3);
		pontuacao.adicionarArma(arma2);
		pontuacao.adicionarArma(arma3);
		Assert.assertEquals(4, pontuacao.getArmas().get(arma1).intValue());
		Assert.assertEquals(2, pontuacao.getArmas().get(arma2).intValue());
		Assert.assertEquals(3, pontuacao.getArmas().get(arma3).intValue());
	}
}
