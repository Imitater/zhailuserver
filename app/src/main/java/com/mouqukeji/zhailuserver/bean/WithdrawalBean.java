package com.mouqukeji.zhailuserver.bean;

public class WithdrawalBean {


    /**
     * serverBillDetail : {"makeup_fee":"0.00","finish_time":"2019-02-20 16:23:55","order_sn":"2019020423572654995752","task_price":"1.00"}
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
         * makeup_fee : 0.00
         * finish_time : 2019-02-20 16:23:55
         * order_sn : 2019020423572654995752
         * task_price : 1.00
         */

        private String makeup_fee;
        private String finish_time;
        private String order_sn;
        private String task_price;

        public String getMakeup_fee() {
            return makeup_fee;
        }

        public void setMakeup_fee(String makeup_fee) {
            this.makeup_fee = makeup_fee;
        }

        public String getFinish_time() {
            return finish_time;
        }

        public void setFinish_time(String finish_time) {
            this.finish_time = finish_time;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getTask_price() {
            return task_price;
        }

        public void setTask_price(String task_price) {
            this.task_price = task_price;
        }
    }
}
