package io.github.mavenreposs.royalcms.component.storage;

public class Permission {

    // 公开权限
    private String publicPermission;
    // 私有权限
    private String privatePermission;

    public Permission(String publicPermission, String privatePermission) {
        this.publicPermission = Integer.valueOf(publicPermission,8).toString();
        this.privatePermission = Integer.valueOf(privatePermission,8).toString();
    }

    public String getPublicPermission() {
        return publicPermission;
    }

    public void setPublicPermission(String publicPermission) {
        this.publicPermission = publicPermission;
    }

    public String getPrivatePermission() {
        return privatePermission;
    }

    public void setPrivatePermission(String privatePermission) {
        this.privatePermission = privatePermission;
    }
}
