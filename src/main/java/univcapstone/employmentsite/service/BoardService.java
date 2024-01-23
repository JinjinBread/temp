package univcapstone.employmentsite.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univcapstone.employmentsite.domain.Post;
import univcapstone.employmentsite.dto.PostDto;
import univcapstone.employmentsite.repository.BoardRepository;
import univcapstone.employmentsite.repository.BookmarkRepository;

@Slf4j
@Transactional
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Post showPost(Long postId){
        Post post=boardRepository.findByPostId(postId);
        if(post==null){
            throw new IllegalStateException("해당하는 게시글을 찾을 수 없습니다.");
        }
        return post;
    }

    public Post uploadPost(PostDto postData){
        Post post=new Post(postData.getPostId(),
                postData.getTitle(),
                postData.getContent(),
                postData.getFileName(),
                postData.getUserId());
        boardRepository.save(post);
        return post;
    }

    public void updatePost(PostDto postData) {
        Post post=new Post(postData.getPostId(),
                postData.getTitle(),
                postData.getContent(),
                postData.getFileName(),
                postData.getUserId());
        boardRepository.update(post);

    }
}
