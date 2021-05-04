package backend.db.controller;

import backend.db.model.Log;
import backend.db.service.CalcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class Controller {

    Logger logger = Logger.getLogger(backend.db.controller.Controller.class.getName());

    @Autowired
    private CalcService service;

    @GetMapping("/")
    public ResponseEntity<List<Log>> getLogs() {
        logger.log(Level.INFO, "Get request");
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        return new ResponseEntity<>(service.getLogs(), responseHeaders, HttpStatus.CREATED);
    }

    @PostMapping("/")
    public ResponseEntity<String> calculate(@RequestBody String equation) {
        logger.log(Level.INFO, "Post request with content: "+ equation);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Access-Control-Allow-Origin", "*");
        String answer = Double.toString(service.calculate(equation));
        service.postLog(equation + " = " + answer);
        return new ResponseEntity<>(answer, responseHeaders, HttpStatus.CREATED);
    }
}
