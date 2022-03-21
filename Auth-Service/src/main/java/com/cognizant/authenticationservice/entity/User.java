package com.cognizant.authenticationservice.entity;

import lombok.*;

import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Getter
@Setter
@NoArgsConstructor
@Entity
@RequiredArgsConstructor
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String roles;
    
	
}
