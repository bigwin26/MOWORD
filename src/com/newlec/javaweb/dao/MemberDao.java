package com.newlec.javaweb.dao;

import com.newlec.javaweb.entity.Member;

public interface MemberDao {

	int insert(String id, String string, String name,String moon, String gender, String birthday, String phone, String email);

	int insert(Member member);

	Member get(String id);

}
