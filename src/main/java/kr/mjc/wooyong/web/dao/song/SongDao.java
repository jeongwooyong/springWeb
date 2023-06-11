package kr.mjc.wooyong.web.dao.song;

import kr.mjc.wooyong.web.dao.Limit;
import kr.mjc.wooyong.web.dao.song.Song;
import kr.mjc.wooyong.web.dao.song.Song;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class SongDao {
    private static final String ADD_SONG = """
      insert song(title, name)
      values (:title, :name)
      """;
    private static final String LIST_SONGS = """
      select songId, title, name from song
      order by songId desc limit ?,?
      """;

    private static final String GET_SONGS = """
      select songId, title, name from song
      where songId=?
      """;



    private static final String UPDATE_SONGS = """
      update song set title=:title, name=:name
      where songId=:songId
      """;


    private  static final String DELETE_SONGS = """
           delete from song where songId = ? 
        """;


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final RowMapper<Song> songRowMapper =
            new BeanPropertyRowMapper<>(Song.class);

    public List<Song> listSongs(Limit limit) {
        return jdbcTemplate.query(LIST_SONGS, songRowMapper,
                limit.getOffset(), limit.getCount());
    }

    public Song getSong(int songId) {
        return jdbcTemplate.queryForObject(GET_SONGS, songRowMapper,
                songId);
    }

 

    public void addSong(Song song) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(song);
        namedParameterJdbcTemplate.update(ADD_SONG, params);
    }

    public int updateSong(Song song) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(song);
        return namedParameterJdbcTemplate.update(UPDATE_SONGS, params);
    }

    public int deleteSong(int songId, int userId) {
        return jdbcTemplate.update(DELETE_SONGS, songId, userId);
    }

}

