package com.practice.splitexpense;

import com.practice.splitexpense.commands.Command;
import com.practice.splitexpense.commands.CommandExecutor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
@EnableJpaAuditing
public class SplitexpenseApplication implements CommandLineRunner  {

    private Scanner scanner;
    private CommandExecutor commandExecutor;

    public SplitexpenseApplication(CommandExecutor commandExecutor,List<Command> commands) {
        this.scanner = new Scanner(System.in);
        this.commandExecutor = commandExecutor;
        //this.commandExecutor.addCommand(command);
        this.commandExecutor.addCommands(commands);
    }
    public static void main(String[] args) {
        SpringApplication.run(SplitexpenseApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        while(true) {
            System.out.println("Enter input: ");
            String input = scanner.nextLine();
            commandExecutor.execute(input);
        }
    }
}
