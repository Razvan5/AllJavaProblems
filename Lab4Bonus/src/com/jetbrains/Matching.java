package com.jetbrains;

import java.util.List;

public class Matching {

    List<Element> matching;

    Matching(List<Element> matching){
        this.matching=matching;
    }

    public List<Element> getMatching() {
        return matching;
    }

    @Override
    public String toString() {
        return "Matching{" +
                "matching=" + matching +
                '}';
    }
}
