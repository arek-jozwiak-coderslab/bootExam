package pl.samples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import pl.arcyro.otp.lotos.model.TimetableLotos;
import pl.arcyro.otp.lotos.model.TimetableStatusLotos;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableLotosRepository extends JpaRepository<TimetableLotos, Long>, QuerydslPredicateExecutor<TimetableLotos> {

    Optional<TimetableLotos> findByDeliveryNumber(String deliveryNumber);
    List<TimetableLotos> findByLoadingDate(LocalDate date);

    @Query("SELECT ts from TimetableLotos ts where ts.status <>?1 and ts.userLotos.userId=?2 order by ts.loadingDate, ts.loadingFrom")
    List<TimetableLotos> findAllExcludeByStatusAndUserId(TimetableStatusLotos status, Long id);

    List<TimetableLotos> findAllByTimetableLoadingJoinId(Long id);

}
