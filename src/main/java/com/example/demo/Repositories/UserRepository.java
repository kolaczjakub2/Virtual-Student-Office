package com.example.demo.Repositories;

import com.example.demo.Domain.Declaration;
import com.example.demo.Domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByCardNumber(int integer);

    Optional<User> findByDeclaration(Declaration declaration);

    @Query(value = "SELECT * FROM USER  WHERE DECLARATION_ID IS NOT NULL", nativeQuery = true)
    Optional<List<User>> findUsersWithDeclarations();

    @Query(value = "SELECT * FROM USER  INNER JOIN DECLARATION ON USER.DECLARATION_ID=DECLARATION.ID " +
            "WHERE DECLARATION.ROOM IN :selectedRooms AND DECLARATION.LOCAL_DATE IN :selectedDates  ", nativeQuery = true)
    Optional<List<User>> findUsersWithDeclarationsByRoomAndDate(@Param("selectedRooms") ArrayList<String> selectedRooms,
                                                                @Param("selectedDates") ArrayList<LocalDate> selectedDates);
}
