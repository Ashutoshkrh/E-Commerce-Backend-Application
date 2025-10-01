package com.globazaar.userservice.service;

import com.globazaar.userservice.dto.CreateUserDto;
import com.globazaar.userservice.dto.UserDto;
import com.globazaar.userservice.entity.User;
import com.globazaar.userservice.exception.ResourceNotFoundException;
import com.globazaar.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.globazaar.userservice.client.ProductClient;
import com.globazaar.userservice.dto.ProductDto;
import com.globazaar.userservice.dto.UserResponseDto;
import java.util.List;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductClient productClient; // Inject the product client

    public UserResponseDto createUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setUsername(createUserDto.getUsername());
        user.setEmail(createUserDto.getEmail());
        user.setPassword(createUserDto.getPassword());

        User savedUser = userRepository.save(user);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(savedUser.getId());
        responseDto.setUsername(savedUser.getUsername());
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setProducts(null); // Set to null for a new user

        return responseDto;
    }

    public UserResponseDto getUserById(Long userId) {
        User user = findUserById(userId);

        List<ProductDto> products = productClient.getProductsForUser(userId);

        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setId(user.getId());
        responseDto.setUsername(user.getUsername());
        responseDto.setEmail(user.getEmail());

        if (products != null && !products.isEmpty()) {
            responseDto.setProducts(products);
        } else {
            responseDto.setProducts(null);
        }

        return responseDto;
    }


    public void deleteUser(Long userId) {
        User user = findUserById(userId);
        userRepository.delete(user);
    }

    // A private helper method to find the user, reducing code duplication
    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }

    // A private helper method to handle the conversion logic
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}