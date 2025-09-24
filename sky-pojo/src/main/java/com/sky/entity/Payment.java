package com.sky.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    // 支付ID
    private UUID paymentId;

    // 用户ID
    private UUID userId;

    // 相关实体ID（如订单ID、课程ID等）
    private UUID relatedId;

    // 支付金额
    private BigDecimal amount;

    // 支付类型
    private Type type;

    // 支付方式
    private Method method;

    // 订单状态
    private Status status;

    // 订单创建时间
    private LocalDateTime createTime;

    // 最后更新时间
    private LocalDateTime updateTime;

    // 是否删除
    private Boolean deleted;

    @Getter
    public enum Type {
        TOP_UP("topUp", "充值"),
        COURSE("course", "课程购买"),
        TOURNAMENT("tournament", "赛事报名"),
        REFUND("refund", "退款");

        private final String type;
        private final String description;

        Type(String type, String description) {
            this.type = type;
            this.description = description;
        }
    }

    @Getter
    public enum Method {
        WECHAT("wechat", "微信支付"),
        ALIPAY("alipay", "支付宝支付"),
        OFFLINE("offline", "线下支付");

        private final String method;
        private final String description;

        Method(String method, String description) {
            this.method = method;
            this.description = description;
        }
    }

    @Getter
    public enum Status {
        PENDING("pending", "待支付"),
        COMPLETED("completed", "已完成"),
        FAILED("failed", "支付失败");

        private final String status;
        private final String description;

        Status(String status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
