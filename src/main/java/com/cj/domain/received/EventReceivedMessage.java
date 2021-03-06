package com.cj.domain.received;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("event")
public class EventReceivedMessage extends ReceivedMessage {
	public static final String SUBSCRIBE = "subscribe";
	private String event;
	private String eventKey;
}
