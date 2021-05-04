package backend.db.service;

import backend.db.model.Log;
import backend.db.repo.CalcRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.objecthunter.exp4j.*;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class CalcService {

    Logger logger = Logger.getLogger(backend.db.service.CalcService.class.getName());

    @Autowired
    CalcRepo repo;

    public List<Log> getLogs() {
        logger.log(Level.INFO, "Returning logs!");
        Pageable pageable = PageRequest.of(0, 10);
        return repo.findByOrderByDateTimeDesc(pageable).toList();
    }

    public Log postLog(String text) {
        logger.log(Level.INFO, "Posting log!");
        return repo.save(new Log(text));
    }

    public double calculate(String equation) {
        logger.log(Level.INFO, "Logging input: " + equation);
        double answer = new ExpressionBuilder(equation).build().evaluate();
        logger.log(Level.INFO, "Logging output: " + answer);
        return answer;
    }
}
