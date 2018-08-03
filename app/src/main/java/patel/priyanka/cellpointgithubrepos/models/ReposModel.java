package patel.priyanka.cellpointgithubrepos.models;

import java.util.ArrayList;

public class ReposModel {

    private String name;
    private String language;
    private int stargazers_count;
    private ArrayList<ReposModel> languageList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public ArrayList<ReposModel> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(ArrayList<ReposModel> languageList) {
        this.languageList = languageList;
    }
}
