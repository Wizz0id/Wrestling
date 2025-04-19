package com.example.Wrestling.repository;

import com.example.Wrestling.dto.TitleDTO;
import com.example.Wrestling.entity.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title, Long> {
    @Query(value = "select title.id, title.name, promotion.picture " +
            "from title " +
            "left join promotion on title.promo_id = promotion.id " +
            "where title.name ilike '%' || :search || '%' " +
            "or promotion.name ilike '%' || :search || '%' ",nativeQuery = true)
    List<TitleDTO> findBySearch(String search);

    @Query(value = "select title.id, title.name, promotion.picture " +
            "from title " +
            "left join promotion on title.promo_id = promotion.id ",nativeQuery = true)
    List<TitleDTO> getAll();

    @Query(value = "select id, name, start_date, end_date, picture, promo_id " +
            "from title left join champions " +
            "on title.id = champions.title_id " +
            "where champions.wrestler_id=:wrestlerId", nativeQuery = true)
    List<Title> getAllByWrestler(long wrestlerId);
}
