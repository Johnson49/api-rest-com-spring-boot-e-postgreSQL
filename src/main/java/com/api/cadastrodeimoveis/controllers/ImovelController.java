package com.api.cadastrodeimoveis.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
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

import com.api.cadastrodeimoveis.models.ImovelModel;
import com.api.cadastrodeimoveis.services.ImovelService;
import com.api.cadastrodeimoveis.validate.ValidarImovel;





@RequestMapping("/imoveis")
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RestController
public class ImovelController {

    final ImovelService imovelService;

    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @PostMapping
    public ResponseEntity<Object> SalvarImovel(@RequestBody @Valid ValidarImovel validarImovel) {

        if (imovelService.jaExisteEndereco(validarImovel.getNumero())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Conflito: Este número já existe em nosso banco de dados.");
        }

        var imovelModel = new ImovelModel();

        BeanUtils.copyProperties(validarImovel, imovelModel);

        imovelModel.setRegistradoEm(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(imovelService.save(imovelModel));
    }

    @GetMapping
    public ResponseEntity<Page<ImovelModel>> getAllImoveis(
        @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable ) {
        return ResponseEntity.status(HttpStatus.OK).body(imovelService.findAll(pageable));
    }

    @GetMapping("/detalhes")
    public ResponseEntity<Object> getOneImovel(@RequestParam UUID id) {
        Optional<ImovelModel> imovelOptional = imovelService.findById(id);

        if (!imovelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imovel não foi encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(imovelOptional.get());
    }

    @DeleteMapping
    public ResponseEntity<Object> deleteimovel(@RequestParam UUID id) {
        Optional<ImovelModel> imovelOptional = imovelService.findById(id);

        if (!imovelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imovel não foi encontrado.");
        }

        imovelService.delete(imovelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Imovel deletado com sucessor.");
    }

    @PutMapping
    public ResponseEntity<Object> updateImovel(@RequestParam UUID id,
                                        @RequestBody @Valid ValidarImovel validarImovel) {

        Optional<ImovelModel> imovelOptional = imovelService.findById(id);

        if (!imovelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imovel não foi encontrado.");
        }

        var imovelModel = new ImovelModel();

        BeanUtils.copyProperties(validarImovel, imovelModel);

        imovelModel.setId(imovelOptional.get().getId());
        imovelModel.setRegistradoEm(imovelOptional.get().getRegistradoEm());

        return ResponseEntity.status(HttpStatus.OK).body(imovelService.save(imovelModel));



    }
}