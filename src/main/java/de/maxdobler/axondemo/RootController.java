package de.maxdobler.axondemo;

import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.UUID;


@Controller
public class RootController {

    private CommandGateway commandGateway;

    public RootController(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    @GetMapping("/new")
    public String click(Model model) {

        var newTaskId = UUID.randomUUID().toString();
        var taskId = commandGateway.sendAndWait(new CreateTaskCommand(newTaskId));
        model.addAttribute("taskId", taskId);

        return "new";
    }
}
