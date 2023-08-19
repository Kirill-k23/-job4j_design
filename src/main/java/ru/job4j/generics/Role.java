package ru.job4j.generics;

public class Role extends Base {
    private final String rolename;

    public Role(String id, String roleName) {
        super(id);
        this.rolename = roleName;
    }

    public String getRoleName() {
        return rolename;
    }
}
