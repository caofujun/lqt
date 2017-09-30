package com.nis.docsearch.service;

import com.nis.access.entity.AcAccount;
import com.nis.comm.entity.MyPage;
import com.nis.docsearch.entity.DocDocument;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Component
public interface DocDocumentService {
	String a(DocDocument arg0);

	void delete(String arg0);

	void update(DocDocument arg0);

	DocDocument get(String arg0);

	int getDocCountByDocName(String arg0);

	MyPage<DocDocument> b(DocDocument arg0);

	List<DocDocument> getAll();

	List<DocDocument> findDocDocument(DocDocument arg0);

	Map<String, DocDocument> C(List<String> arg0);

	int findDocDocumentCount(DocDocument arg0);

	String a(CommonsMultipartFile arg0, AcAccount arg1, String arg2) throws Exception;

	void a(String arg0, AcAccount arg1) throws Exception;

	void a(DocDocument arg0, AcAccount arg1) throws Exception;

	List<DocDocument> findDocDocumentNOPrivilege(DocDocument arg0);

	int findDocDocumentCountNOPrivilege(DocDocument arg0);

	void w(String arg0, String arg1) throws Exception;

	MyPage<DocDocument> c(DocDocument arg0);

	List<DocDocument> findDocDocumentNOPrivilegeForSuggest(DocDocument arg0);

	int findDocDocumentCountNOPrivilegeForSuggest(DocDocument arg0);

	List<DocDocument> getIndex(DocDocument arg0);
}