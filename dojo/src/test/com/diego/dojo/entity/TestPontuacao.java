package com.diego.dojo.entity;

import org.junit.Assert;
import org.junit.Test;

import com.diego.dojo.entity.Arma;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Pontuacao;

public class TestPontuacao {

	@Test
	public void testNovaArma() {
		final Pontuacao pontuacao = new Pontuacao(new Jogador("David"));

		final Arma arma = new Arma("Arma");
		pontuacao.adicionarArma(arma);

		Assert.assertEquals(1, pontuacao.getArmas().get(arma).intValue());
	}

	@Test
	public void testArmaExistente() {
		final Pontuacao pontuacao = new Pontuacao(new Jogador("David"));

		final Arma arma = new Arma("Arma");
		pontuacao.adicionarArma(arma);
		pontuacao.adicionarArma(arma);

		Assert.assertEquals(2, pontuacao.getArmas().get(arma).intValue());
	}

	@Test
	public void testMultiplasArmas() {
		final Pontuacao pontuacao = new Pontuacao(new Jogador("David"));

		final Arma arma = new Arma("Arma");
		final Arma arma2 = new Arma("Arma2");
		final Arma arma3 = new Arma("Arma3");
		pontuacao.adicionarArma(arma);
		pontuacao.adicionarArma(arma);
		pontuacao.adicionarArma(arma2);
		pontuacao.adicionarArma(arma3);
		pontuacao.adicionarArma(arma);
		pontuacao.adicionarArma(arma);
		pontuacao.adicionarArma(arma3);
		pontuacao.adicionarArma(arma2);
		pontuacao.adicionarArma(arma3);

		Assert.assertEquals(4, pontuacao.getArmas().get(arma).intValue());
		Assert.assertEquals(2, pontuacao.getArmas().get(arma2).intValue());
		Assert.assertEquals(3, pontuacao.getArmas().get(arma3).intValue());
	}
}
