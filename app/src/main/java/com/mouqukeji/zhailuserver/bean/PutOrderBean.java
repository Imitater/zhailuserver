package com.mouqukeji.zhailuserver.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PutOrderBean {

    /**
     * ordersList : [{"order_id":"610","cate_id":"12","cate_name":"帮忙买","demand":"","delivery_time":"2019-3-9 14:40","address":"","detail":"","buy_address":"","create_time":"9分钟前","task_price":"2.00","start_address":null,"start_detail":null,"end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"ccc","type_name":"早餐","weight":"0","express_point":"","goods":"包子包子","progress":"3","makeup_id":"0","picture":"","express_pay_type":"1"},{"order_id":"609","cate_id":"12","cate_name":"帮忙买","demand":"","delivery_time":"2019-3-9 14:04","address":"","detail":"","buy_address":"","create_time":"45分钟前","task_price":"2.00","start_address":null,"start_detail":null,"end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"ccc","type_name":"午餐","weight":"0","express_point":"","goods":"学校食堂","progress":"8","makeup_id":"22","picture":"http://picture.mouqukeji.com/icon_8225222019-21-9","express_pay_type":"1"},{"order_id":"607","cate_id":"13","cate_name":"帮忙寄","demand":"","delivery_time":"2019-3-9 12:58","address":"韩医生专业祛痘连锁机构(滨江店)","detail":"李连杰","buy_address":"","create_time":"1小时前","task_price":"2.00","start_address":"韩医生专业祛痘连锁机构(滨江店)","start_detail":"ccc","end_address":null,"end_detail":null,"type_name":"食品","weight":"1","express_point":"垃圾","goods":"","progress":"8","makeup_id":"0","picture":"","express_pay_type":"2"},{"order_id":"606","cate_id":"13","cate_name":"帮忙寄","demand":"","delivery_time":"立即取件","address":"芘芙蛋糕(星光大道店)","detail":"巴黎","buy_address":"","create_time":"2小时前","task_price":"2.00","start_address":"韩医生专业祛痘连锁机构(滨江店)","start_detail":"ccc","end_address":null,"end_detail":null,"type_name":"数码产品","weight":"1","express_point":"哈哈哈","goods":"","progress":"5","makeup_id":"0","picture":"","express_pay_type":"2"},{"order_id":"605","cate_id":"14","cate_name":"帮忙送","demand":"","delivery_time":"立即配送","address":"","detail":"","buy_address":"","create_time":"2小时前","task_price":"2.00","start_address":"韩医生专业祛痘连锁机构(滨江店)","start_detail":"ccc","end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"ccc","type_name":"文件","weight":"1","express_point":"","goods":"","progress":"4","makeup_id":"0","picture":"","express_pay_type":"1"},{"order_id":"604","cate_id":"13","cate_name":"帮忙寄","demand":"","delivery_time":"立即取件","address":"韩医生专业祛痘连锁机构(滨江店)","detail":"巴黎","buy_address":"","create_time":"2小时前","task_price":"2.00","start_address":"韩医生专业祛痘连锁机构(滨江店)","start_detail":"ccc","end_address":null,"end_detail":null,"type_name":"数码产品","weight":"1","express_point":"回来了","goods":"","progress":"4","makeup_id":"0","picture":"","express_pay_type":"2"},{"order_id":"600","cate_id":"12","cate_name":"帮忙买","demand":"","delivery_time":"2019-3-9 12:01","address":"","detail":"","buy_address":"","create_time":"2小时前","task_price":"2.00","start_address":null,"start_detail":null,"end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"ccc","type_name":"午餐","weight":"0","express_point":"","goods":"学校食堂","progress":"4","makeup_id":"21","picture":"http://picture.mouqukeji.com/icon_1533892019-21-9","express_pay_type":"1"},{"order_id":"599","cate_id":"12","cate_name":"帮忙买","demand":"","delivery_time":"2019-3-9 11:47","address":"","detail":"","buy_address":"","create_time":"3小时前","task_price":"2.00","start_address":null,"start_detail":null,"end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"ccc","type_name":"午餐","weight":"0","express_point":"","goods":"水饺炒菜","progress":"4","makeup_id":"20","picture":"http://picture.mouqukeji.com/icon_8975532019-21-9","express_pay_type":"1"},{"order_id":"592","cate_id":"12","cate_name":"帮忙买","demand":"","delivery_time":"2019-3-9 11:10","address":"","detail":"","buy_address":"","create_time":"3小时前","task_price":"2.00","start_address":null,"start_detail":null,"end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"ccc","type_name":"午餐","weight":"0","express_point":"","goods":"学校食堂学校食堂学校食堂","progress":"4","makeup_id":"19","picture":"http://picture.mouqukeji.com/icon_4066682019-21-9","express_pay_type":"1"},{"order_id":"591","cate_id":"12","cate_name":"帮忙买","demand":"","delivery_time":"2019-3-9 11:10","address":"","detail":"","buy_address":"","create_time":"3小时前","task_price":"2.00","start_address":null,"start_detail":null,"end_address":"韩医生专业祛痘连锁机构(滨江店)","end_detail":"ccc","type_name":"晚餐","weight":"0","express_point":"","goods":"汤面","progress":"4","makeup_id":"18","picture":"http://picture.mouqukeji.com/icon_9492952019-21-9","express_pay_type":"1"}]
     * pages : 6
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
         * order_id : 610
         * cate_id : 12
         * cate_name : 帮忙买
         * demand :
         * delivery_time : 2019-3-9 14:40
         * address :
         * detail :
         * buy_address :
         * create_time : 9分钟前
         * task_price : 2.00
         * start_address : null
         * start_detail : null
         * end_address : 韩医生专业祛痘连锁机构(滨江店)
         * end_detail : ccc
         * type_name : 早餐
         * weight : 0
         * express_point :
         * goods : 包子包子
         * progress : 3
         * makeup_id : 0
         * picture :
         * express_pay_type : 1
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
        private String makeup_id;
        private String picture;
        private String express_pay_type;

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

        public String getMakeup_id() {
            return makeup_id;
        }

        public void setMakeup_id(String makeup_id) {
            this.makeup_id = makeup_id;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getExpress_pay_type() {
            return express_pay_type;
        }

        public void setExpress_pay_type(String express_pay_type) {
            this.express_pay_type = express_pay_type;
        }
    }
}
