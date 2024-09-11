package com.hospital.hospital.repo.user;
import com.hospital.hospital.model.user.UserDb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepo extends JpaRepository<UserDb, String> {
}
