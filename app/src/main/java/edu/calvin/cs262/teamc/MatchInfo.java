package edu.calvin.cs262.teamc;

/** MatchInfo
 *
 * Contains information on matches
 *
 * @author Andrw Thomas
 */
public class MatchInfo {
     String name;
     String breed;
     String imgSrc;

    public MatchInfo() {
        name = breed = null;
        imgSrc = "";
    }

    public MatchInfo(String n, String b, String i) {
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

    public String getImgSrc() {
        return imgSrc;
    }
}
