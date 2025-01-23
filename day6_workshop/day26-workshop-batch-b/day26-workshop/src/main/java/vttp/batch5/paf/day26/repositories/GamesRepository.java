package vttp.batch5.paf.day26.repositories;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import vttp.batch5.paf.day26.Utils.Constants;

@Repository
public class GamesRepository {
    @Autowired
    private MongoTemplate template;

    // Seach games by name;
    /*
     * db.boardgames.find({
     * name:{
     * $regex:"a name",
     * $options:"i"
     * }
     * })
     */
    public Document getGameByName(String name) {
        Criteria criteria = Criteria.where(Constants.F_NAMES).regex(name, "i");

        Query query = Query.query(criteria)
                .with(Sort.by(Sort.Direction.ASC, Constants.F_RANKING))
                .limit(1);
        query.fields().exclude(Constants.F_ID, Constants.F_USER_RATED, Constants.F_YEAR);

        return template.find(query, Document.class, Constants.C_GAMES).getFirst();

    }

    /*
    db.boardgames.find
    ({ "name": {
        $regex:"a name",
        $options:"i"
    }})
     */
    public Boolean check(String name){
        Criteria criteria = Criteria.where(Constants.F_NAMES).regex(name, "i");
        Query query = Query.query(criteria);
        List<Document> result = template.find(query, Document.class, Constants.C_GAMES);
        if(result.size() < 1){
            return false;
        }
        return true;
    }
}
