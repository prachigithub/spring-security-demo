/*
 *  Copyright (c) 2019 Caterpillar Inc. All Rights Reserved.
 *
 *  This work contains Caterpillar Inc.'s unpublished
 *  proprietary information which may constitute a trade secret
 *  and/or be confidential. This work may be used only for the
 *  purposes for which it was provided, and may not be copied
 *  or disclosed to others. Copyright notice is precautionary
 *  only, and does not imply publication.
 */

package com.security.security.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Occurrence service endpoints provide occurrences for assets which are on-boarded and being
 * tracked for maintenance activities.
 */
@RestController
@RequestMapping("/v1/dummy")
public class DummyController {

  @PreAuthorize("hasRole('completesvc')")
  @GetMapping("/endpoint")
  public ResponseEntity<String> getEndpoint() {
    return new ResponseEntity<String>("hello",HttpStatus.OK);
  }
}
