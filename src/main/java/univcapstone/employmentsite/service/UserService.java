package univcapstone.employmentsite.service;

import univcapstone.employmentsite.domain.User;
import univcapstone.employmentsite.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입
     */
    public Long join(User user) {
        validateDuplicateLoginId(user); //중복 로그인 아이디 검증
        userRepository.save(user);
        return user.getId();
    }

    /**
     * 로그인
     */
    public User login(String loginId, String password) {
        return userRepository.findByLoginId(loginId)
                .filter(u -> u.getPassword().equals(password))
                .orElse(null);
    }

    /**
     * 로그인 ID 중복 검사 함수
     */
    public void validateDuplicateLoginId(User user) {
        userRepository.findByLoginId(user.getLoginId())
                .ifPresent(u -> {
                    throw new IllegalStateException(u + "은(는) 이미 존재하는 아이디입니다.");
                });
    }
    public void validateDuplicateLoginId(String userId) {
        userRepository.findByLoginId(userId)
                .ifPresent(u -> {
                    throw new IllegalStateException(u + "은(는) 이미 존재하는 아이디입니다.");
                });
    }

    /**
     * ID 찾기
     * @param name
     * @param email
     * @return
     */
    public User findId(String name,String email){
        return userRepository.findByEmail(email)
                .filter(u->u.getName().equals(name))
                .orElse(null);
    }

    /**
     * 비밀번호 찾기
     * @param userId
     * @param name
     * @param email
     * @return
     */
    public User findPassword(String userId,String name,String email){
        return userRepository.findByLoginId(userId)
                .filter(u->u.getName().equals(name))
                .filter(u->u.getEmail().equals(email))
                .orElse(null);
    }

    /**
     * 비밀번호 변경
     * @param id
     * @param newPassword
     */
    public void updatePassword(Long id,String newPassword){
        userRepository.updatePassword(id,newPassword);
    }
}
