package com.reservafacil.reservafacil.repositories;

import com.reservafacil.reservafacil.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
