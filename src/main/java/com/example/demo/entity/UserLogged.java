package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;

import come.example.demo.enums.UserRole;


@Entity
@Table(name = "userlogged")
public class UserLogged {
	
	@Id
	@Column(name = "iduser")
	private String idUser;
	
	@Column(name = "dtlogin")
	private String dtLogin;
	
	@Column(name = "tmlogin")
	private String tmLogin;
	
	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	
	/**
	 * @return the idUser
	 */
	public String getIdUser() {
		return idUser;
	}

	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	/**
	 * @return the dtLogin
	 */
	public String getDtLogin() {
		return dtLogin;
	}

	/**
	 * @param dtLogin the dtLogin to set
	 */
	public void setDtLogin(String dtLogin) {
		this.dtLogin = dtLogin;
	}

	/**
	 * @return the tmLogin
	 */
	public String getTmLogin() {
		return tmLogin;
	}

	/**
	 * @param tmLogin the tmLogin to set
	 */
	public void setTmLogin(String tmLogin) {
		this.tmLogin = tmLogin;
	}

	/**
	 * @return the userRole
	 */
	public UserRole getUserRole() {
		return userRole;
	}

	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "UserLogged [idUser=" + idUser + ", dtLogin=" + dtLogin + ", tmLogin=" + tmLogin + ", userRole="
				+ userRole + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtLogin, idUser, tmLogin, userRole);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogged other = (UserLogged) obj;
		return Objects.equals(dtLogin, other.dtLogin) && Objects.equals(idUser, other.idUser)
				&& tmLogin == other.tmLogin && userRole == other.userRole;
	}
	

	
}
