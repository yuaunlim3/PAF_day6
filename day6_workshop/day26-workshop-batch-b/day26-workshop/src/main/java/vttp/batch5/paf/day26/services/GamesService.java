package vttp.batch5.paf.day26.services;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.batch5.paf.day26.Utils.Constants;
import vttp.batch5.paf.day26.repositories.CommentsRepository;
import vttp.batch5.paf.day26.repositories.GamesRepository;

@Service
public class GamesService {
    @Autowired private GamesRepository gamesRepository;
    @Autowired private CommentsRepository commentsRepository;

    public Document getResult(String name){
        Document gameDetail = gamesRepository.getGameByName(name);
        int id = gameDetail.getInteger(Constants.F_GAMEID);

        List<Document> data = commentsRepository.getComments(id);
        List<Document> comments = new LinkedList<>();
        for(Document document: data){
            Document comment = new Document();
            comment.append("user", document.getString(Constants.F_USER));
            comment.append("rating",document.getInteger(Constants.F_RATINGS));
            comment.append("text",document.getString(Constants.F_TEXT));
            comments.add(comment);
        }
		gameDetail.append("comments", comments);

        return gameDetail;
    }

    public Boolean isExist(String name){
        return gamesRepository.check(name);
    }
}
