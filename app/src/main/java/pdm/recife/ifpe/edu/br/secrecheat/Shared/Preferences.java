package pdm.recife.ifpe.edu.br.secrecheat.Shared;

import android.content.Context;

public class Preferences {

    private String userIdentifier;

    public Preferences(Context context){

    }


    public String getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(String userIdentifier) {
        this.userIdentifier = userIdentifier;
    }
}
