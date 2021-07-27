package com.mattmalec.pterodactyl4j.application.managers;

import com.mattmalec.pterodactyl4j.PteroAction;
import com.mattmalec.pterodactyl4j.requests.PteroActionImpl;
import com.mattmalec.pterodactyl4j.application.entities.ApplicationServer;
import com.mattmalec.pterodactyl4j.application.entities.impl.PteroApplicationImpl;
import com.mattmalec.pterodactyl4j.requests.Route;

public class ServerController {

	private final ApplicationServer server;
	private final PteroApplicationImpl impl;

	public ServerController(ApplicationServer server, PteroApplicationImpl impl) {
		this.server = server;
		this.impl = impl;
	}

	public PteroAction<Void> suspend() {
		return PteroActionImpl.onRequestExecute(impl.getP4J(), Route.Servers.SUSPEND_SERVER.compile(server.getId()));
	}

	public PteroAction<Void> unsuspend() {
		return PteroActionImpl.onRequestExecute(impl.getP4J(), Route.Servers.UNSUSPEND_SERVER.compile(server.getId()));
	}

	public PteroAction<Void> reinstall() {
		return PteroActionImpl.onRequestExecute(impl.getP4J(), Route.Servers.REINSTALL_SERVER.compile(server.getId()));
	}

	public PteroAction<Void> delete(boolean withForce) {
		return PteroActionImpl.onRequestExecute(impl.getP4J(), withForce ?
				Route.Servers.FORCE_DELETE_SERVER.compile(server.getId()) :
				Route.Servers.SAFE_DELETE_SERVER.compile(server.getId()));
	}
}
