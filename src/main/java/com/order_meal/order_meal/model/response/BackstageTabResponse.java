package com.order_meal.order_meal.model.response;

import org.springframework.beans.BeanUtils;

import com.order_meal.order_meal.entity.Tab;

import lombok.Getter;
import lombok.Setter;

public class BackstageTabResponse {
    

    @Getter
    @Setter
    public class Tabs {

        private Integer id;

        private String name;

        public Tabs() {

        }

        public Tabs(Tab tab) {
            BeanUtils.copyProperties(tab, this);

        }
    }
}
