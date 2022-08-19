package flab.quing.repository;

import flab.quing.domain.Menu;
import flab.quing.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByStore(Store store);
}
