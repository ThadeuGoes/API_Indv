package br.com.api.trabalhoIndividual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.dto.RoleDTO;
import br.com.api.trabalhoIndividual.entities.Role;
import br.com.api.trabalhoIndividual.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	RoleService roleService;

	@PostMapping
	public ResponseEntity<Role> save(@RequestBody RoleDTO role) {
		Role newRole = roleService.save(role);
		if (newRole != null)
			return new ResponseEntity<>(newRole, HttpStatus.CREATED);
		else
			return new ResponseEntity<>(newRole, HttpStatus.BAD_REQUEST);
	}

}