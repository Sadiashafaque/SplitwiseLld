package com.practice.splitexpense.commands;

import com.practice.splitexpense.controller.UserController;
import com.practice.splitexpense.dtos.CreateUserDto;
import com.practice.splitexpense.models.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

// Step 2 - Create concrete commands
@Component
@AllArgsConstructor
public class CreateUserCommand implements Command {
    private UserController userController;
    @Override
    public boolean matches(String input) {
        if (Command.getCommand(input).equals(Commands.REGISTER_USER_COMMAND)) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(String input) {
        // UserController
        // Arguments -> register Tantia email password phoneNumber
        System.out.println("Executing create user command");
        List<String> tokens = Command.getTokens(input);

        // Create the user DTO
        CreateUserDto createUserDto = CreateUserDto.builder()
                .name(tokens.get(1))
                .email(tokens.get(2))
                .password(tokens.get(3))
                .phoneNumber(tokens.get(4))
                .build();

        User user = userController.createUser(createUserDto);
        System.out.println("Created user with id: " + user.getId());
    }
}
