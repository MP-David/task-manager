package com.david.task_manager.request;

import lombok.Data;

@Data
public class JwtPostRequestBody {
        private String username;
        private String password;
}
