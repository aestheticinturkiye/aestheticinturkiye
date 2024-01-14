package com.dev.esthomy.models;

import lombok.Getter;

@Getter
public enum MemberRole {
    HOSPITAL(0),
    PATIENT(1);

    private final int value;

    MemberRole(int value) {
        this.value = value;
    }

    public static MemberRole valueOf(int value) {
        for(MemberRole memberRole : MemberRole.values()){
            if(memberRole.value == value){
                return memberRole;
            }
        }
        return null;
    }
}
