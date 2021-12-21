package com.example.demo.service.implementation;

import com.example.demo.enumeration.Status;
import com.example.demo.model.Server;
import com.example.demo.repository.ServerRepository;
import com.example.demo.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

@RequiredArgsConstructor //lombok creates the constructor for us, so we don't need to inject
@Service
@Transactional //this is the same as try commit catch rollback
@Slf4j // Simple Logging Facade for Java. It enables a user to work with any of the logging frameworks such as Log4j...
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;
    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName()); //this logs a message to the console
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) throws IOException {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        //pinging
        InetAddress address = InetAddress.getByName(ipAddress);
        //inline if else -->  condition ? expr1 : expr2
        server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
        serverRepository.save(server); // we save the new status
        return server;

    }

    @Override
    public Collection<Server> list(int limit) {
        return null;
    }

    @Override
    public Server get(Long id) {
        return null;
    }

    @Override
    public Server update(Server server) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    // Other methods

    private String setServerImageUrl() {
        return null;
    }
}
