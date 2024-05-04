package com.dev.esthomy.models.enums;

import lombok.Getter;

@Getter
public enum MemberRole {
    BROKER(0),
    CLIENT(1);

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
