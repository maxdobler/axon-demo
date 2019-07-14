package de.maxdobler.axondemo.task;

import org.axonframework.commandhandling.gateway.CommandGateway;

import org.axonframework.queryhandling.QueryGateway;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;


@Controller
@RequestMapping("/task")
public class TaskController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public TaskController(CommandGateway commandGateway, QueryGateway queryGateway) {

        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping("/{id}")
    String updateTask(@PathVariable("id") String id,
        @RequestParam("time") Integer time) {

        var command = new AddTimeCommand(id, time);
        commandGateway.sendAndWait(command);

        return "redirect:/task/" + id;
    }


    @GetMapping("/{id}")
    public String task(@PathVariable("id") String taskId, Model model) throws InterruptedException, ExecutionException,
        TimeoutException {

        var taskDto = queryGateway.query(new GetTaskQuery(taskId), TaskDto.class).get(1L, SECONDS);

        model.addAttribute("taskId", taskDto.id);
        model.addAttribute("timelogs", taskDto.timeLogs);

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
