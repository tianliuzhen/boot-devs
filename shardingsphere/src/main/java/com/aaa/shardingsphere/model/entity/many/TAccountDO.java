package com.aaa.shardingsphere.model.entity.many;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author liuzhen.tian
 * @version 1.0 TAccount.java  2025/2/5 21:15
 */
@Table(name = "t_account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TAccountDO {
    private Long accountId;
    private String accountName;
    private Long userId;
}
