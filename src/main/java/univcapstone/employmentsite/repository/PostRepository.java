package univcapstone.employmentsite.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import univcapstone.employmentsite.domain.Post;

import java.util.List;
import java.util.Map;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAll(Pageable pageable);
    Page<Post> findAllByOrderByDateDesc(Pageable pageable);
    @Query("SELECT p FROM Post p WHERE p.category='resume' ORDER BY p.date DESC")
    Page<Post> findResumeByOrderByDateDesc(Pageable pageable);
    @Query("SELECT p FROM Post p WHERE p.category='interview' ORDER BY p.date DESC")
    Page<Post> findInterviewByOrderByDateDesc(Pageable pageable);
    @Query("SELECT p FROM Post p WHERE p.category='share' ORDER BY p.date DESC")
    Page<Post> findShareByOrderByDateDesc(Pageable pageable);
    Post findByPostId(Long postId);
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:title% ORDER BY p.date DESC")
    List<Post> findByTitle(String title);
    @Query("SELECT p FROM Post p WHERE p.user.loginId= :loginId ORDER BY p.date DESC")
    List<Post> findMyPost(String loginId);
    //"SELECT LEAD(p.postId) OVER (ORDER BY p.date) AS next_post_id FROM Post p WHERE p.postId = :postId"

    @Query("SELECT MIN(p.postId) FROM Post p WHERE p.date > (SELECT p1.date FROM Post p1 WHERE p1.postId = :postId)")
    Long findNextPost(Long postId);

    @Query("SELECT MAX(p.postId) FROM Post p WHERE p.date < (SELECT p1.date FROM Post p1 WHERE p1.postId = :postId)")
    Long findPrevPost(Long postId);
}
