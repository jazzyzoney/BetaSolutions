package org.example.betasolutions.subProject;
import org.example.betasolutions.ConnectionManager;
import org.example.betasolutions.PSSTSuperclass;
import org.springframework.stereotype.Repository;

@Repository
public class SubProjectRepository extends PSSTSuperclass {

    public SubProjectRepository(ConnectionManager connectionManager) {
        super(connectionManager);
    }
}
