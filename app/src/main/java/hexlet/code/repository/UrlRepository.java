package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.SQLException;
import java.time.Instant;
import java.util.Optional;

public class UrlRepository extends BaseRepository {

    public static Optional<Url> findByUrl(String url) throws SQLException {
        var sql = "SELECT * FROM urls WHERE name = ?";
        try ( var conn = dataSource.getConnection();
              var prepStatement = conn.prepareStatement(sql)) {
            prepStatement.setString(1, url);
            var result = prepStatement.executeQuery();
            if (result.first()) {
                var id = result.getLong("id");
                var name = result.getString("name");
                var createdAt = Instant.parse(result.getString("createdAt"));
                var newUrl = new Url(id, name, createdAt);
                return Optional.of(newUrl);
            }
            return Optional.empty();
        }
    }
}
