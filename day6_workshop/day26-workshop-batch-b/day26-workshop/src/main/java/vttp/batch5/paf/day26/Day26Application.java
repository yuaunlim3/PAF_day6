package vttp.batch5.paf.day26;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import vttp.batch5.paf.day26.repositories.CommentsRepository;
import vttp.batch5.paf.day26.repositories.GamesRepository;

@SpringBootApplication
public class Day26Application {

	@Autowired
	private GamesRepository gamesRepository;
	@Autowired
	private CommentsRepository commentsRepository;

	public static void main(String[] args) {
		SpringApplication.run(Day26Application.class, args);
	}
}
