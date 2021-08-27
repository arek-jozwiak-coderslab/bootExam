package pl.samples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.arcyro.otp.lotos.model.LoadingBaseLotos;

import java.util.Optional;

@Repository
public interface LoadingBaseRepository extends JpaRepository<LoadingBaseLotos, Long> {

    Optional<LoadingBaseLotos> findByFirstCode(String number);

}
