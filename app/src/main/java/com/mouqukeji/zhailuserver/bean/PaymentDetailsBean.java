package com.mouqukeji.zhailuserver.bean;

import java.util.List;

public class PaymentDetailsBean {

    private List<ServerBillListBean> serverBillList;

    public List<ServerBillListBean> getServerBillList() {
        return serverBillList;
    }

    public void setServerBillList(List<ServerBillListBean> serverBillList) {
        this.serverBillList = serverBillList;
    }

    public static class ServerBillListBean {
        /**
         * id : 4
         * type : 3
         * money : 1.00
         * create_time : 2019-01-22 10:40
         */

        private String id;
        private String type;
        private String money;
        private String create_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }
    }
}
