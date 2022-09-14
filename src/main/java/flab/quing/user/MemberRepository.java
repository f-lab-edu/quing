package flab.quing.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<User, Long> {
    Optional<User> findByName(String name);
    Optional<User> findByPhone(String phone);
}

