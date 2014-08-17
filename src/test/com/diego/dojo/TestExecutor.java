package com.diego.dojo;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.diego.dojo.entity.CategoryEnum;
import com.diego.dojo.entity.Morte;
import com.diego.dojo.entity.Partida;

public class TestExecutor {

	private static final SimpleDateFormat SDF = new SimpleDateFormat(CategoryEnum.PADRAO_DATA.getDescricao());

	@Test(expected = IndexOutOfBoundsException.class)
	public void testLinhaLogInvalida() throws ParseException {
		new Executor().carregarLinha("linha invalida");
	}

	@Test
	public void testLinhaVazia() throws ParseException {
		final Executor executor = new Executor();
		executor.carregarLinha("");
		Assert.assertNull(executor.getPartidaAtual());
	}

	@Test
	public void testLinhaNula() throws ParseException {
		final Executor executor = new Executor();
		executor.carregarLinha(null);
		Assert.assertNull(executor.getPartidaAtual());
	}

	@Test(expected = ParseException.class)
	public void testDataInvalida() throws ParseException {
		final Executor executor = new Executor();
		executor.carregarLinha("23/04/2013 - teste");
		Assert.assertNull(executor.getPartidaAtual());
	}

	@Test(expected = ParseException.class)
	public void testDataInvalida2() throws ParseException {
		final Executor executor = new Executor();
		executor.carregarLinha("23/04/ano 15:34:22 - teste");
		Assert.assertNull(executor.getPartidaAtual());
	}

	@Test
	public void testInicioPartida() throws ParseException {
		final Executor executor = new Executor();
		executor.setPartidas(new ArrayList<Partida>());
		executor.carregarLinha("23/04/2013 15:34:22 - New match 11348965 has started");
		Assert.assertNotNull(executor.getPartidaAtual());

		Assert.assertEquals("11348965", executor.getPartidaAtual().getNome());
		Assert.assertEquals(SDF.parseObject("23/04/2013 15:34:22"), executor.getPartidaAtual().getInicio());
	}

	@Test
	public void testMorte() throws ParseException {
		final Executor executor = new Executor();
		executor.setPartidaAtual(new Partida(new Date(), "11348965 has started"));

		executor.carregarLinha("23/04/2013 15:36:04 - Roman killed Nick using M16");
		Assert.assertNotNull(executor.getPartidaAtual());

		Assert.assertEquals(1, executor.getPartidaAtual().getMortes().size());
		final Morte morte = executor.getPartidaAtual().getMortes().get(0);
		Assert.assertEquals("Roman", morte.getMatador().getNome());
		Assert.assertEquals("Nick", morte.getMorto().getNome());
		Assert.assertEquals("M16", morte.getArma().getNome());
		Assert.assertEquals(SDF.parseObject("23/04/2013 15:36:04"), morte.getData());
	}
	
	@Test(expected = IOException.class)
	public void testArquivoInvalido() throws IOException, ParseException {
		new Executor().lerArquivoLog("FileIsNotFound.txt");
	}

	@Test
	public void testMortePorWorld() throws ParseException {
		final Executor executor = new Executor();
		executor.carregarLinha("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
		Assert.assertNull(executor.getPartidaAtual());
		Assert.assertNull(executor.getPartidas());
	}

	@Test
	public void testFimPartida() throws ParseException {
		final Executor executor = new Executor();
		executor.setPartidaAtual(new Partida(new Date(), "11348965 has started"));
		executor.carregarLinha("23/04/2013 15:39:22 - Match 11348965 has ended");
		Assert.assertEquals(SDF.parseObject("23/04/2013 15:39:22"), executor.getPartidaAtual().getFim());
	}
}
