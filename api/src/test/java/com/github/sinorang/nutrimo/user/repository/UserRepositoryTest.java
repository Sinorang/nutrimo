package com.github.sinorang.nutrimo.user.repository;

import com.github.sinorang.nutrimo.common.config.TestQuerydslConfig;
import com.github.sinorang.nutrimo.common.repository.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest // JPA 슬라이스 테스트 (Repository Bean만 로딩)
@ActiveProfiles("test") // application-test.yml (nutrimo_test DB)
@Import(TestQuerydslConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("User 저장 및 이메일 조회가 정상 동작한다")
    void saveAndFindByEmail() {
        // given
        User user = User.builder()
                .email("tester@example.com")
                .password("1234")
                .nickname("tester")
                .build();

        // when
        User saved = userRepository.save(user);
        Optional<User> found = userRepository.findByEmail("tester@example.com");

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("tester@example.com");
    }
}