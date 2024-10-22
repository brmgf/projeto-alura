CREATE TABLE Registration(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    registrationDateTime DATETIME NOT NULL,

    CONSTRAINT RC_Student FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT RC_Course FOREIGN KEY (course_id) REFERENCES course(id),
    UNIQUE(user_id, course_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;