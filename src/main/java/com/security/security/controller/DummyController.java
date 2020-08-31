package com.security.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dummy")
public class DummyController {

  @PreAuthorize("hasRole('permission1')")
  @GetMapping("/endpoint")
  public ResponseEntity<String> getEndpoint() {
    return new ResponseEntity<String>("hello",HttpStatus.OK);
  }
}
