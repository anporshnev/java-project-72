package hexlet.code.repository;

import hexlet.code.model.Url;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
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
                var createdAt = result.getTimestamp("created_at");
                var newUrl = new Url(name, createdAt.toInstant());
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

    public static List<Url> getAll() throws SQLException {
        var sql = "SELECT * FROM urls";
        try (var conn = dataSource.getConnection();
             var prepStatement = conn.prepareStatement(sql)) {
            var resultSet = prepStatement.executeQuery();
            var result = new ArrayList<Url>();

            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at");
                var url = new Url(name, createdAt.toInstant());
                url.setId(id);
                result.add(url);
            }
            return result;
        }
    }
}
