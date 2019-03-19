package com.mouqukeji.zhailuserver.bean;

public class AccountListBean {

    /**
     * accountInfo : {"id":"2","account":"152****4764","name":"'李45四'"}
     */

    private AccountInfoBean accountInfo;

    public AccountInfoBean getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfoBean accountInfo) {
        this.accountInfo = accountInfo;
    }

    public static class AccountInfoBean {
        /**
         * id : 2
         * account : 152****4764
         * name : '李45四'
         */

        private String id;
        private String account;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
