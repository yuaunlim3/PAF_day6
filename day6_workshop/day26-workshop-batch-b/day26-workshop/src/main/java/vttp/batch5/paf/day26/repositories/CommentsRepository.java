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
public class CommentsRepository {
    @Autowired private MongoTemplate template;

    /*
    db.comments.find({gid : 1})
        .sort({rating:-1})
        .limit(5)
        .projection({
            _id:0,
            user:1,
            rating:1,
            c_text:1
        })
     */
    public List<Document> getComments(int id){
        Criteria criteria = Criteria.where(Constants.F_GAMEID).is(id);
        Query query = Query.query(criteria)
                        .with(Sort.by(Sort.Direction.DESC,Constants.F_RATINGS))
                        .limit(5);
        query.fields()
                .include(Constants.F_USER,Constants.F_RATINGS,Constants.F_TEXT)
                .exclude(Constants.F_ID);

        return template.find(query, Document.class,Constants.C_COMMENTS );
    }
}
