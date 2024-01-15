package hexlet.code.repository;

import hexlet.code.model.Url;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

public class UrlRepository extends BaseRepository {

    public static Optional<Url> findByUrl(String url) throws SQLException {
        var sql = "SELECT * FROM urls WHERE name = ?";
        try ( var conn = dataSource.getConnection();
              var prepStatement = conn.prepareStatement(sql)) {
            prepStatement.setString(1, url);
            var result = prepStatement.executeQuery();
            if (result.next()) {
                var id = result.getLong("id");
                var name = result.getString("name");
                var createdAt = Instant.parse(result.getString("createdAt"));
                var newUrl = new Url(name, createdAt);
                newUrl.setId(id);
                return Optional.of(newUrl);
            }
            return Optional.empty();
        }
    }

    public static void save(Url url) throws SQLException {
        var sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepStatement.setString(1, url.getName());
            prepStatement.setTimestamp(2, Timestamp.from(url.getCreatedAt()));
            prepStatement.executeUpdate();
            var generatedKeys = prepStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                url.setId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("DB have not returned an id after saving an entity");
            }
        }
    }
}
