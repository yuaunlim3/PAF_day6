package vttp.batch5.paf.day26.controllers;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp.batch5.paf.day26.services.GamesService;

@Controller
@RequestMapping({"/","index.html"})
public class GamesController {
    @Autowired private GamesService gamesService;

    @GetMapping("/api/search")
    public ResponseEntity<String> start(@RequestParam String q){
        if(!gamesService.isExist(q)){
            Document result = new Document();
            result.append("message", "An error message");
            return ResponseEntity.status(404).body(result.toJson());
        }
        Document result = gamesService.getResult(q);
        return ResponseEntity.ok(result.toJson());
    }
}
