package PAF.day6_example;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import PAF.day6_example.Repository.SeriesRepository;

@SpringBootApplication
public class Day6ExampleApplication implements CommandLineRunner {

	@Autowired
	private SeriesRepository seriesRepository;
	public static void main(String[] args) {
		SpringApplication.run(Day6ExampleApplication.class, args);
	}

	@Override
	public void run(String... args){
		/*
		List<Document> results = seriesRepository.findSeriesByNameNRatings("ar");
		System.out.printf(">>>Result size: %d\n",results.size());
		for(Document document:results){
			System.out.printf(">>>>%s\n\n",document.toJson());
		}
			*/

		List<String> results = seriesRepository.getAllGenresByCountry("Canada");
		for(String series: results){
			System.out.println(series);
		}
	}

}
