/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.test.users.clients;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author rober
 */
@Data
@Builder
public class GitHubUserResponse {

    private long id;
    private String login;
    private String name;

    private String type;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String avatar_url;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime created_at;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long followers;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long public_repos;

    @JsonProperty("avatarUrl")
    public String getAvatarUrl() {
        return this.avatar_url;
    }

    @JsonProperty("createdAt")
    public LocalDateTime getCreatedAt() {
        return this.created_at;
    }

    @JsonProperty(value = "calculations", access = JsonProperty.Access.READ_ONLY)
    public float calculations() {
        return (float) 6 * (2 + public_repos) / followers;
    }

}
