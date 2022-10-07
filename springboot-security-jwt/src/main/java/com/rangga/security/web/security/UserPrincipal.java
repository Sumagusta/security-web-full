package com.rangga.security.web.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.rangga.security.web.model.User;

public class UserPrincipal implements UserDetails{
	
	private User userModel;
	
	public UserPrincipal(User userModel) {
		super(); // super digunakan untuk memanggil konstruktor kelas induk.
		this.userModel = userModel;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { // membolehkan collection untuk duplikat
		return Collections.singleton(new SimpleGrantedAuthority("USER")); // return collections class, Semua metode kelas ini menampilkan NullPointerException jika koleksi atau objek yang diteruskan ke metode tersebut adalah null.
			// singleton hanya boleh memiliki satu object, jika selanjutnya instance kembali maka objek baru akan merujuk ke object awal.
		//Collections.singleton() dimaksudkan untuk mmebuat hanya satu instance SimpleGrandAuthority, dan jika ada SimpleGrandAuthority lainnya maka value nya tetap merujuk ke USER
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return userModel.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userModel.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
