package com.diego.dojo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.diego.dojo.business.MorteMB;
import com.diego.dojo.business.EventoMB;
import com.diego.dojo.entity.CategoryEnum;
import com.diego.dojo.entity.Morte;
import com.diego.dojo.entity.Partida;

/**
 * @author Diego
 * @since 15/08/2014
 */
public class Executor {

	private final SimpleDateFormat SDF = new SimpleDateFormat(CategoryEnum.PADRAO_DATA.getDescricao());
	
	private List<Partida> listaPartidas;
	private Partida partidaAtual;
	private MorteMB morteMB;
	private EventoMB eventoMB;

	public Executor() {
		this.morteMB = new MorteMB();
		this.eventoMB = new EventoMB();
	}

	/**
	 * Le o arquivo de log e monta lista de partidas.
	 * 
	 * @param caminho
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	public List<Partida> lerArquivoLog(String caminho) throws IOException, ParseException {

		listaPartidas = new ArrayList<Partida>();

		File file = new File(caminho);
		
		if (!file.exists()) {  
            throw new IOException("File is not found");  
        } 
		
		InputStreamReader stream = new InputStreamReader(new FileInputStream(file));
		BufferedReader reader = new BufferedReader(stream);
		
		while (reader.ready()) {
			String linha = reader.readLine();
			carregarLinha(linha);
		}
		
		reader.close();

		return listaPartidas;
	}

	/**
	 * Carrega cada linha de log separadamente.
	 * 
	 * @param linhaLeituraArquivo
	 * @throws ParseException
	 */
	protected void carregarLinha(String linhaLeituraArquivo) throws ParseException {

		if ((linhaLeituraArquivo == null) || linhaLeituraArquivo.isEmpty()) {
			return;
		}

		int separadorDataLog = linhaLeituraArquivo.indexOf(CategoryEnum.SEPARADOR_LINHA_LOG.getDescricao());
		String data = linhaLeituraArquivo.substring(0, separadorDataLog);
		Date dataLog = SDF.parse(data);
		String linhaArquivo = linhaLeituraArquivo.substring(separadorDataLog + 3);

		if (linhaArquivo.indexOf(CategoryEnum.INICIO_PARTIDA.getDescricao()) != -1) {
			// inicio da partida
			carregarInicioPartida(dataLog, linhaArquivo);
		} else if (linhaArquivo.indexOf(CategoryEnum.FIM_PARTIDA.getDescricao()) != -1) {
			// da partida
			carregarFimPartida(dataLog, linhaArquivo);
		} else if (linhaArquivo.indexOf(CategoryEnum.WORLD.getDescricao()) != -1) {
			// morto por <WORLD> desconsiderar
		} else {
			// morto por um jogador
			carregarMorte(dataLog, linhaArquivo);
		}
	}

	private void carregarMorte(Date dataLog, String linhaArquivo) {
		Morte morte = morteMB.criarMorte(dataLog, linhaArquivo);
		eventoMB.adicionarMorte(partidaAtual, morte);
	}

	private void carregarFimPartida(Date dataLog, String log) {
		partidaAtual.setFim(dataLog);
	}

	private void carregarInicioPartida(Date dataLog, String log) {
		partidaAtual = eventoMB.criarPartida(dataLog, log);
		listaPartidas.add(partidaAtual);
	}

	protected List<Partida> getPartidas() {
		return listaPartidas;
	}

	protected void setPartidas(List<Partida> partidas) {
		this.listaPartidas = partidas;
	}

	protected Partida getPartidaAtual() {
		return partidaAtual;
	}

	protected void setPartidaAtual(Partida partidaAtual) {
		this.partidaAtual = partidaAtual;
	}

}
