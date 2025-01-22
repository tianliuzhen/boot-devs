CREATE TABLE `t_order_1` (
                             `order_id` varchar(100) DEFAULT NULL,
                             `order_name` varchar(100) DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_order_item_1` (
                                  `order_item_id` varchar(100) DEFAULT NULL,
                                  `order_id` varchar(100) DEFAULT NULL,
                                  `order_item_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_order_0` (
                             `order_id` varchar(100) DEFAULT NULL,
                             `order_name` varchar(100) DEFAULT NULL,
                             `user_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_order_item_0` (
                                  `order_item_id` varchar(100) DEFAULT NULL,
                                  `order_id` varchar(100) DEFAULT NULL,
                                  `order_item_name` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `t_address` (
                             `id` bigint DEFAULT NULL,
                             `user_id` varchar(100) DEFAULT NULL,
                             `address` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
