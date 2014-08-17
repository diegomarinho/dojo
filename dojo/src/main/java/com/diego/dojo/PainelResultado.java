package com.diego.dojo;

import java.text.SimpleDateFormat;
import java.util.Set;

import com.diego.dojo.entity.Arma;
import com.diego.dojo.entity.CategoryEnum;
import com.diego.dojo.entity.Jogador;
import com.diego.dojo.entity.Partida;
import com.diego.dojo.entity.Pontuacao;

/**
 * Classe responsavel por mostrar o Painel de Resultado via log dos usuarios.
 * 
 * @author Diego
 * @since 15/08/2014
 */
public class PainelResultado {

	/**
	 * Imprime o Painel de Resultado da partida.
	 * 
	 * @param partida
	 */
	public void exibirPainelResultado(Partida partida) {

		System.out.println(CategoryEnum.PARTIDA.getDescricao() + partida.getNome());

		Jogador jogador = this.exibirAssassinatosJogador(partida.getPontuacoes());

		this.exibirPremio(partida, jogador);
		
		this.exibirVencedor(partida.getPontuacoes());

		System.out.println("========================================");
		System.out.println("========================================");
		System.out.println("========================================");
		System.out.println("");
		System.out.println("");
	}
	
	private void exibirPremio(Partida partida, Jogador jogadorComMaiorSequenciaDeAssassinatosSeguidos) {
		
		int numeroJogador = 1;
		
		for (Pontuacao pontuacao : partida.getPontuacoes()) {
			
			StringBuffer sb = new StringBuffer();
			
			sb.append("\n" + numeroJogador + "".concat(CategoryEnum.SEPARADOR_LINHA_LOG.getDescricao() + CategoryEnum.JOGADOR.getDescricao()) + pontuacao.getJogador().getNome());
			sb.append("\n" + CategoryEnum.ASSASINATOS.getDescricao() + pontuacao.getMatou());
			sb.append("\n" + CategoryEnum.MORTES.getDescricao() + pontuacao.getMorreu());
			sb.append("\n" + CategoryEnum.BONUS.getDescricao());

			if (pontuacao.getMorreu() == 0) {
				sb.append("[AWARD]  - Perfeito! Não houve morte.");
			}

			if (pontuacao.getJogador().equals(jogadorComMaiorSequenciaDeAssassinatosSeguidos)) {
				sb.append("\n\t\t" + " [STREAK] - Perfeito! Maior sequencia de assassinatos");
			}

			if (partida.getJogadorMatouMaisDeCincoVezesUmMinuto().contains(pontuacao.getJogador())) {
				sb.append("\n\t\t" + " [AWARD]  - Perfeito! Assassinaou 5x. em 1 min.");
			}

			System.out.println(sb);
			numeroJogador++;
		}
	}

	private Jogador exibirAssassinatosJogador(Set<Pontuacao> pontuacoes) {

		int maiorNumeroDeAssassinatosSeguidos = 0;
		Jogador jogadorComQtdeDeAssassinatos = null;
		
		for (Pontuacao pontuacao : pontuacoes) {
			
			if (maiorNumeroDeAssassinatosSeguidos < pontuacao.getAssassinatosSeguidos()) {
				maiorNumeroDeAssassinatosSeguidos = pontuacao.getAssassinatosSeguidos();
				jogadorComQtdeDeAssassinatos = pontuacao.getJogador();
			}
		}

		return jogadorComQtdeDeAssassinatos;
	}

	private void exibirVencedor(Set<Pontuacao> pontuacoes) {
		
		Pontuacao vencedor = null;
		Arma armaPreferida = null;
		Integer qtdMortesArmaPreferida = 0;
		
		for (Pontuacao pontuacao : pontuacoes) {
			if ((vencedor == null) || (vencedor.getMatou() < pontuacao.getMatou())) {
				vencedor = pontuacao;
			}
		}

		for (Arma arma : vencedor.getArmas().keySet()) {
			if (qtdMortesArmaPreferida < vencedor.getArmas().get(arma).intValue()) {
				armaPreferida = arma;
				qtdMortesArmaPreferida = vencedor.getArmas().get(arma).intValue();
			}
		}
		
		System.out.println("\n" + CategoryEnum.VENCEDOR.getDescricao() + vencedor.getJogador().getNome());

		System.out.println(exibirArmaPreferida(vencedor, armaPreferida));
	}

	protected String exibirArmaPreferida(Pontuacao vencedor, Arma armaPreferida) {
		return CategoryEnum.ARMAPREFERIDA.getDescricao() + armaPreferida.getNome();
	}

}
