//CHECKSTYLE:OFF - Pela arquitetura o único delegate do sistema.
package com.diego.dojo.business;

import java.util.Date;

import com.diego.dojo.entity.Arma;
import com.diego.dojo.entity.CategoryEnum;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Morte;

/**
 * @author Diego
 * @since 15/08/2014
 */
public class MorteMB {	// NOPMD - ExcessiveClassLength - Delegate.

	/**
	 * Responsavel por carregar os dados da partida.
	 * 
	 * @param dataMorte
	 * @param linhaArquivo
	 * @return
	 */
	public Morte carregarDadosPartida(Date dataMorte, String linhaArquivo) {
		String nomeMatador = linhaArquivo.substring(0, linhaArquivo.indexOf(" "));
		linhaArquivo = linhaArquivo.substring(nomeMatador.length());
		linhaArquivo = linhaArquivo.substring(CategoryEnum.KILLED.getDescricao().length());
		
		String nomeMorto = linhaArquivo.substring(0, linhaArquivo.indexOf(" "));
		linhaArquivo = linhaArquivo.substring(nomeMorto.length());
		linhaArquivo = linhaArquivo.substring(CategoryEnum.USING.getDescricao().length());
		
		String nomeArma = linhaArquivo;

		return new Morte(new Jogador(nomeMatador), new Jogador(nomeMorto), new Arma(nomeArma), dataMorte);
	}

}
