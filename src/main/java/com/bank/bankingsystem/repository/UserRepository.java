        package com.bank.bankingsystem.repository;

        import com.bank.bankingsystem.entity.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.Optional;

        @Repository
        public interface UserRepository extends JpaRepository<User, Long> {
            Optional<User> findByPesel(String pesel);
            Optional<User> findByEmail(String email);
        }