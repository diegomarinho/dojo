package com.diego.dojo.entity.domain;

import java.util.List;

/**
 * @author Diego
 * @since 16/08/2014
 */
public interface IBSUsuario {

	public String getEmail();

	public Long getId();

	public String getNome();

	public List<String> getPermissoes();

}
