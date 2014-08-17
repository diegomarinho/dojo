package com.diego.dojo.business;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.diego.dojo.entity.Morte;

public class TestMorteMB {

	@Test
	public void testMorteJogador() {
		final MorteMB servico = new MorteMB();
		final Date data = new Date();
		final Morte morte = servico.carregarDadosPartida(data, "Roman killed Nick using M16");

		Assert.assertEquals(data, morte.getData());
		Assert.assertEquals("Roman", morte.getMatador().getNome());
		Assert.assertEquals("Nick", morte.getMorto().getNome());
		Assert.assertEquals("M16", morte.getArma().getNome());
	}

}
