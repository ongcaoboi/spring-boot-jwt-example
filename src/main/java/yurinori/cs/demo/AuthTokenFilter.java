package yurinori.cs.demo;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import yurinori.cs.demo.model.Account;
import yurinori.cs.demo.services.AccountService;
import yurinori.cs.demo.services.JwtService;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	private final static Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Value("${yurinori.app.tokenHeader}")
	private String TOKEN_HEADER = "Authorization";

	@Autowired
	private JwtService jwtService = new JwtService();
	
	@Autowired
	private AccountService accountService = new AccountService();

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			System.out.println("__________"+ request.getAuthType());
			String jwt = jwtService.getJwtFromCookies(request);
			if (jwt != null && jwtService.validateToken(jwt)){
				String useName = jwtService.getUserNameFromToken(jwt);
				Account user = accountService.getAccountByUserName(useName);
				
				if (user != null) {
					UsernamePasswordAuthenticationToken authentication =
							new UsernamePasswordAuthenticationToken(user, null, null);

					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} catch (Exception e) {
			logger.error("Cannot set user authentication: {}", e);
		}
		filterChain.doFilter(request, response);
	}
	
	
}
