package com.example.demo.service.implementation;

import com.example.demo.enumeration.Status;
import com.example.demo.model.Server;
import com.example.demo.repository.ServerRepository;
import com.example.demo.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import static java.lang.Boolean.TRUE;

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
        log.info("Fetching all servers");
        //we want to limit the number of servers we get back, that's why we use PageRequest
        return serverRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        Optional<Server> optionalServer = serverRepository.findById(id);
        return optionalServer.orElse(null);
        //return optionalServer.get();
        //Or we can do this --> return serverRepository.findById(id);
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        // jpa is smart enough to know that this server has an existent id
        // if the id is null or isn't there yet, jpa will create a new Server
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server with id: {}", id);
        serverRepository.deleteById(id);
        return TRUE;
    }

    // Other methods

    // random image
    private String setServerImageUrl() {
        String[]imageNames = {"server1.png", "server2.png", "server3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image" + imageNames[new Random().nextInt(3)]).toString();
    }
}
