package br.com.carwash.rest;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import br.com.carwash.aplicacao.token.JwtToken;
import br.com.carwash.exception.NotLogedException;
import br.com.carwash.utils.StringUtil;

public class SuperRest {


	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	private String getBarrierTocken() {
		return request.getHeader("Authorization");
	}
	
	protected boolean isAtorizade() throws NotLogedException {
		String jwts = getBarrierTocken();
		if(Objects.isNull(jwts))
			throw new NotLogedException();
		JwtToken.getClaim(StringUtil.extractformatedToken(jwts));
		return !jwts.isEmpty();
	}
	protected Long getTokenId() {
		String jtwsf = StringUtil.extractformatedToken(getBarrierTocken());
		return (long) JwtToken.extractTokenId(jtwsf);
	}
}
