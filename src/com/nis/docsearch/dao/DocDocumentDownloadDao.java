package com.nis.docsearch.dao;

import com.nis.docsearch.entity.DocDocumentDownload;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DocDocumentDownloadDao {
	void save(DocDocumentDownload arg0);

	void delete(@Param("id") String arg0);

	void update(DocDocumentDownload arg0);

	DocDocumentDownload get(@Param("id") String arg0);

	List<DocDocumentDownload> findDocDocumentDownload(DocDocumentDownload arg0);

	int findDocDocumentDownloadCount(DocDocumentDownload arg0);

	List<DocDocumentDownload> getAll();

	int findDocDocumentCountGroupByAccountId(@Param("docId") String arg0);
}