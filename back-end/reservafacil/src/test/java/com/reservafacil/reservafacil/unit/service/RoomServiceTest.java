package com.reservafacil.reservafacil.unit.service;

import com.reservafacil.reservafacil.repositories.RoomRepository;
import com.reservafacil.reservafacil.services.RoomService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository repository;
    @InjectMocks
    private RoomService service;

}
