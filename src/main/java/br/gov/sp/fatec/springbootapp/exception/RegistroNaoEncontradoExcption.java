package br.gov.sp.fatec.springbootapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoExcption extends RuntimeException{
    
    public RegistroNaoEncontradoExcption(){
        super();
    }

    public RegistroNaoEncontradoExcption(String message){
        super(message);
    }

    public RegistroNaoEncontradoExcption(Throwable cause){
        super(cause);
    }

    public RegistroNaoEncontradoExcption(String message, Throwable cause){
        super(message, cause);
    }

}
