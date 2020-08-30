package com.security.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.security.security.AuthHeaderValue;
import com.security.security.EntitlementsToken;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RequestFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
									FilterChain filterChain) throws ServletException, IOException {

		String authHeaderValue = "{\n" +
				"\"entitlements\": [\n" +
				"\t\"pmview\",\n" +
				"\t\"completesvc\"\n" +
				"]\n" +
				"}";
//		String authHeaderValue=request.getHeader("x-cat-entitlement");
//      Similarly Extract principal from auth token
		System.out.println("My filter");

		if(authHeaderValue!=null)
		{
			ObjectMapper objectMapper = new ObjectMapper();
			AuthHeaderValue authHeaderValue1 =
					objectMapper.readValue(authHeaderValue, AuthHeaderValue.class);
			if(SecurityContextHolder.getContext().getAuthentication()==null)
			{
				List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
				for (String entitlement: authHeaderValue1.getEntitlements())
				{

					simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + entitlement));
				}
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
						new UsernamePasswordAuthenticationToken(null/*can be taken from another token*/, null, simpleGrantedAuthorities);
				SecurityContextHolder.getContext()
						.setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);

	}
}
