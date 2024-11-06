package uz.alex.photoappapiusers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alex.photoappapiusers.entitiy.UserEntity;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}

