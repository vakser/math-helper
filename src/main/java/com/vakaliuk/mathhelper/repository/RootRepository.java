package com.vakaliuk.mathhelper.repository;

import com.vakaliuk.mathhelper.entity.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RootRepository extends JpaRepository<Root, Long> {
    @Query(value = "select * from roots as r where r.value=:value", nativeQuery = true)
    List<Root> findByRootValue(@Param("value") Double value);
}
