package com.globo.challenge.controller;

import com.globo.challenge.dto.UserDto;
import com.globo.challenge.model.User;
import com.globo.challenge.repository.UserRepository;
import com.globo.challenge.service.UserService;
import com.globo.challenge.util.CpfCnpjUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @ApiOperation(value = "Create new User")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid UserDto userDto) {
        try {
            service.create(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @ApiOperation(value = "Get User by Document")
    @GetMapping("/{document}")
    public ResponseEntity<?> getUser(@RequestParam("document") @NotNull String document) {
        try {
            if (CpfCnpjUtil.isCpf(document) && CpfCnpjUtil.isValidCPF(document)) {
                document = CpfCnpjUtil.formatCpf(document);
            } else if (CpfCnpjUtil.isCnpj(document) && CpfCnpjUtil.isValidCNPJ(document)) {
                document = CpfCnpjUtil.formatCnpj(document);
            } else {
                return ResponseEntity.badRequest().body("Invalid document, please try again!");
            }

            User user = repository.findByDocument(document);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found, please try again!");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

}
