package com.mouqukeji.zhailuserver.bean;

import java.util.List;

public class GetOrderBean {

    /**
     * ordersList : [{"order_id":"26","cate_id":"12","cate_name":"帮忙买","demand":"","delivery_time":"2019-01-14 14点42分","address":"","detail":"","buy_address":"0","create_time":"37天前","task_price":"0.01","start_address":null,"start_detail":null,"end_address":"浙江省杭州市滨江区浙江中医药大学","end_detail":"生活区6幢603室","type_name":"零食","weight":"1","express_point":"请选择快递服务","goods":"泡面 1包","progress":"3"},{"order_id":"32","cate_id":"14","cate_name":"帮忙送","demand":"","delivery_time":"2019-01-14 15点47分","address":"","detail":"","buy_address":"0","create_time":"37天前","task_price":"0.01","start_address":"\"西湖\"","start_detail":"\"1单元\"","end_address":"\"asdsadasd\"","end_detail":"\"asdasdasdsdsadsadsad\"","type_name":"文件","weight":"1","express_point":"","goods":"","progress":"3"},{"order_id":"34","cate_id":"15","cate_name":"万能帮","demand":"请到生活区宿舍帮忙装下笔记本电脑系统","delivery_time":"2019-01-15 10点0分","address":"","detail":"","buy_address":"0","create_time":"36天前","task_price":"0.01","start_address":null,"start_detail":null,"end_address":"浙江省杭州市滨江区浙江中医药大学","end_detail":"生活区6幢603室","type_name":"文件","weight":"1","express_point":"","goods":"","progress":"3"},{"order_id":"41","cate_id":"14","cate_name":"帮忙送","demand":"","delivery_time":"2019-01-17 11点0分","address":"","detail":"","buy_address":"","create_time":"34天前","task_price":"3.00","start_address":"浙江省杭州市滨江区浙江中医药大学","start_detail":"生活区6幢603室","end_address":"浙江省杭州市滨江区浙江中医药大学","end_detail":"生活区6幢603室","type_name":"文件","weight":"1","express_point":"","goods":"","progress":"3"},{"order_id":"42","cate_id":"14","cate_name":"帮忙送","demand":"","delivery_time":"2019-01-17 11点10分","address":"","detail":"","buy_address":"","create_time":"34天前","task_price":"3.00","start_address":"浙江省杭州市滨江区浙江中医药大学","start_detail":"生活区6幢603室","end_address":"浙江省杭州市滨江区浙江中医药大学","end_detail":"生活区6幢603室","type_name":"文件","weight":"1","express_point":"","goods":"","progress":"3"},{"order_id":"49","cate_id":"11","cate_name":"帮忙取","demand":"","delivery_time":"2019-01-21 15点20分","address":"","detail":"","buy_address":"","create_time":"30天前","task_price":"0.01","start_address":null,"start_detail":null,"end_address":"浙江省杭州市滨江区浙江中医药大学","end_detail":"生活区6幢603室","type_name":"文件","weight":"1","express_point":"韵达快递","goods":"","progress":"3"},{"order_id":"304","cate_id":"11","cate_name":"帮忙取","demand":"","delivery_time":"2019-11-4 23:52","address":"","detail":"","buy_address":"","create_time":"15天前","task_price":"4.00","start_address":null,"start_detail":null,"end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"111","type_name":"食品","weight":"1","express_point":"中天·官河锦庭","goods":"","progress":"3"}]
     * pages : 1
     */

    private int pages;
    private List<OrdersListBean> ordersList;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<OrdersListBean> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<OrdersListBean> ordersList) {
        this.ordersList = ordersList;
    }

    public static class OrdersListBean {
        /**
         * order_id : 26
         * cate_id : 12
         * cate_name : 帮忙买
         * demand :
         * delivery_time : 2019-01-14 14点42分
         * address :
         * detail :
         * buy_address : 0
         * create_time : 37天前
         * task_price : 0.01
         * start_address : null
         * start_detail : null
         * end_address : 浙江省杭州市滨江区浙江中医药大学
         * end_detail : 生活区6幢603室
         * type_name : 零食
         * weight : 1
         * express_point : 请选择快递服务
         * goods : 泡面 1包
         * progress : 3
         */

        private String order_id;
        private String cate_id;
        private String cate_name;
        private String demand;
        private String delivery_time;
        private String address;
        private String detail;
        private String buy_address;
        private String create_time;
        private String task_price;
        private Object start_address;
        private Object start_detail;
        private String end_address;
        private String end_detail;
        private String type_name;
        private String weight;
        private String express_point;
        private String goods;
        private String progress;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCate_name() {
            return cate_name;
        }

        public void setCate_name(String cate_name) {
            this.cate_name = cate_name;
        }

        public String getDemand() {
            return demand;
        }

        public void setDemand(String demand) {
            this.demand = demand;
        }

        public String getDelivery_time() {
            return delivery_time;
        }

        public void setDelivery_time(String delivery_time) {
            this.delivery_time = delivery_time;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getBuy_address() {
            return buy_address;
        }

        public void setBuy_address(String buy_address) {
            this.buy_address = buy_address;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTask_price() {
            return task_price;
        }

        public void setTask_price(String task_price) {
            this.task_price = task_price;
        }

        public Object getStart_address() {
            return start_address;
        }

        public void setStart_address(Object start_address) {
            this.start_address = start_address;
        }

        public Object getStart_detail() {
            return start_detail;
        }

        public void setStart_detail(Object start_detail) {
            this.start_detail = start_detail;
        }

        public String getEnd_address() {
            return end_address;
        }

        public void setEnd_address(String end_address) {
            this.end_address = end_address;
        }

        public String getEnd_detail() {
            return end_detail;
        }

        public void setEnd_detail(String end_detail) {
            this.end_detail = end_detail;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getExpress_point() {
            return express_point;
        }

        public void setExpress_point(String express_point) {
            this.express_point = express_point;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }
    }
}
