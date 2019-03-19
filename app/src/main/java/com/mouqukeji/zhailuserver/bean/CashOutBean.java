package com.mouqukeji.zhailuserver.bean;

public class CashOutBean {

    /**
     * serverBillDetail : {"ftype":"支付宝","amount":"1.00","progress":"2","create_time":"2019-02-02 11:11:20","update_time":"2019-02-02 11:11:20","account":"152231304764","forward_sn":"","type":"提现到账"}
     */

    private ServerBillDetailBean serverBillDetail;

    public ServerBillDetailBean getServerBillDetail() {
        return serverBillDetail;
    }

    public void setServerBillDetail(ServerBillDetailBean serverBillDetail) {
        this.serverBillDetail = serverBillDetail;
    }

    public static class ServerBillDetailBean {
        /**
         * ftype : 支付宝
         * amount : 1.00
         * progress : 2
         * create_time : 2019-02-02 11:11:20
         * update_time : 2019-02-02 11:11:20
         * account : 152231304764
         * forward_sn :
         * type : 提现到账
         */

        private String ftype;
        private String amount;
        private String progress;
        private String create_time;
        private String update_time;
        private String account;
        private String forward_sn;
        private String type;

        public String getFtype() {
            return ftype;
        }

        public void setFtype(String ftype) {
            this.ftype = ftype;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getForward_sn() {
            return forward_sn;
        }

        public void setForward_sn(String forward_sn) {
            this.forward_sn = forward_sn;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
