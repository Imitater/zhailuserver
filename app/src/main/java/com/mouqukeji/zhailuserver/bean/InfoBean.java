package com.mouqukeji.zhailuserver.bean;

public class InfoBean {


    /**
     * userInfo : {"avatar":"http://picture.mouqukeji.com/icon_4610232019-11-4","username":"13323038757","nickname":"13323038757","background_img":"","gender":"0","school_name":"芘芙蛋糕(星光大道店)","order_num":14,"punctual":"0.0%","avg":"暂无"}
     */

    private UserInfoBean userInfo;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class UserInfoBean {
        /**
         * avatar : http://picture.mouqukeji.com/icon_4610232019-11-4
         * username : 13323038757
         * nickname : 13323038757
         * background_img :
         * gender : 0
         * school_name : 芘芙蛋糕(星光大道店)
         * order_num : 14
         * punctual : 0.0%
         * avg : 暂无
         */

        private String avatar;
        private String username;
        private String nickname;
        private String background_img;
        private String gender;
        private String school_name;
        private int order_num;
        private String punctual;
        private String avg;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getBackground_img() {
            return background_img;
        }

        public void setBackground_img(String background_img) {
            this.background_img = background_img;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getSchool_name() {
            return school_name;
        }

        public void setSchool_name(String school_name) {
            this.school_name = school_name;
        }

        public int getOrder_num() {
            return order_num;
        }

        public void setOrder_num(int order_num) {
            this.order_num = order_num;
        }

        public String getPunctual() {
            return punctual;
        }

        public void setPunctual(String punctual) {
            this.punctual = punctual;
        }

        public String getAvg() {
            return avg;
        }

        public void setAvg(String avg) {
            this.avg = avg;
        }
    }
}
