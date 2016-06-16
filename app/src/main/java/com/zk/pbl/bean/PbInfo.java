package com.zk.pbl.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16.
 */
public class PbInfo {


    /**
     * children : [{"code":"40edd31c889f4180a2926317b4991b76","createdate":"2016-06-16 11:23:59","id":11,"number":2,"prodline":1,"seq":2,"state":"正常"},{"code":"0fdf198ff8434c2380eada3cc66631ba","createdate":"2016-06-16 11:23:59","id":12,"number":2,"prodline":1,"seq":1,"state":"正常"},{"code":"27fcbc3e5efc4c36b01bb923980f1a65","createdate":"2016-06-16 11:23:59","id":13,"number":2,"prodline":1,"seq":3,"state":"正常"}]
     * code : af01f64eff294a5bbe18f47410477c2c
     * createdate : 2016-06-16 11:23:59
     * descript : sdfas
     * emergency : 否
     * id : 1
     * number : 0
     * prodline : 1
     * seq : 0
     * state : 正常
     * type : 产品单
     */

    private String code;
    private String createdate;
    private String descript;
    private String emergency;
    private int id;
    private int number;
    private int prodline;
    private int seq;
    private String state;
    private String type;
    /**
     * code : 40edd31c889f4180a2926317b4991b76
     * createdate : 2016-06-16 11:23:59
     * id : 11
     * number : 2
     * prodline : 1
     * seq : 2
     * state : 正常
     */

    private List<ChildrenBean> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getProdline() {
        return prodline;
    }

    public void setProdline(int prodline) {
        this.prodline = prodline;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public static class ChildrenBean {
        private String code;
        private String createdate;
        private int id;
        private int number;
        private int prodline;
        private int seq;
        private String state;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getProdline() {
            return prodline;
        }

        public void setProdline(int prodline) {
            this.prodline = prodline;
        }

        public int getSeq() {
            return seq;
        }

        public void setSeq(int seq) {
            this.seq = seq;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
