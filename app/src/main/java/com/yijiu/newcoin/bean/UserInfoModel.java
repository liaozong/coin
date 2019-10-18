package com.yijiu.newcoin.bean;

public class UserInfoModel {


    /**
     * user : {"userId":6,"userName":"135****959","phone":"13524542959","level":0,"usdtAddress":"1C37j1d85WZ5sfuYWpFuZqQssiBAsyxjLo","usdtBalance":1000,"cny":6868.5,"score":712.15,"inviteCode":"","inviteSelfCode":"hxfcw9gx0x","countryPhone":{"country":"中国大陆","english":"China","hot":1,"letterSort":"c","prefix":"+86","id":1},"deliveryId":0,"bindUsdt":false,"usdtRate":6.8685,"discount":0,"wxAccount":"","wxName":"","wxQrcode":"","zfbAccount":"","zfbName":"","zfbQrcode":"","cartSize":0}
     * token : 13524542959&Bearer eyJhbGciOiJIUzUxMiJ9.eyJST0xFIjoiUk9MRSIsImV4cCI6MTU2MzUzODY2Nn0.NqsIUHTlpdukoUtvwom6mP5NPnPm6Tj_O63SCogJeJxVq0ICxesyFvdAGAgXAD2kjvlp0PTdLXI3_tSULo8k3g
     */

    private UserBean user;
    private String token;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class UserBean {
        /**
         * userId : 6
         * userName : 135****959
         * phone : 13524542959
         * level : 0
         * usdtAddress : 1C37j1d85WZ5sfuYWpFuZqQssiBAsyxjLo
         * usdtBalance : 1000
         * cny : 6868.5
         * score : 712.15
         * inviteCode :
         * inviteSelfCode : hxfcw9gx0x
         * countryPhone : {"country":"中国大陆","english":"China","hot":1,"letterSort":"c","prefix":"+86","id":1}
         * deliveryId : 0
         * bindUsdt : false
         * usdtRate : 6.8685
         * discount : 0
         * wxAccount :
         * wxName :
         * wxQrcode :
         * zfbAccount :
         * zfbName :
         * zfbQrcode :
         * cartSize : 0
         */

        private int userId;
        private String userName;
        private String phone;
        private int level;
        private String usdtAddress;
        private double usdtBalance;
        private double cny;
        private double score;
        private String inviteCode;
        private String inviteSelfCode;
        private CountryPhoneBean countryPhone;
        private int deliveryId;
        private boolean bindUsdt;
        private double usdtRate;
        private int discount;
        private String wxAccount;
        private String wxName;
        private String wxQrcode;
        private String zfbAccount;
        private String zfbName;
        private String zfbQrcode;
        private int cartSize;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getUsdtAddress() {
            return usdtAddress;
        }

        public void setUsdtAddress(String usdtAddress) {
            this.usdtAddress = usdtAddress;
        }

        public double getUsdtBalance() {
            return usdtBalance;
        }

        public void setUsdtBalance(double usdtBalance) {
            this.usdtBalance = usdtBalance;
        }

        public double getCny() {
            return cny;
        }

        public void setCny(double cny) {
            this.cny = cny;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(String inviteCode) {
            this.inviteCode = inviteCode;
        }

        public String getInviteSelfCode() {
            return inviteSelfCode;
        }

        public void setInviteSelfCode(String inviteSelfCode) {
            this.inviteSelfCode = inviteSelfCode;
        }

        public CountryPhoneBean getCountryPhone() {
            return countryPhone;
        }

        public void setCountryPhone(CountryPhoneBean countryPhone) {
            this.countryPhone = countryPhone;
        }

        public int getDeliveryId() {
            return deliveryId;
        }

        public void setDeliveryId(int deliveryId) {
            this.deliveryId = deliveryId;
        }

        public boolean isBindUsdt() {
            return bindUsdt;
        }

        public void setBindUsdt(boolean bindUsdt) {
            this.bindUsdt = bindUsdt;
        }

        public double getUsdtRate() {
            return usdtRate;
        }

        public void setUsdtRate(double usdtRate) {
            this.usdtRate = usdtRate;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

        public String getWxAccount() {
            return wxAccount;
        }

        public void setWxAccount(String wxAccount) {
            this.wxAccount = wxAccount;
        }

        public String getWxName() {
            return wxName;
        }

        public void setWxName(String wxName) {
            this.wxName = wxName;
        }

        public String getWxQrcode() {
            return wxQrcode;
        }

        public void setWxQrcode(String wxQrcode) {
            this.wxQrcode = wxQrcode;
        }

        public String getZfbAccount() {
            return zfbAccount;
        }

        public void setZfbAccount(String zfbAccount) {
            this.zfbAccount = zfbAccount;
        }

        public String getZfbName() {
            return zfbName;
        }

        public void setZfbName(String zfbName) {
            this.zfbName = zfbName;
        }

        public String getZfbQrcode() {
            return zfbQrcode;
        }

        public void setZfbQrcode(String zfbQrcode) {
            this.zfbQrcode = zfbQrcode;
        }

        public int getCartSize() {
            return cartSize;
        }

        public void setCartSize(int cartSize) {
            this.cartSize = cartSize;
        }

        public static class CountryPhoneBean {
            /**
             * country : 中国大陆
             * english : China
             * hot : 1
             * letterSort : c
             * prefix : +86
             * id : 1
             */

            private String country;
            private String english;
            private int hot;
            private String letterSort;
            private String prefix;
            private int id;

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getEnglish() {
                return english;
            }

            public void setEnglish(String english) {
                this.english = english;
            }

            public int getHot() {
                return hot;
            }

            public void setHot(int hot) {
                this.hot = hot;
            }

            public String getLetterSort() {
                return letterSort;
            }

            public void setLetterSort(String letterSort) {
                this.letterSort = letterSort;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
