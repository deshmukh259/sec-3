package com.springsec.sec3.redis;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
@Component
public class RedisPublisher {
    private final Jedis jedis;
    private final String channel;

    public RedisPublisher() {

        this.jedis = new Jedis("localhost", 6379);
        this.channel = "test-channel";
    }

    public void publish(String message) {
        jedis.publish(channel, message);
        System.out.println("Published message: " + message);
    }

    public void close() {
        jedis.close();
    }

    public static void main(String[] args) {
        RedisPublisher publisher = new RedisPublisher();
        publisher.publish("Hello, Redis!");
        publisher.close();
    }
}
