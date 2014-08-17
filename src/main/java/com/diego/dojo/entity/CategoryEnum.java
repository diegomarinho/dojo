package com.diego.dojo.entity;

import com.diego.dojo.entity.domain.BSEnum;

public enum CategoryEnum implements BSEnum {

	PADRAO_DATA(0L, "dd/MM/yyyy hh:mm:ss"),
	MORTO_POR_WORLD(1L, "morto por <WORLD>, morte desconsiderada."),
	SEPARADOR_LINHA_LOG(2L, " - "),
	INICIO_PARTIDA(3L, "New match "),
	FIM_PARTIDA(4L, "Match"),
	WORLD(5L, "<WORLD>"),
	KILLED(6L, " killed "),
	USING(7L, " using "),
	PARTIDA(8L, "PARTIDA........: "),
	DURACAO(9L, "DURA플O.......: "),
	JOGADOR(10L, "JOGADOR....: "),
	VENCEDOR(11L, "VENCEDOR.......: "),
	MORTES(12L, "MORTES.........: "),
	ASSASINATOS(13L, "ASSASINATOS....: "),
	BONUS(14L, "BONUS..........: "),
	ARMAPREFERIDA(15L, "ARMA PREFERIDA.: ");
	
	/**
	 * Recupera o Enum pelo valor do seu c처digo.
	 * 
	 * @param codigo O c처digo para recuperar o Enum.
	 * @return Enum de acordo com o c처digo informado.
	 */
	public static CategoryEnum getTipo(Long codigo) {
		
		CategoryEnum tipoRetorno = null;
		
		for (CategoryEnum tipo: CategoryEnum.values()) {
			if (tipo.getId().intValue() == codigo.intValue()) {
				tipoRetorno = tipo;
			}
		}
		
		return tipoRetorno;
	}

	private final String descricao;

	private final Long codigo;

	private CategoryEnum(final Long codigo, final String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public Long getId() {
		return this.codigo;
	}

}
