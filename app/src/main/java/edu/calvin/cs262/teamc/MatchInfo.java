package edu.calvin.cs262.teamc;

public class MatchInfo {
    private String name;
    private String breed;
    private int imgSrc;

    public MatchInfo() {
        name = breed = null;
        imgSrc = 0;
    }

    public MatchInfo(String n, String b, int i) {
        name = n;
        breed = b;
        imgSrc = i;
    }

    public String getName() {
        return name;
    }

    public String getBreed() {
        return breed;
    }

    public int getImgSrc() {
        return imgSrc;
    }
}
