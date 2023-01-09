package dev.mayaqq.ygasi.registry;

import dev.mayaqq.ygasi.events.PlayerConnectEvent;

public class EventRegistry {
    public static void register() {
        PlayerConnectEvent.onPlayerConnect();
    }
}
