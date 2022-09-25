package com.api.cadastrodeimoveis.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.cadastrodeimoveis.models.ProprietarioModel;
import com.api.cadastrodeimoveis.services.ProprietarioService;
import com.api.cadastrodeimoveis.validate.ValidarProprietario;

@RequestMapping("/proprietario")
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RestController
public class ProprietarioController {

    final ProprietarioService proprietarioService;

    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarImovel(@RequestBody @Valid ValidarProprietario validarProprietario) {

        if (proprietarioService.jaExisteEndereco(validarProprietario.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflito: Este email já existe em nosso banco de dados.");
        }

        var proprietarioModel = new ProprietarioModel();

        BeanUtils.copyProperties(validarProprietario, proprietarioModel);

        proprietarioModel.setRegistradoEm(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(proprietarioService.save(proprietarioModel));
    }

    @GetMapping
    public ResponseEntity<Page<ProprietarioModel>> getAllImoveis(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(proprietarioService.findAll(pageable));
    }

    @GetMapping("/detalhes")
    public ResponseEntity<Object> getOneImovel(@RequestParam UUID id) {
        Optional<ProprietarioModel> proprietarioOptional = proprietarioService.findById(id);

        if (!proprietarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proprietário não foi encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(proprietarioOptional.get());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteimovel(@RequestParam UUID id) {
        Optional<ProprietarioModel> proprietarioOptional = proprietarioService.findById(id);

        if (!proprietarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proprietário não foi encontrado.");
        }

        proprietarioService.delete(proprietarioOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Proprietário deletado com sucessor.");
    }

    @PutMapping
    public ResponseEntity<Object> updateImovel(@RequestParam UUID id,
            @RequestBody @Valid ValidarProprietario validarProprietario) {

        Optional<ProprietarioModel> proprietarioOptional = proprietarioService.findById(id);

        if (!proprietarioOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Proprietário não foi encontrado.");
        }

        var proprietarioModel = new ProprietarioModel();

        BeanUtils.copyProperties(validarProprietario, proprietarioModel);

        proprietarioModel.setId(proprietarioOptional.get().getId());
        proprietarioModel.setRegistradoEm(proprietarioOptional.get().getRegistradoEm());

        return ResponseEntity.status(HttpStatus.OK).body(proprietarioService.save(proprietarioModel));

    }

}
