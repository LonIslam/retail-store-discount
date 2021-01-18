package com.example.retailstorediscounts.model;

import com.example.retailstorediscounts.entity.UserType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    private Long id;
    private String name;
    private UserType userType;
}
