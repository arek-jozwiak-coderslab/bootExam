package pl.samples.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.arcyro.otp.lotos.model.TimetableLoadingJoin;
import pl.arcyro.otp.lotos.model.TimetableStatusLotos;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface TimetableLoadingJoinRepository extends JpaRepository<TimetableLoadingJoin, Long> {

    @Modifying
    @Query("update TimetableLotos t set t.status=?2 where t.timetableLoadingJoin.id=?1")
    void updateStatus(Long timetableJoinId, TimetableStatusLotos status);

    @Modifying
    @Query("update TimetableLotos t set t.status=?2 where t.timetableLoadingJoin.id IN (?1)")
    void updateStatus(List<Long> ids, TimetableStatusLotos status);

    @Modifying
    @Query("delete from TimetableLotos t where t.id in ?1")
    void deleteByIds(long[] ids);
}
