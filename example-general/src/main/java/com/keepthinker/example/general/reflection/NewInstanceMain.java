package com.keepthinker.example.general.reflection;

public class NewInstanceMain {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = WechatToken.class;

        WechatToken wechatToken = (WechatToken) clazz
                .getDeclaredConstructor(String.class, String.class)
                .newInstance("openId", "appId");

        System.out.println(wechatToken.getAppId());


    }

    public static class WechatToken{

        public WechatToken(String openId, String appId) {
            this.openId = openId;
            this.appId = appId;
        }

        private String openId;
        private String appId;

        public String getOpenId() {
            return openId;
        }

        public void setOpenId(String openId) {
            this.openId = openId;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
}
