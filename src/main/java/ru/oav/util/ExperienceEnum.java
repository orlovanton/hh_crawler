package ru.oav.util;

/**
 * Created by antonorlov on 25/09/2017.
 */
public enum ExperienceEnum {

    NO_EXPERIENCE("noExperience","Нет опыта"),
    BETWEEN_1_AND_3("between1And3","От 1 года до 3 лет"),
    BETWEEN_3_AND_6("between3And6","От 3 до 6 лет"),
    MORETHAN_6("between3And6","От 3 до 6 лет");

    private String code;
    private String desc;

    ExperienceEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
