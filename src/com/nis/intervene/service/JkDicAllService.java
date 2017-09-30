package com.nis.intervene.service;

import com.nis.comm.entity.MyPage;
import com.nis.intervene.entity.JkDicAll;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface JkDicAllService {
	void save(JkDicAll arg0);

	void delete(String arg0);

	void update(JkDicAll arg0);

	JkDicAll get(String arg0);

	MyPage<JkDicAll> a(JkDicAll arg0);

	List<JkDicAll> getAll();

	List<JkDicAll> getByClassCode(String arg0);

	List<JkDicAll> findDictType();

	List<JkDicAll> findDict(JkDicAll arg0);
}