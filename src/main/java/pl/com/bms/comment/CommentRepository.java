package pl.com.bms.comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, String> {
    List<Comment> findByIdeaIDOrderByDatePostedDesc(String ideaID);
}
