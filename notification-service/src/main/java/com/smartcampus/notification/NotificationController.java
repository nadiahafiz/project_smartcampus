package com.smartcampus.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.smartcampus.notification.Notification;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // 1. POST - Save a new broadcast alert
    @PostMapping
    public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {
        Notification savedLog = notificationRepository.save(notification);
        return new ResponseEntity<>(savedLog, HttpStatus.CREATED);
    }

    // 2. GET - Pull down all alerts history for the UI
    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return new ResponseEntity<>(notificationRepository.findAll(), HttpStatus.OK);
    }
}