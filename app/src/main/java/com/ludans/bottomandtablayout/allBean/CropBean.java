package com.ludans.bottomandtablayout.allBean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CropBean implements Serializable {

        @Override
        public String toString() {
            return "CropBean{" +
                    "type='" + type + '\'' +
                    ", content=" + content +
                    '}';
        }

        /**
         * content : [{"crop_disasters_name":"恶苗病","crop_disasters_url":"0001-0003%20水稻恶苗病.htm"},{"crop_disasters_name":"细菌性条斑病","crop_disasters_url":"0057-0058%20水稻细菌性条斑病.htm"}]
         * type : /disease/01－水稻
         */

        private String type;
        private List<ContentBean> content;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public ArrayList<ContentBean> getContent() {
            return (ArrayList<ContentBean>) content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * crop_disasters_name : 恶苗病
             * crop_disasters_url : 0001-0003%20水稻恶苗病.htm
             */

            private String crop_disasters_name;
            private String crop_disasters_url;

            public String getCrop_disasters_name() {
                return crop_disasters_name;
            }

            public void setCrop_disasters_name(String crop_disasters_name) {
                this.crop_disasters_name = crop_disasters_name;
            }

            public String getCrop_disasters_url() {
                return crop_disasters_url;
            }

            public void setCrop_disasters_url(String crop_disasters_url) {
                this.crop_disasters_url = crop_disasters_url;
            }
        }
    }


