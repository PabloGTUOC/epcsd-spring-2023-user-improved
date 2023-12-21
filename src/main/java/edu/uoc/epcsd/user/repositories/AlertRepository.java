package edu.uoc.epcsd.user.repositories;

import edu.uoc.epcsd.user.entities.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    @Query("select a from Alert a where a.productId = ?1 and a.from <= ?2 and a.to >= ?2")
    List<Alert> findAlertsByProductIdAndInterval(Long productId, LocalDate availableOnDate);

    @Query("SELECT a FROM Alert a WHERE a.productId = :productId AND :date BETWEEN a.from AND a.to")
    List<Alert> findAlertsByProductIdAndDate(@Param("productId") Long productId, @Param("date") LocalDate date);

    @Query("SELECT a FROM Alert a WHERE a.user.id = :userId AND :from <= a.to AND :to >= a.from")
    List<Alert> findAlertsByUserAndDateInterval(@Param("userId") Long userId, @Param("from") LocalDate from, @Param("to") LocalDate to);

}