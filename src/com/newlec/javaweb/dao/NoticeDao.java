package com.newlec.javaweb.dao;

import java.util.List;

import com.newlec.javaweb.entity.Notice;
import com.newlec.javaweb.entity.NoticeView;

public interface NoticeDao {
	List<NoticeView> getList(int page, String query);
	int getCount();
	NoticeView get(String id);
	int update(String id, String title, String content);
}
