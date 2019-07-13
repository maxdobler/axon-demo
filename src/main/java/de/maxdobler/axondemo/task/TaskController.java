package de.maxdobler.axondemo.task;

import org.axonframework.commandhandling.gateway.CommandGateway;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;


@Controller
@RequestMapping("/task")
public class TaskController {

    private final CommandGateway commandGateway;

    public TaskController(CommandGateway commandGateway) {

        this.commandGateway = commandGateway;
    }

    @PostMapping("/{id}")
    String updateTask(@PathVariable("id") String id,
        @RequestParam("time") Integer time, Model model) {

        var command = new AddTimeCommand(id, time);
        commandGateway.sendAndWait(command);
        model.addAttribute("taskId", id);

        return "task";
    }


    @GetMapping("/{id}")
    public String task(Model model) {

        model.addAttribute("taskId", "abc");

        return "task";
    }


    @GetMapping("/new")
    public String newTask(Model model) {

        var newTaskId = UUID.randomUUID().toString();
        var taskId = commandGateway.sendAndWait(new CreateTaskCommand(newTaskId));
        model.addAttribute("taskId", taskId);

        return "task";
    }
}
