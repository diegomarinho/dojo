package com.diego.dojo.entity.domain;

import java.util.List;

public interface IBSUsuario {

	public String getEmail();

	public Long getId();

	public String getNome();

	public List<String> getPermissoes();

}
