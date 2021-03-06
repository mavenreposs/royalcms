package cn.royalcms.component.model.builder;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import java.util.ArrayList;
import java.util.List;

public class Sorts {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private List<Order> orders;

        public Builder() {
            this.orders = new ArrayList<>();
        }

        public Builder asc(String property) {
            return asc(true, property);
        }

        public Builder desc(String property) {
            return desc(true, property);
        }

        public Builder asc(boolean condition, String property) {
            if (condition) {
                orders.add(new Order(Sort.Direction.ASC, property));
            }
            return this;
        }

        public Builder desc(boolean condition, String property) {
            if (condition) {
                orders.add(new Order(Sort.Direction.DESC, property));
            }
            return this;
        }

        public Sort build() {
            return Sort.by(orders);
        }
    }
}

