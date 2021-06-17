package br.com.carwash.aplicacao.token;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import br.com.carwash.dto.UsuarioAutenticado;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtToken {
	static String key = "TVohC1L5cM5Ca784WQwF1TnEYpx7P4O0FPOVvtZfh5tYL4s8wb/fFw1ewhZJJovq"
					+ "lLvrwTX7MgWry6eJy4NLJKDXR7rL9Q4uVXjUoEpHSn4UsjT4uiSDBOP9sahgSPga"
					+ "3+/DDJ9RRL5qOCquKTFaUpVQgs+k8vWsVhji/4mtHEQ=";

	static String audience = "mobil-app";

	public static String getStringTocken(UsuarioAutenticado user) {
		Instant now = Instant.now();
		byte[] segredo = Base64.getDecoder().decode(key);
		return Jwts.builder().setSubject("mobil-token")
				.setAudience(audience)
				.setIssuedAt(Date.from(now))
				.claim("token-id", user.getUserId())
				.setExpiration(Date.from(now.plus(15, ChronoUnit.MINUTES)))
				.signWith(Keys.hmacShaKeyFor(segredo)).compact();

	}

	public static Claims getClaim(String jwt) {
		byte[] segredo = Base64.getDecoder().decode(key);
		return Jwts.parser()
				.requireAudience(audience)
				.setAllowedClockSkewSeconds(60)
				.setSigningKey(Keys.hmacShaKeyFor(segredo))
				.parseClaimsJws(jwt).getBody();
	}

	public static long extractTokenId(String jtws) {
		Claims claims =getClaim(jtws);
		return claims.get("token-id", Long.class);
	}
}