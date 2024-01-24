package univcapstone.employmentsite.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import univcapstone.employmentsite.domain.Post;
import univcapstone.employmentsite.domain.User;
import univcapstone.employmentsite.dto.PostDto;
import univcapstone.employmentsite.service.BoardService;
import univcapstone.employmentsite.service.BookmarkService;
import univcapstone.employmentsite.service.UserService;
import univcapstone.employmentsite.util.response.BasicResponse;
import univcapstone.employmentsite.util.response.DefaultResponse;

import java.util.Optional;

@Slf4j
@RestController
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boardlist")
    public String boardMain(){
        //게시글 메인화면 보기
        return "";
    }

    @GetMapping("/boardlist/{postId}")
    public ResponseEntity<? extends BasicResponse> board(@PathVariable Long postId){
        Post post=boardService.showPost(postId);
        log.info("클릭한 게시물 정보:{}",post);

        DefaultResponse<Post> defaultResponse = DefaultResponse.<Post>builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("회원가입 완료")
                .result(post)
                .build();

        return ResponseEntity.ok()
                .body(defaultResponse);
    }

    @PostMapping("/boardlist/write")
    public ResponseEntity<? extends BasicResponse> boardWrite(
            @RequestBody @Validated PostDto postDto
    ){
        log.info("작성한 게시물 정보:{}",postDto);
        
        Post post=boardService.uploadPost(postDto);

        DefaultResponse<Post> defaultResponse = DefaultResponse.<Post>builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 작성 완료")
                .result(post)
                .build();

        return ResponseEntity.ok()
                .body(defaultResponse);

    }

    @PostMapping("/boardlist/edit/{postId}")
    public ResponseEntity<? extends BasicResponse> edit(
            @PathVariable Long postId,
            @RequestBody @Validated PostDto postDto
    ){
        log.info("수정한 게시물 정보:{}",postDto);
        
        postDto.setPostId(postId);
        boardService.updatePost(postDto);

        DefaultResponse<String> defaultResponse = DefaultResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 수정 완료")
                .result("")
                .build();

        return ResponseEntity.ok()
                .body(defaultResponse);

    }

    @DeleteMapping("/boardlist/delete/{postId}")
    public ResponseEntity<? extends BasicResponse> delete(@PathVariable("postId") Long postId){
        //게시글 삭제
        log.info("삭제하려는 게시물 id : {}",postId);
        boardService.deletePost(postId);

        DefaultResponse<String> defaultResponse = DefaultResponse.<String>builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 삭제 완료")
                .result("")
                .build();

        return ResponseEntity.ok()
                .body(defaultResponse);
    }


    @GetMapping("/boardlist/search/{boardTitle}")
    public ResponseEntity<? extends BasicResponse> search(@PathVariable String boardTitle){
        //게시글 검색 (제목으로)
        Optional<Post> post=boardService.searchbyTitle(boardTitle);

        log.info("검색을 위해 입력한 단어={} ,찾은 결과물 {}",boardTitle,post);

        DefaultResponse<Optional<Post>> defaultResponse = DefaultResponse.<Optional<Post>>builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("게시글 삭제 완료")
                .result(post)
                .build();

        return ResponseEntity.ok()
                .body(defaultResponse);
    }

    @GetMapping("/boardlist/{postId}/bookmark")
    public String bookmark(
            @RequestParam("postId") String postId,
            @RequestParam("userId") String userId,
            @PathVariable int board_id
    ){
        //게시글 북마크
        return "";
    }

    @PostMapping("/boardlist/{postId}/{replyId}")
    public String reply(
            @RequestParam("postId") String postId,
            @RequestParam("userId") String userId,
            @RequestParam("refId") String refId,
            @RequestParam("replyContent") String replyContent,
            @RequestParam("replyRefId") String replyRefId
    ){
        //댓글 달기
        return "";

    }


    @DeleteMapping("/boardlist/{postId}/{replyId}")
    public String replyDelete(
            @PathVariable Long postId,
            @PathVariable int replyId
    ){
        //댓글삭제.. 대댓글이 있다면 삭제안되도록 해야하나?..
        return "";
    }
}
