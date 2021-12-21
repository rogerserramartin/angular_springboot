package com.example.demo.service;

import com.example.demo.model.Server;


import java.io.IOException;
import java.util.Collection;

// we define the methods, like the DAO pattern, which has an interface that defines the crud operations
// these are just functionalities that we want in our application
public interface ServerService {
    Server create(Server server);
    Server ping(String ipAddress) throws IOException;
    Collection<Server> list(int limit);
    Server get(Long id);
    Server update(Server server);
    Boolean delete(Long id);
}
