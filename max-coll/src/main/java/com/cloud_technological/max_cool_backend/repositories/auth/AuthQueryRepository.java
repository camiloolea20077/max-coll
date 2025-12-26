package com.cloud_technological.max_cool_backend.repositories.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.cloud_technological.max_cool_backend.dto.auth.UserDetailDto;
import com.cloud_technological.max_cool_backend.utils.MapperRepository;

@Service
public class AuthQueryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Lazy
    private MapperRepository mapperRepository;

    public UserDetailDto findByUserLogin(String email) {
        try {
            String sql = """
            SELECT 
                u.id AS id,
                u.email AS email,
                u.nombre AS name,
                r.nombre AS role
            FROM public.usuario u
            LEFT JOIN public.rol r ON r.id = u.rol_id
            WHERE u.email = ?
            LIMIT 1;
            """;
            // Ejecutar la consulta SQL
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, email);

            // Convertir el resultado a DTO
            return mapperRepository.mapToDtoModel(result, UserDetailDto.class);

        } catch (EmptyResultDataAccessException e) {
            return null; // Devolver null si no hay resultados
        } catch (Exception e) {
            // Manejar otros errores de SQL o conversión
            e.printStackTrace();
            throw new RuntimeException("Error al ejecutar la consulta", e);
        }
    }
}
