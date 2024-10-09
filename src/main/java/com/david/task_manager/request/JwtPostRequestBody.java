package com.david.task_manager.request;

import jakarta.validation.constraints.NotEmpty;

public record JwtPostRequestBody(@NotEmpty String username,
                                 @NotEmpty String password
) {}
