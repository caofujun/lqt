package com.nis.docsearch.service;

import com.nis.comm.entity.MyPage;
import com.nis.docsearch.entity.DocDocumentDownload;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public interface DocDocumentDownloadService {
	void save(DocDocumentDownload arg0);

	void delete(String arg0);

	void update(DocDocumentDownload arg0);

	DocDocumentDownload get(String arg0);

	MyPage<DocDocumentDownload> a(DocDocumentDownload arg0);

	List<DocDocumentDownload> getAll();
}