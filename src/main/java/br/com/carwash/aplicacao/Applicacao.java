package br.com.carwash.aplicacao;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class Applicacao  extends ResourceConfig {
	public Applicacao() {
		packages("br.com.carwash.rest.*");
	}
}
