package com.tribal.demo.controller;

import com.tribal.demo.Domain.JokeDomain;
import com.tribal.demo.service.JokeService;
import com.tribal.demo.utils.MensajeRespuesta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/jokes")
public class JokesController {

    private final JokeService jokeService;

    @GetMapping
    public ResponseEntity<?> getJokes(){
        try{
            List<JokeDomain> jokes = jokeService.getAllJokes();
            return new ResponseEntity<>( MensajeRespuesta.builder()
                    .mensaje("Succesful")
                    .data(jokes)
                    .build()
                    , HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>( MensajeRespuesta.builder()
                    .mensaje("Error: "+ex.getMessage())
                    .data(null)
                    .build()
                    , HttpStatus.NO_CONTENT);
        }

    }
}
