package com.example.Wrestling.repository;

import com.example.Wrestling.dto.WrestlerDTO;
import com.example.Wrestling.entity.Wrestler;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {
    @Query(value = "select wrestler.id, fio, gender, height, picture, retired, start_of_career, trainer, weight, promo_id " +
            "from wrestler left join gimmick on wrestler.id = gimmick.wrestler_id " +
            "where wrestler.fio ilike '%' || :search || '%' or gimmick.name ilike '%' || :search || '%'", nativeQuery = true)
    List<Wrestler> findBySearch(String search);
    @Query("SELECT new com.example.Wrestling.dto.WrestlerDTO(" +
            "w.id, w.fio, w.height, w.weight, w.picture, w.gender, w.trainer, w.startOfCareer, w.retired, w.promotion.id, COUNT(m.id)) " +
            "FROM Wrestler w LEFT JOIN w.matches m " +
            "WHERE w.id = :id GROUP BY w")
    Optional<WrestlerDTO> getById(long id);
    @Query(value = "select * from wrestler where promo_id=:promotionID", nativeQuery = true)
    List<Wrestler> getAllByPromotionId(long promotionID);
    @Query(value = "select wrestler.id, fio, gender, height, picture, retired, start_of_career, trainer, weight, promo_id " +
            "from wrestler left join participants " +
            "on wrestler.id = participants.wrestler_id " +
            "where match_id=:matchId", nativeQuery = true)
    List<Wrestler> getAllByMatchId(long matchId);
    @Query(value = "select wrestler.id, fio, gender, height, picture, retired, start_of_career, trainer, weight, promo_id " +
            "from wrestler left join champions on " +
            "wrestler.id = champions.wrestler_id " +
            "where champions.title_id=:titleId", nativeQuery = true)
    List<Wrestler> getAllByTitleId(long titleId);
    @Modifying
    @Transactional
    @Query(value = "UPDATE Wrestler SET " +
            "fio = :#{#wrestler.fio}, " +
            "height = :#{#wrestler.height}, " +
            "weight = :#{#wrestler.weight}, " +
            "gender = :#{#wrestler.gender}, " +
            "picture = :#{#wrestler.picture}, " +
            "trainer = :#{#wrestler.trainer}, " +
            "startOfCareer = :#{#wrestler.startOfCareer}, " +
            "retired = :#{#wrestler.retired}, " +
            "promotion.id = :#{#wrestler.promotion.id} " +
            "WHERE id = :#{#wrestler.id}")
    Integer update(Wrestler wrestler);
}
