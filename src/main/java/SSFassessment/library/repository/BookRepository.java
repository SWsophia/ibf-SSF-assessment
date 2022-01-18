package SSFassessment.library.repository;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Autowired
    private RedisTemplate<String, String> template;

    public void add(String workID, String bookDetailsString) {
        template.opsForValue().set(workID, bookDetailsString, Duration.ofMinutes(10));
    }

    public Optional<String> getBook(String workID) {
        return Optional.ofNullable(template.opsForValue().get(workID));
    }
}
