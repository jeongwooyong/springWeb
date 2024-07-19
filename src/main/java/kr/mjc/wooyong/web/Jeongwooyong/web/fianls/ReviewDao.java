package kr.mjc.wooyong.web.Jeongwooyong.web.fianls;


import kr.mjc.wooyong.web.dao.Limit;

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
public class ReviewDao {

    private static final String LIST_REVIEW = """
      select reviewId, contents, grade, userId, name, cdate from review
      order by reviewId desc limit ?,?
      """;

    private static final String GET_REVIEW = """
      select reviewId, contents, grade, userId, name, cdate from review
      where reviewId=?
      """;

    private static final String GET_USER_REVIEW = """
      select reviewId, contents, grade, userId, name, cdate from review
      where reviewId=? and userId=?
      """;

    private static final String ADD_REVIEW = """
      insert review(contents, userId, name , grade)
      values (:contents, :userId,:name,:grade)
      """;


    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    /**
     * resultSet을 review 오브젝트로 자동 매핑하는 매퍼
     */
    private final RowMapper<Review> reviewRowMapper =
            new BeanPropertyRowMapper<>(Review.class);

    public List<Review> listReviews(Limit limit) {
        return jdbcTemplate.query(LIST_REVIEW, reviewRowMapper,
                limit.getOffset(), limit.getCount());
    }

    public Review getReview(int reviewId) {
        return jdbcTemplate.queryForObject(GET_REVIEW, reviewRowMapper,
                reviewId);
    }

    public Review getUserReview(int reviewId, int userId) {
        return jdbcTemplate.queryForObject(GET_USER_REVIEW, reviewRowMapper,
                reviewId, userId);
    }

    public void addReview(Review review) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(review);
        namedParameterJdbcTemplate.update(ADD_REVIEW, params);
    }

}