package com.example.Wrestling.repository;

import com.example.Wrestling.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    @Query(value = "select * from title where name ilike '%' || :search || '%'",nativeQuery = true)
    List<Title> findBySearch(String search);
    @Query(value = "select id, name, start_date, end_date, picture, promo_id " +
            "from title left join champions " +
            "on title.id = champions.title_id " +
            "where champions.wrestler_id=:wrestlerId", nativeQuery = true)
    List<Title> getAllByWrestler(long wrestlerId);
}
