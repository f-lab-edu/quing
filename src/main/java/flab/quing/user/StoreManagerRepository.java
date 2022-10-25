package flab.quing.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreManagerRepository extends JpaRepository<StoreManager, Long> {

    Optional<StoreManager> findByLoginId(String loginId);

}
