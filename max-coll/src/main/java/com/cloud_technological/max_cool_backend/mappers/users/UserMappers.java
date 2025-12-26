package com.cloud_technological.max_cool_backend.mappers.users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.cloud_technological.max_cool_backend.dto.users.CreateUserDto;
import com.cloud_technological.max_cool_backend.dto.users.UserDto;
import com.cloud_technological.max_cool_backend.entity.UsersEntity;


@Mapper(componentModel = "spring")
public interface UserMappers {
  @Mappings({
    @Mapping(target = "id", ignore = true),
    @Mapping(target = "createdAt", ignore = true),
    @Mapping(target = "empresaId", source = "dto.companyId"),
    @Mapping(target = "rolId", source = "dto.roleId"),
    @Mapping(target = "nombre", source = "dto.name"),
    @Mapping(target = "passwordHash", source = "dto.password"),
    @Mapping(target = "activo", source = "dto.active"),
  })
  UsersEntity createToEntity(CreateUserDto dto);

  @Mappings({
    @Mapping(target = "companyId", source = "entity.empresaId"),
    @Mapping(target = "roleId", source = "entity.rolId"),
    @Mapping(target = "name", source = "entity.nombre"),
    @Mapping(target = "active", source = "entity.activo"),
  })
  UserDto entityToDto(UsersEntity entity);
}
