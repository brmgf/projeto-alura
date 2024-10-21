CREATE TABLE Course(
    id bigint(20) NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    code varchar(10) NOT NULL,
    description varchar(255),
    inactivateDateTime datetime DEFAULT CURRENT_TIMESTAMP,
    status enum('ACTIVE', 'INACTIVE') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT 'ACTIVE',
    user_id bigint NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT CC_Code UNIQUE (code),
    CONSTRAINT CC_User foreign key (user_id) references user (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;