package com.savchuk.services;

import com.savchuk.dao.entitties.Notification;

/**
 * Created by home on 18.05.17.
 */
public interface INotificationService {
    Notification saveNotification(Notification notification);
}
