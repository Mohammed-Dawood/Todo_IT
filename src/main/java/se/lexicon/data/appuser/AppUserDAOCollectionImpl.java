package se.lexicon.data.appuser;

import se.lexicon.model.AppUser;
import java.util.ArrayList;
import java.util.List;

public class AppUserDAOCollectionImpl implements AppUserDAO {
    private List<AppUser> appUsers = new ArrayList<>();

    @Override
    public void persist(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser cannot be null");
        appUsers.add(appUser);
    }

    @Override
    public AppUser findByUsername(String username) {
        return appUsers.stream()
                .filter(appUser -> appUser.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AppUser> findAll() {
        return new ArrayList<>(appUsers);
    }

    @Override
    public boolean remove(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser cannot be null");
        return appUsers.remove(appUser);
    }
}
