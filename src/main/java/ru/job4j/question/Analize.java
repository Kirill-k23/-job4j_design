package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted;
        Map<Integer, User> users = new HashMap<>();
        for (User user : previous) {
            users.put(user.getId(), user);
        }
        for (User us : current) {
            int usId = us.getId();
            if (!users.containsKey(usId)) {
                added++;
            }
            if (users.containsKey(usId) && !users.containsValue(us)) {
                changed++;
            }
            users.remove(usId);
        }
        deleted = users.size();
        return new Info(added, changed, deleted);
    }
}
