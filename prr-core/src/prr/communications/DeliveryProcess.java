package prr.communications;

import java.io.Serializable;

import prr.notifications.Notification;

public interface DeliveryProcess extends Serializable {
    public void deliver(Notification notification);
}