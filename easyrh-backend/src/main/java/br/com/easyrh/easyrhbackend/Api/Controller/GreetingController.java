package br.com.easyrh.easyrhbackend.Api.Controller;

//#region IMPORTS
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.easyrh.easyrhbackend.Communication.Response.GreetingResponse.GreetingResponseJson;
//#endregion

@RestController
public class GreetingController {
    
    private static final String template = "Hello %s!";
    private static AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")//O requestMapping mapea a requisção para esse endpoint específico
    public GreetingResponseJson Greeting(@RequestParam(value = "name", defaultValue = "world") String name) //RequestParam é um parametro de query que será passado na uri da requisição (opcionais)
    {
        return new GreetingResponseJson(String.format(template, name), counter.incrementAndGet());
    }
}
