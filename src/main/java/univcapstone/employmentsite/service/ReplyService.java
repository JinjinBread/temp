package univcapstone.employmentsite.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univcapstone.employmentsite.domain.Post;
import univcapstone.employmentsite.domain.Reply;
import univcapstone.employmentsite.domain.User;
import univcapstone.employmentsite.dto.ReplyDto;
import univcapstone.employmentsite.repository.BoardRepository;
import univcapstone.employmentsite.repository.ReplyRepository;

@Slf4j
@Transactional
@Service
public class ReplyService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyService(BoardRepository boardRepository, ReplyRepository replyRepository) {
        this.boardRepository = boardRepository;
        this.replyRepository = replyRepository;
    }

    public Reply saveReply(Long postId, User user, ReplyDto replyDto) {

        Post post = boardRepository.getReferenceById(postId);

        Reply reply = Reply.builder()
                .post(post)
                .user(user)
                .parentReplyId(replyDto.getParentReplyId())
                .replyContent(replyDto.getReplyContent())
                .build();

        replyRepository.save(reply);
        return reply;
    }

    public void deleteReply(Long replyId, User loginUser) {
        Reply findReply = replyRepository.findByReplyId(replyId);
        User replyWriter = findReply.getUser();

        if (!replyWriter.equals(loginUser)) {
            throw new IllegalStateException("자신이 작성한 댓글만 삭제할 수 있습니다.");
        }

        replyRepository.delete(replyId);
    }
}
