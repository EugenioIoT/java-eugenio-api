package com.logicalis.eugenio.client.util;

import com.logicalis.eugenio.client.api.auth.dtos.EugenioLoginDTO;
import com.logicalis.eugenio.client.valueobject.InfoLoginDTO;

public class LoginDtoToEugenioLoginDto {

	public static EugenioLoginDTO convert(InfoLoginDTO loginDTO) {
		EugenioLoginDTO eugenioLoginDTO = new EugenioLoginDTO();
		eugenioLoginDTO.setUsername(loginDTO.getUsername());
		eugenioLoginDTO.setPassword(loginDTO.getPassword());
		eugenioLoginDTO.setTenant(loginDTO.getTenant());
		return eugenioLoginDTO;
	}

}
