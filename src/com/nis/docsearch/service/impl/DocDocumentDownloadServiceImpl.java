package com.nis.docsearch.service.impl;

import com.nis.comm.entity.MyPage;
import com.nis.docsearch.dao.DocDocumentDownloadDao;
import com.nis.docsearch.entity.DocDocumentDownload;
import com.nis.docsearch.service.DocDocumentDownloadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DocDocumentDownloadServiceImpl implements DocDocumentDownloadService {
	@Autowired
	private DocDocumentDownloadDao qR;

	public void save(DocDocumentDownload docDocumentDownload) {
		this.qR.save(docDocumentDownload);
	}

	public void delete(String id) {
		this.qR.delete(id);
	}

	public void update(DocDocumentDownload docDocumentDownload) {
		this.qR.update(docDocumentDownload);
	}

	public DocDocumentDownload get(String id) {
		return this.qR.get(id);
	}

	public MyPage<DocDocumentDownload> a(DocDocumentDownload docDocumentDownload) {
		int total = this.qR.findDocDocumentDownloadCount(docDocumentDownload);
		List data = null;
		if (total > 0) {
			data = this.qR.findDocDocumentDownload(docDocumentDownload);
		}

		return new MyPage(docDocumentDownload.getPage().intValue(), docDocumentDownload.getSize().intValue(), total,
				data);
	}

	public List<DocDocumentDownload> getAll() {
		return this.qR.getAll();
	}
}