package com.cloud_technological.max_cool_backend.repositories.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepository {
 @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM usuario WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
    
        Long count = namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
        return count != null && count > 0;
    }
    // public PageImpl<UsersTableDto> listUsers(PageableDto<Object> pageableDto) {
    //     int pageNumber = pageableDto.getPage() != null ? pageableDto.getPage().intValue() : 0;
    //     int pageSize = pageableDto.getRows() != null ? pageableDto.getRows().intValue() : 10;
    //     String search = pageableDto.getSearch() != null ? pageableDto.getSearch().trim() : null;

    //     StringBuilder sql = new StringBuilder("""
    //         SELECT 
    //             o.id,
    //             o.nombre_completo AS name,
    //             o.email AS email,
    //             r.nombre AS role,
    //             o.activo as activo,
    //             COUNT(*) OVER() AS total_rows
    //         FROM users o
    //         LEFT JOIN roles r ON r.id = o.role_id 
    //         WHERE 
    //             o.deleted_at IS NULL
    //             AND o.activo = 1
    //     """);

    //     MapSqlParameterSource params = new MapSqlParameterSource();

    //     if (search != null && !search.isEmpty()) {
    //         sql.append("""
    //             AND (
    //                 LOWER(o.nombre_completo) ILIKE :search
    //                 OR LOWER(o.email) ILIKE :search
    //             )
    //         """);
    //         params.addValue("search", "%" + search.toLowerCase() + "%");
    //     }

    //     if (pageableDto.getOrder_by() != null && !pageableDto.getOrder_by().isEmpty()) {
    //         sql.append(" ORDER BY ").append(pageableDto.getOrder_by()).append(" ").append(pageableDto.getOrder()).append(" ");
    //     } else {
    //         sql.append(" ORDER BY o.id ASC ");
    //     }

    //     sql.append(" OFFSET :offset LIMIT :limit");
    //     long offset = pageNumber * pageSize;
    //     params.addValue("offset", offset);
    //     params.addValue("limit", pageSize);

    //     List<Map<String, Object>> resultList = namedParameterJdbcTemplate.query(sql.toString(), params, new ColumnMapRowMapper());
    //     List<UsersTableDto> result = MapperRepository.mapListToDtoList(resultList, UsersTableDto.class);

    //     long count = resultList.isEmpty() ? 0 : ((Number) resultList.get(0).get("total_rows")).longValue();
    //     PageRequest pageable = PageRequest.of(pageNumber, pageSize);

    //     return new PageImpl<>(result, pageable, count);
    // }
    // public List<UsersElementsDto> getUsers() {
    //     String sql = """
    //         SELECT 
    //             u.id,
    //             CONCAT(u.nombre_completo, ' - ', r.nombre) AS nombre_con_rol
    //         FROM users u
    //         JOIN roles r ON u.role_id = r.id
    //     """;
    //     return namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(), new RowMapper<UsersElementsDto>() {
    //         @Override
    //         public UsersElementsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
    //             UsersElementsDto dto = new UsersElementsDto();
    //             dto.setId(rs.getLong("id"));
    //             dto.setNombre_con_rol(rs.getString("nombre_con_rol"));
    //             return dto;
    //         }
    //     });
    // }
}
