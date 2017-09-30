package com.nis.docsearch.dao;

import com.nis.docsearch.entity.DocDocument;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocDocumentDao {
	void save(DocDocument arg0);

	void delete(@Param("id") String arg0);

	void update(DocDocument arg0);

	DocDocument get(@Param("id") String arg0);

	int getDocCountByDocName(@Param("docName") String arg0);

	List<DocDocument> findDocDocument(DocDocument arg0);

	List<DocDocument> findDocumentIdIn(@Param("idIn") List<String> arg0);

	int findDocDocumentCount(DocDocument arg0);

	List<DocDocument> getAll();

	List<DocDocument> findDocDocumentNOPrivilege(DocDocument arg0);

	int findDocDocumentCountNOPrivilege(DocDocument arg0);

	void updateDocComplain(DocDocument arg0);

	List<DocDocument> findDocDocumentNOPrivilegeForSuggest(DocDocument arg0);

	int findDocDocumentCountNOPrivilegeForSuggest(DocDocument arg0);

	List<DocDocument> getIndex(DocDocument arg0);
}