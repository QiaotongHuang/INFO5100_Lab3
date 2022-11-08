package controller.Admin;

import java.util.HashMap;

public class AdminDir {
    //<userName,passWord>
    private HashMap<String,AdminImp> Admin = new HashMap<>();
    private HashMap<String,CommunityAdminImp> CommunityAdmin = new HashMap<>();


    public HashMap<String, AdminImp> getAdmins() {
        return Admin;
    }

    public HashMap<String, CommunityAdminImp> getCommunityAdmins() {
        return CommunityAdmin;
    }

    public AdminImp addAdmin(String login, String password) {
        AdminImp temp = new AdminImp(login,password);
        Admin.put(login,temp);
        return temp;
    }

    public CommunityAdminImp addCommunityAdmin(String login, String password,String community) {
        CommunityAdminImp temp =new CommunityAdminImp(community,login,password);
        CommunityAdmin.put(login,temp);
        return temp;
    }
}
