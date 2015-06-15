package server;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerImplTest {
    
    @Test
    public void testGetConnection_resultNormal() {
        Server server = new ServerImpl();
        String expectedConnectionResult = "1";
        String[] connectionResult = server.getConnection();
        String actualConnectionResult = connectionResult[0];
        assertEquals(expectedConnectionResult, actualConnectionResult);

        String expectedUserId = "0";
        String actualUserId = connectionResult[1];
        assertEquals(expectedUserId, actualUserId);
    }

    @Test
    public void testGetConnection_resultFailed() {
        Server server = new ServerImpl();
        String expectedConnectionResult = "-1";
        server.getConnection(); // user1
        server.getConnection(); // user2
        server.getConnection(); // user3
        server.getConnection(); // user4
        server.getConnection(); // user5
        String[] connectionResult = server.getConnection(); // user6
        String actualConnectionResult = connectionResult[0];
        assertEquals(expectedConnectionResult, actualConnectionResult);

        String actualUserId = connectionResult[1];
        assertEquals(null, actualUserId);
    }

    @Test
    public void testSetAndGetUserBet() {
        Server server = new ServerImpl();
        String[] connectionResult = server.getConnection();
        int userId = Integer.parseInt(connectionResult[1]);
        int expectedUserBet = 50;
        server.setUserBet(userId, expectedUserBet);
        int actualUserBet = server.getUserBet(userId);
        assertEquals(expectedUserBet, actualUserBet);
    }

    @Test
    public void testSetUserMoney() {
        Server server = new ServerImpl();
        String[] connectionResult = server.getConnection();
        int userId = Integer.parseInt(connectionResult[1]);
        int expectedUserMoney = 150;
        server.setUserMoney(userId, expectedUserMoney);
        int actualUserMoney = server.getUserMoney(userId);
        assertEquals(expectedUserMoney, actualUserMoney);
    }
}