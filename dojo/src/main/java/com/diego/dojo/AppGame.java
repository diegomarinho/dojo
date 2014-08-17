package com.diego.dojo;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.diego.dojo.entity.Partida;

/**
 * Read file "logfile.log".
 * 
 * @author Diego
 * @since 15/08/2014
 */
public class AppGame {

	public static void main(String[] args) {
		
		Executor executor = new Executor();
		PainelResultado PainelResultado = new PainelResultado();
		
		try {
			
			List<Partida> partidas = executor.lerArquivoLog("logfile.log");

			for (Partida partida : partidas) {
				PainelResultado.exibirPainelResultado(partida);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
