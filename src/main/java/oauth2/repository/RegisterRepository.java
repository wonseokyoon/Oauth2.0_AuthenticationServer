package oauth2.repository;

import oauth2.domain.RegisterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterEntity, String> {
    Optional<RegisterEntity> findByClientId(String clientId);

}
