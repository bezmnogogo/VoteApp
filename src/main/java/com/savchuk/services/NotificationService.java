package com.savchuk.services;

import com.savchuk.dao.entitties.Notification;
import com.savchuk.dao.repository.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by home on 18.05.17.
 */
@Service
public class NotificationService implements INotificationService{
    private final INotificationRepository notificationRepository;

    @Autowired
    public NotificationService(INotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Notification saveNotification(Notification notification) {
        return notificationRepository.saveAndFlush(notification);
    }
}
