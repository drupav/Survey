package com.survey.app.model;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.survey.app.model.audit.DateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rajeevkumarsingh on 01/08/17.
 */

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "username"
        }),
        @UniqueConstraint(columnNames = {
            "email"
        })
})
public class User extends DateAudit implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String name;

    @NotBlank
    @Size(max = 15)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Column(name = "is_active", columnDefinition = "BOOLEAN")
    private Boolean isActive;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User() {

    }

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String name, String username, String email) {
    	
	}

	public User(Long id, String name, String username, String email) {
		this.id = id;
		 this.name = name;
         this.username = username;
         this.email = email;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public Boolean isActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities()
	{
		Set<Role> roles = this.getRoles();

		if (roles == null) {
			return Collections.emptyList();
		}

		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
		}

		return authorities;
	}


	@Override
	public String getUsername()
	{
		return this.username;
	}





	@Override
	public boolean isAccountNonExpired()
	{
		return true;
	}


	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}


	@Override
	public boolean isEnabled()
	{
		return true;
	}
    
    
}