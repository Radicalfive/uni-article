package top.radical.article.common;

/**
 * @author : radical
 * @description :性别枚举：性别:0男 1女 2保密
 * @data : 2022/3/22
 **/
public enum Gender {
    /**
     * 默认
     */
    DEFAULT(-1, "默认"),
    /**
     * 男
     */
    MALE(0, "男"),
    /**
     * 女
     */
    FEMALE(1, "女"),
    /**
     * 保密
     */
    SECRET(2, "保密");
    /**
     * key
     */
    private int key;
    /**
     * value
     */
    private String value;

    Gender(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public static Gender getValue(int key) {
        for (Gender gender : Gender.values()) {
            if (gender.getKey() == key) {
                return gender;
            }
        }
        return DEFAULT;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
