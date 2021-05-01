package cat.itb.pixiv.ClassesModels;

import java.util.ArrayList;
import java.util.List;

public class User {
    String username;
    String password;
    String key;
    String imatgePerfil;
    List<String> favorites=new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String key, String imatgePerfil) {
        this.username = username;
        this.password = password;
        this.key = key;
        this.imatgePerfil = imatgePerfil;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getImatgePerfil() {
        return imatgePerfil;
    }

    public void setImatgePerfil(String imatgePerfil) {
        this.imatgePerfil = imatgePerfil;
    }

    public boolean isFaved(String id){
        return favorites.contains(id);
    }

    public void removeFavorite(String id){
        favorites.remove(id);
    }

    public void addFavorite(String id){
        favorites.add(id);
    }



    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", key='" + key + '\'' +
                ", imatgePerfil='" + imatgePerfil + '\'' +
                '}';
    }
}
