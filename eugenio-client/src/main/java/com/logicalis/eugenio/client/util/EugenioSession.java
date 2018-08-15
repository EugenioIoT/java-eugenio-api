package com.logicalis.eugenio.client.util;

import java.time.LocalDateTime;

import com.logicalis.eugenio.client.api.auth.dtos.EugenioLoginResponseDTO;

/**
 * Info class of eugenio login.
 */
public class EugenioSession {

	private EugenioLoginResponseDTO loggedInfo;
	private LocalDateTime loggedAt;

	/**
	 * Set info of login.
	 * 
	 * @param loggedInfo.
	 */
	public void login(EugenioLoginResponseDTO loggedInfo) {
		this.loggedInfo = loggedInfo;
		this.loggedAt = LocalDateTime.now();
	}

	/**
	 * Verify if the user is logged.
	 * 
	 * @return logged.
	 */
	public Boolean isLogged() {
		return this.loggedInfo != null && this.loggedAt != null;
	}

	/**
	 * Verify if the logged token's expired.
	 * 
	 * @return expired.
	 */
	public Boolean isExpired() {
		return !isLogged() || LocalDateTime.now().minusMinutes(loggedInfo.getExpiresIn()).isAfter(this.loggedAt);
	}

	/**
	 * Get logged user.
	 * @return {@link EugenioLoginResponseDTO}.
	 */
	public EugenioLoginResponseDTO getLoggedInfo() {
		return loggedInfo;
	}

}
