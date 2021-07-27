package com.mattmalec.pterodactyl4j.client.entities.impl;

import com.mattmalec.pterodactyl4j.PteroAction;
import com.mattmalec.pterodactyl4j.requests.PteroActionImpl;
import com.mattmalec.pterodactyl4j.client.entities.ClientServer;
import com.mattmalec.pterodactyl4j.client.entities.Schedule;
import com.mattmalec.pterodactyl4j.client.managers.ScheduleAction;
import com.mattmalec.pterodactyl4j.client.managers.ScheduleManager;
import com.mattmalec.pterodactyl4j.requests.Route;

public class ScheduleManagerImpl implements ScheduleManager {

	private final ClientServer server;
	private final PteroClientImpl impl;

	public ScheduleManagerImpl(ClientServer server, PteroClientImpl impl) {
		this.server = server;
		this.impl = impl;
	}

	@Override
	public ScheduleAction createSchedule() {
		return new CreateScheduleImpl(server, impl);
	}

	@Override
	public ScheduleAction editSchedule(Schedule schedule) {
		return new EditScheduleImpl(server, schedule, impl);
	}

	@Override
	public PteroAction<Void> delete(Schedule schedule) {
		return PteroActionImpl.onRequestExecute(impl.getP4J(),
				Route.Schedules.DELETE_SCHEDULE.compile(server.getUUID().toString(), schedule.getId()));
	}
}
