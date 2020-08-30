package com.security.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public class EntitlementsToken extends AbstractAuthenticationToken {
  private static final long serialVersionUID = 7032335279756013130L;

  String[] roles;
  private String jwtToken;

  public EntitlementsToken(
      Collection<? extends GrantedAuthority> authorities, String[] roles) {
    super(authorities);
    this.roles = roles;
  }

  public EntitlementsToken(Collection<? extends GrantedAuthority> authorities) {

    super(authorities);
    setAuthenticated(true);
  }

  @Override
  public Object getCredentials() {
    return jwtToken;
  }

  @Override
  public Object getPrincipal() {
    return null;
  }
}
