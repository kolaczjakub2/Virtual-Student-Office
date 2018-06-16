package com.example.demo.Repositories;

import com.example.demo.Domain.Declaration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.Set;

public interface DeclarationRepository extends CrudRepository<Declaration,Long> {
    @Query(value = "SELECT room FROM DECLARATION ", nativeQuery = true)
    Set<String> findRoomNames();
    @Query(value = "SELECT local_Date FROM DECLARATION  WHERE room=?1", nativeQuery = true)
    Set<Date> getAvailableDatesForRoom(String name);

    @Query(value = "SELECT * FROM DECLARATION  WHERE room=?1 AND local_Date=?2 AND local_Time=?3", nativeQuery = true)
    Optional<Declaration> getDeclarationByTimeAndRoom(String name, LocalDate localDate, LocalTime Time);

}
