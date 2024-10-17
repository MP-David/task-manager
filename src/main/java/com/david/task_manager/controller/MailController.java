package com.david.task_manager.controller;

import com.david.task_manager.domain.Task;
import com.david.task_manager.repository.TaskRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping(path = "mail")
@Log4j2
public class MailController {

    private final JavaMailSender mailService;
    private final TemplateEngine templateEngine;
    private final TaskRepository taskRepository;

    @PostMapping("/mail-send")
    public void sendMail() {

        List<Task> tasksEndingInThreeDays =  taskRepository.findTasksEndingInThreeDays()
                .stream()
                .filter(task -> task.getResponsible().getEmail() != null)
                .toList();

        for (Task task : tasksEndingInThreeDays) {
            try {

                Map<String, Object> variables = new HashMap<>();
                variables.put("nomeTask", task.getTitle());
                variables.put("numeroTask", task.getId());
                variables.put("scoreTask", task.getScore());

                String htmlContent = generateHtmlContent("warning-3-days", variables);
                sendHtmlMail("mail@gmail.com", task.getResponsible().getEmail(), "Task Manager", htmlContent);

            } catch (Exception e) {
                log.error("Erro ao enviar e-mail ao: " + task.getResponsible().getEmail() + "Erro: " + e.getMessage());
            }
        }
    }

    private String generateHtmlContent(String templateName, Map<String, Object> variables) {
        Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateName, context);
    }

    private void sendHtmlMail(String from, String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailService.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        mailService.send(message);
    }
}
