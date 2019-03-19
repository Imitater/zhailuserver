package com.mouqukeji.zhailuserver.bean;

public class SigninBean {


    /**
     * user : {"user_id":"57","token":"84138f05e2242f3bd34dca3f1e20e80ab8a87eae","did":null}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * user_id : 57
         * token : 84138f05e2242f3bd34dca3f1e20e80ab8a87eae
         * did : null
         */

        private String user_id;
        private String token;
        private Object did;

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getDid() {
            return did;
        }

        public void setDid(Object did) {
            this.did = did;
        }
    }
}
