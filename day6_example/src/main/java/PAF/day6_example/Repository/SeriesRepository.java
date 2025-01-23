package PAF.day6_example.Repository;

import java.util.List;

import org.apache.tomcat.util.bcel.Const;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import PAF.day6_example.Utils.Constants;

@Repository
public class SeriesRepository {

    // Inject MongoTemplate
    @Autowired
    private MongoTemplate template;

    // Search movies names
    /*
     * db.series.find({
     * name:{
     * $regex:"a name",
     * $options:"i"
     * }
     * })
     */
    public List<Document> findSeriesByName(String name) {

        // Define Search criteria
        Criteria criteria = Criteria.where(Constants.F_NAMES)
                .regex(name, "i");

        // Create query based on the defined criteria
        Query query = Query.query(criteria);

        // Perform search
        return template.find(query, Document.class, Constants.C_SERIES);

    }

    // Movies with rating more than 8
    public List<Document> findSeriesByNameNRatings(String name) {

        // Define Search criteria
        Criteria criteriaByRating = Criteria.where(Constants.F_RATING)
                .gte(8);
        Criteria criteriaByName = Criteria.where(Constants.F_NAMES)
                .regex(name, "i")
                .andOperator(criteriaByRating);

        // Create query based on the defined criteria with sorting, limiting and skip
        Query query = Query.query(criteriaByName)
                .with(
                        Sort.by(Sort.Direction.DESC, Constants.F_RATING))
                .limit(4)
                .skip(5L);

        query.fields()
                .include(Constants.F_NAMES, Constants.F_RATING, Constants.F_GENRES, Constants.F_SUMMARY,
                        Constants.F_ORIGINAL_IMAGE)
                .exclude("_id");

        // Perform search
        return template.find(query, Document.class, Constants.C_SERIES);

    }

    /*
     db.series.distinct("genres")
     */
    public List<String> getAllGenres(){
        Query query = new Query();

        return template.findDistinct(query,Constants.F_GENRES, Constants.C_SERIES, String.class);

    }

    public List<String> getAllGenresByCountry(String country){
        Criteria criteria = Criteria.where(Constants.F_COUNTRY).regex(country, "i");
        Query query = Query.query(criteria);

        return template.findDistinct(query,Constants.F_GENRES, Constants.C_SERIES, String.class);

    }

}
