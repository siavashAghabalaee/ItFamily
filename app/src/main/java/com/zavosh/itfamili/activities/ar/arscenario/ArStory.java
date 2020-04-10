package com.zavosh.itfamili.activities.ar.arscenario;

class ArStory {

    private String refname;
    private ArChars[] chars;
    private Integer[][] charDoubleRelation;
    private ArSound[] sounds;
    private ArStoryboard storyboard;




    public String getRefname() {
        return refname;
    }

    public ArChars[] getChars() {
        return chars;
    }

    public Integer[][] getCharDoubleRelation() {
        return charDoubleRelation;
    }

    public ArSound[] getSounds() {
        return sounds;
    }

    public ArStoryboard getStoryboard() {
        return storyboard;
    }
}
