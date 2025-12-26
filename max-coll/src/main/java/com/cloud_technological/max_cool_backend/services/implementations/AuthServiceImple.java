package com.cloud_technological.max_cool_backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cloud_technological.max_cool_backend.dto.auth.AuthDto;
import com.cloud_technological.max_cool_backend.dto.auth.LoginDto;
import com.cloud_technological.max_cool_backend.dto.auth.UserDetailDto;
import com.cloud_technological.max_cool_backend.mappers.users.UserMappers;
import com.cloud_technological.max_cool_backend.repositories.auth.AuthQueryRepository;
import com.cloud_technological.max_cool_backend.repositories.users.UserJPARepository;
import com.cloud_technological.max_cool_backend.repositories.users.UserQueryRepository;
import com.cloud_technological.max_cool_backend.security.JwtTokenProvider;
import com.cloud_technological.max_cool_backend.services.AuthService;
import com.cloud_technological.max_cool_backend.services.UserService;
import com.cloud_technological.max_cool_backend.utils.AESencryptUtil;
import com.cloud_technological.max_cool_backend.utils.GlobalException;
import com.cloud_technological.max_cool_backend.utils.MapperRepository;

@Service
public class AuthServiceImple implements AuthService {

    private static final Integer TIME_TOKEN = 5;

    private static final String SEPARATOR = "---";

    @Autowired
    private AESencryptUtil encrypt;

    @Autowired
    private AuthQueryRepository authQueryRepository;

    @Autowired
    private MapperRepository mapperRepository;

    @Autowired
    private JwtTokenProvider _jwtTokenProvider;

    private final AuthenticationManager _authenticationManager;

    private final UserService _userService;

    private final UserQueryRepository userQueryRepository;
    private final UserJPARepository userJPARepository;
    private final UserMappers userMappers;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImple(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider,
            UserQueryRepository userQueryRepository, UserJPARepository userJPARepository, UserMappers userMappers, PasswordEncoder passwordEncoder
            ) {
        _jwtTokenProvider = jwtTokenProvider;
        this.userQueryRepository = userQueryRepository;
        this.userJPARepository = userJPARepository;
        this.userMappers = userMappers;
        this.passwordEncoder = passwordEncoder;
        _authenticationManager = authenticationManager;
        _userService = userService;
    }

    public AuthDto login(LoginDto loginDto) {
        try {
            Authentication authentication = _authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Obtener el usuario
            UserDetailDto user = authQueryRepository.findByUserLogin(loginDto.getEmail());
            if (user == null) {
                throw new GlobalException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
            }
                
            // Generación del token
            String token = _jwtTokenProvider.generateToken(authentication, loginDto.getEmail());
            
            // Crear DTO de respuesta
            AuthDto authDto = new AuthDto();
            authDto.setUser(user);
            authDto.setToken(token);
            
            return authDto;
        } catch (BadCredentialsException e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        } catch (Exception e) {
            throw new GlobalException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


// @Override
// @Transactional
// public AuthDto register(RegisterRequestDto dto) {
    
//     if (dto.getFarm_id() == null) {
//         throw new GlobalException(HttpStatus.BAD_REQUEST, "El ID de finca es requerido");
//     }
//     try {
//         Role role = roleQueryRepository.findByName("ADMIN")
//                 .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Rol ADMIN no encontrado"));

//         FarmEntity farm = farmsJPARepository.findById(dto.getFarm_id())
//                 .orElseThrow(() -> new GlobalException(HttpStatus.BAD_REQUEST, "Finca no encontrada"));
//         UserEntity userEntity = userMappers.createToEntity(userDto);
//         userEntity.setUsername(dto.getAdmin_email());
//         userEntity.setEmail(dto.getAdmin_email());
//         userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
//         userEntity.setActivo(1L);
//         userEntity.setCreated_at(LocalDateTime.now());
//         userEntity.setUpdated_at(LocalDateTime.now());

//         // Guardar solo una vez
//         userJPARepository.save(userEntity);

//         Authentication auth = new UsernamePasswordAuthenticationToken(dto.getAdmin_email(), dto.getPassword());
//         String token = _jwtTokenProvider.generateToken(auth, dto.getAdmin_email());

//         UserDetailDto userDetail = authQueryRepository.findByUserLogin(dto.getAdmin_email());
//         AuthDto authDto = new AuthDto();
//         authDto.setUser(userDetail);
//         authDto.setToken(token);

//         return authDto;

//     } catch (Exception e) {
//         System.err.println("Error en el registro: " + e.getMessage());
//         throw new GlobalException(HttpStatus.CONFLICT, "Error al registrar el usuario: " + e.getMessage());
//     }
// }



    

}
