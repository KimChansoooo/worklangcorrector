package com.csk.worklangcorrector.config;

import lombok.extern.slf4j.Slf4j;
import org.h2.tools.Server;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Slf4j
@Component
@Profile("local")
public class H2ConsoleConfig {
    private Server webServer;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws SQLException {
        log.info("H2ConsoleConfig start");
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8078", "-tcpAllowOthers").start();
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("H2ConsoleConfig stop");
        this.webServer.stop();
    }
}
